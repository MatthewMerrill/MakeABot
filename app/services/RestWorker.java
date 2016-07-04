package services;

import com.avaje.ebean.Model;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Http;

import java.util.Map;

@Singleton
public class RestWorker {

    @Inject
    FormFactory formFactory;

    public <T extends Model> boolean addModel(Http.Request request, Class<T> clazz) {
        Map<String, String[]> data = request.body().asFormUrlEncoded();

        Form<T> form = formFactory.form(clazz);
        form.bindFromRequest(data);

        if (form.hasErrors()) {
            return false;
        }

        try {
            T model = form.get();
            model.insert();
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

}
