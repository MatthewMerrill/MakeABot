package controllers;

import helpers.Auth0Config;
import models.BotHook;
import models.ForumPost;
import models.ForumSection;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.auth0index;
import views.html.forum;
import views.html.index;
import views.html.playground;

import java.util.Collections;

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
        return ok(auth0index.render(Auth0Config.get()));
    }

    /*
    PLAYGROUND ROUTES
     */
    public Result playground() {
        return ok(playground.render(null));
    }
    public Result bot(Long id) {
        if (id == null)
            return redirect(routes.HomeController.playground());

        BotHook botHook = BotHook.finder.byId(id);

        if (botHook == null)
            return badRequest();

        return ok(playground.render(botHook));
    }


    /*
    FORUM ROUTES
     */
    public Result forum() {
        ForumSection topLevel = new ForumSection();
        topLevel.name = "Forum";
        topLevel.preventPosts = true;
        topLevel.childSections = ForumSection.finder.where()
                .eq("parent_section_id", null)
                .findList();
        topLevel.childPosts = Collections.emptyList();

        return ok(forum.render(topLevel));
    }

    public Result forumSection(Long id) {
        if (id == null)
            return redirect(routes.HomeController.forum());

        ForumSection section = ForumSection.finder.byId(id);

        if (section == null)
            return badRequest();

        return ok(views.html.forum.render(section));
    }

    public Result forumPost(Long id) {
        if (id == null)
            return redirect(routes.HomeController.forum());

        ForumPost post = ForumPost.finder.byId(id);

        if (post == null)
            return badRequest();

        return ok(views.html.forum_post.render(post));
    }

}
