package actions;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import play.cache.CacheApi;
import play.cache.NamedCache;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;
import services.CacheSingleton;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class AuthenticatedAction extends Action<String> {

    @Inject @NamedCache("session-cache")
    CacheApi cache;

    @Override
    public CompletionStage<Result> call(Http.Context ctx) {

        String idToken = ctx.session().get("idToken");
        JsonNode user = cache.get(idToken + "profile");

        System.out.println(idToken);
        System.out.println(user);

        if (user == null)
            return CompletableFuture.supplyAsync(() -> redirect("/"));

        return delegate.call(ctx);
    }
}