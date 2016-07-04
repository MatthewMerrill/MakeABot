package models;

import com.avaje.ebean.Model;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by merrillm on 7/3/16.
 */
public class ForumPost extends Model {

    public static Model.Finder<Long, ForumPost> finder = new Model.Finder<>(ForumPost.class);

    @Id
    public Long id;

    public String name;

    @ManyToOne
    public ForumSection parent;

    @OneToMany
    public List<ForumComment> comments;

}
