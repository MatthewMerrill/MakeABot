package controllers;

import models.ForumComment;
import models.ForumPost;
import models.ForumSection;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;

import static play.libs.Json.toJson;

public class RestController extends Controller {

    public Result getHello() {
        return ok("Hello World!");
    }

    public Result getSections() {
        return ok(toJson(ForumSection.finder.findList()));
    }

    public Result getPosts() {
        return ok(toJson(ForumPost.finder.findList()));
    }

    public Result getComments() {
        return ok(toJson(ForumComment.finder.findList()));
    }

    public Result getUsers() {
        return ok(toJson(User.finder.findList()));
    }
}
