package snack.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import snack.domain.Meal;
import snack.repositories.MealRepository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Set;

/**
 * Created by alien on 6/5/17.
 */

@Component
@Path("/meal")
@Produces(MediaType.APPLICATION_JSON)
public class MealService {

    @Autowired
    private MealRepository repository;

    public MealService() {
    }

    @GET
    @Path("list")
    public Set<Meal> list() {
        return repository.all();
    }


}
