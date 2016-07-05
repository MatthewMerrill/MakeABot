package models;

import com.avaje.ebean.Model;
import com.google.inject.Singleton;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
// TODO: Replies like 4chan
public class ForumComment extends Model {

    public static Model.Finder<Long, ForumComment> finder = new Model.Finder<>(ForumComment.class);

    @Id
    public Long id;

    @ManyToOne
    public ForumPost parentPost;

    @ManyToOne
    public User user;

    public String messageBody;

}
