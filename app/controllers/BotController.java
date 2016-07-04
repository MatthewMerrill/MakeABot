package controllers;

import com.google.inject.Inject;
import play.libs.ws.WSRequest;
import play.libs.ws.WSClient;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class BotController extends Controller {

    @Inject
    WSClient wsClient;

    public CompletionStage<Result> sendBotMessage() {
        // WSRequest request = wsClient.url("");
        // TODO: Bot Logic

        return CompletableFuture.supplyAsync(
                () -> ok("Hello World!"));
    }


}
