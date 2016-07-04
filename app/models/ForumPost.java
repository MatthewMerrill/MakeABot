package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class ForumPost extends Model {

    public static Model.Finder<Long, ForumPost> finder = new Model.Finder<>(ForumPost.class);

    @Id
    public Long id;

    public String name;

    @ManyToOne
    public User user;

    @ManyToOne
    public ForumSection parentSection;

    @OneToMany(mappedBy = "parentPost")
    @JsonIgnore
    public List<ForumComment> comments;

}
