package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import play.data.validation.Constraints;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class User extends Model {

    public static Model.Finder<Long, User> finder = new Model.Finder<>(User.class);

    @Id
    public Long id;

    @Column(length = 16)
    @Constraints.MaxLength(16)
    public String username;

    @Constraints.Email
    public String email;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    public List<ForumPost> posts;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    public List<ForumComment> comments;

}
