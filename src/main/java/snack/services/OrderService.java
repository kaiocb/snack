package snack.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import snack.domain.Ingredient;
import snack.domain.Meal;
import snack.repositories.DiscountRepository;
import snack.repositories.IngredientRepository;
import snack.repositories.MealRepository;

import javax.validation.constraints.NotNull;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Set;

/**
 * Created by alien on 6/7/17.
 */

@Component
@Path("/order")
@Produces(MediaType.APPLICATION_JSON)
public class OrderService {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private DiscountRepository discountRepository;


    @POST
    @Path("/check")
    public Meal check(@NotNull Set<Ingredient> ingredients) {

        if (ingredients.size() == 0 || !ingredientRepository.integrity(ingredients))
            throw new BadRequestException();

        Meal meal = new Meal(ingredients);
        discountRepository.applyDiscounts(meal);

        System.out.println();

        return null;
    }

}
