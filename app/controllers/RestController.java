package controllers;

import com.google.inject.Inject;
import models.ForumComment;
import models.ForumPost;
import models.ForumSection;
import models.User;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import services.RestWorker;

import java.util.Map;

import static play.libs.Json.toJson;

public class RestController extends Controller {

    @Inject
    RestWorker restWorker;

    public Result getHello() {
        return ok("Hello World!");
    }

    /*
    REST GETS
     */
    public Result getSections() {
        return ok(toJson(ForumSection.finder.findList()));
    }
    public Result getPosts() {
        return ok(toJson(ForumPost.finder.findList()));
    }
    public Result getComments() {
        return ok(toJson(ForumComment.finder.findList()));
    }

    /*
    REST ADDS
     */
    public Result addComment() {
        return restWorker.addModel(request(), ForumComment.class) ? ok() : badRequest();
    }
    public Result addPost() {
        return restWorker.addModel(request(), ForumPost.class) ? ok() : badRequest();
    }
    public Result addSection() {
        return restWorker.addModel(request(), ForumSection.class) ? ok() : badRequest();
    }

   /* public Result getUsers() {
        return ok(toJson(User.finder.findList()));
    } */
}
