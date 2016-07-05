package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import models.BotHook;
import play.libs.ws.WSRequest;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class BotController extends Controller {

    @Inject
    WSClient wsClient;

    /**
     *
     * Example JQuery POST:
     * $.ajax({ method: "POST", url:"http://foo.local.me:9000/REST/bot/sendMessage?botid=1", data:'{"message":"What is love?"}', contentType: "application/json; charset=utf-8", success: function(data){console.log(data)}});
     *
     */

    public CompletionStage<Result> sendBotMessage() {
        Map<String, String[]> query = request().queryString();

        try {
            BotHook bot = BotHook.finder.byId(Long.valueOf(query.get("botid")[0]));
            WSRequest request = wsClient.url(bot.hookUrl);

            JsonNode data = request().body().asJson();
            System.out.println(data);

            CompletionStage<WSResponse> botPromise = request.post(data);

            return botPromise.thenApply(
                    (res) -> {
                        if (res.getStatus() == 200) {
                            return ok(res.getBody());
                        } else {
                            return badRequest(res.getBody());
                        }
                    });

        } catch (Exception e) {
            return CompletableFuture.supplyAsync(
                    () -> badRequest());
        }
    }


}
