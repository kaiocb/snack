package snack.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import snack.domain.Ingredient;
import snack.repositories.IngredientRepository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Set;

/**
 * Created by alien on 6/5/17.
 */

@Component
@Path("/ingredient")
@Produces(MediaType.APPLICATION_JSON)
public class IngredientService {

    @Autowired
    private IngredientRepository repository;

    public IngredientService() {
    }

    @GET
    @Path("list")
    public Set<Ingredient> list() {
        return repository.all();
    }

}
