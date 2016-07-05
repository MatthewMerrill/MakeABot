package services;

import com.avaje.ebean.Model;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Http;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class RestWorker {

    @Inject
    FormFactory formFactory;

    public <T extends Model> boolean addModel(Http.Request request, Class<T> clazz) {
        Map<String, String[]> data = request.body().asFormUrlEncoded();

        data.entrySet().stream().forEach((e) -> System.out.println(e.getKey() + " -> " + Arrays.toString(e.getValue())));

        Form<T> form = formFactory
                .form(clazz)
                .bindFromRequest(data);

        if (form.hasErrors()) {
            System.out.println(form.errors());
            return false;
        }

        if (form.hasGlobalErrors()) {
            System.out.println(form.globalErrors());
            return false;
        }

        try {
            T model = form.get();
            model.insert();
            return true;
        } catch (Exception ignored) {
            ignored.printStackTrace();
            return false;
        }
    }

}
