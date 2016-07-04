package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

public class User extends Model {

    public static Model.Finder<Long, User> finder = new Model.Finder<>(User.class);

    @Id
    public Long id;

    @Column(length = 16)
    @Constraints.MaxLength(16)
    public String username;

    @Constraints.Email
    public String email;
/*
    @OneToMany
    public List<ForumPost> posts;

    @OneToMany
    public List<ForumComment> comments;
*/

}
