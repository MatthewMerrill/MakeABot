package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BotHook extends Model {

    public static Model.Finder<Long, BotHook> finder = new Model.Finder<>(BotHook.class);

    @Id
    public Long id;

    public String name;

    public String maker = "Bobby Tables";

    public String hookUrl;
}
