package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class ForumSection extends Model {

    public static Model.Finder<Long, ForumSection> finder = new Model.Finder<>(ForumSection.class);

    @Id
    public Long id;

    public String name;

    public boolean preventPosts = false;

    @ManyToOne
    public ForumSection parentSection;

    @OneToMany(mappedBy = "parentSection")
    @JsonIgnore
    public List<ForumSection> childSections;

    @OneToMany(mappedBy = "parentSection")
    @JsonIgnore
    public List<ForumPost> childPosts;

}
