package models;

import com.avaje.ebean.Model;
import com.google.inject.Singleton;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

public class ForumSection extends Model {

    public static Model.Finder<Long, ForumSection> finder = new Model.Finder<>(ForumSection.class);

    @Id
    public Long id;

    public String name;

    @ManyToOne
    public ForumSection parent;

    @OneToMany
    public List<ForumSection> childSections;

    @OneToMany
    public List<ForumPost> childPosts;

}
