package models;

import com.avaje.ebean.Model;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

public class ForumComment extends Model {

    public static Model.Finder<Long, ForumComment> finder = new Model.Finder<>(ForumComment.class);

    @Id
    public Long id;

    @ManyToOne
    public ForumSection parentSection;

    @ManyToOne
    public ForumPost parentPost;

    @ManyToOne
    public User user;

    public String messageBody;

}
