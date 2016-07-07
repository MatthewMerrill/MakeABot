package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import helpers.Auth0Config;
import play.cache.CacheApi;
import play.cache.NamedCache;
import play.libs.F;
import play.libs.Json;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import scala.Option;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class Callback extends Controller {

    @Inject
    WSClient ws;

    @Inject @NamedCache("session-cache")
    CacheApi cache;

    public CompletionStage<Result> callback(Option<String> codeOpt) {

        Http.Session session = Http.Context.current().session();

        if (codeOpt.isDefined()) {
            String code = codeOpt.get();

            return getToken(code).thenApply((F.Tuple tuple) -> {
                String idToken = tuple._1.toString();
                String accessToken = tuple._2.toString();

                JsonNode user = getUser(accessToken);

                System.out.println("Setting cache with: " + user);
                cache.set(idToken + "profile", user);
                System.out.println("In cache: "+cache.get(idToken + "profile"));

                session.put("idToken", idToken);
                session.put("accessToken", accessToken);

                return redirect(routes.UserController.index());
            });
        }

        return CompletableFuture.supplyAsync(() -> badRequest());
    }

    public CompletionStage<F.Tuple> getToken(String code) {
        Auth0Config config = Auth0Config.get();

        JsonNode postData = Json.parse("{" +
                "\"client_id\":\"" + config.clientId() + "\"," +
                "\"client_secret\":\"" + config.secret() + "\"," +
                "\"redirect_uri\":\"" + config.callbackURL() + "\"," +
                "\"grant_type\":\"authorization_code\"," +
                "\"code\":\"" + code + "\"" +
                "}");

        CompletionStage<WSResponse> response = ws.url(String.format("https://%s/oauth/token", config.domain()))
                .setHeader(Http.HeaderNames.ACCEPT, Http.MimeTypes.JSON)
                .post(postData);

        return response.thenApply((res) -> {
            try {
                String idToken = res.asJson().get("id_token").textValue();
                String accessToken = res.asJson().get("access_token").textValue();
                return new F.Tuple(idToken, accessToken);

            } catch (Exception e) {
                e.printStackTrace();
                throw new IllegalStateException("Tokens not sent");
            }
        });

    }

    public JsonNode getUser(String accessToken) {
        Auth0Config config = Auth0Config.get();

        try {
            return ws.url(String.format("https://%s/userinfo", config.domain()))
                    .setQueryParameter("access_token", accessToken)
                    .get().thenApply(res -> res.asJson())
                    .toCompletableFuture().get();
        } catch (Exception e) {
            return null;
        }
    }

}
