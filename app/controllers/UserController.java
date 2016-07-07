package controllers;

import actions.AuthenticatedAction;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import play.cache.CacheApi;
import play.cache.NamedCache;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.With;
import services.CacheSingleton;
import views.html.user;

/**
 * Created by merrillm on 7/5/16.
 */
public class UserController extends Controller {

    @Inject @NamedCache("session-cache")
    CacheApi cache;

    @With(AuthenticatedAction.class)
    public Result index() {

        String idToken = Http.Context.current().session().get("idToken");
        JsonNode profile = cache.get(idToken + "profile");

        System.out.println("User: idToken=" + idToken + ", profile="+profile.get("name"));

        return ok(user.render(profile));
    }



}
