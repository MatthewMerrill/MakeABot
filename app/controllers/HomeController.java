package controllers;

import models.ForumPost;
import models.ForumSection;
import play.mvc.*;

import views.html.*;

import java.util.Arrays;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public Result forum() {

        ForumSection section = new ForumSection();

        section.name = "Example Parent";
        section.childSections = Arrays.asList(
                new ForumSection(){{ this.name = "Child Section 1"; }}
        );
        section.childPosts = Arrays.asList(
                new ForumPost(){{ this.name = "Child Post 1"; }}
        );

        return ok(views.html.forum.render(section));
    }

}
