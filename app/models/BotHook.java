package models;

import com.avaje.ebean.Model;
import play.db.NamedDatabase;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BotHook extends Model {

    public static Model.Finder<Long, BotHook> finder = new Model.Finder<>("default", BotHook.class);

    @Id
    public Long id;

    public String name;
    public String description;

    public String maker = "Bobby Tables";

    public String hookUrl;
}
