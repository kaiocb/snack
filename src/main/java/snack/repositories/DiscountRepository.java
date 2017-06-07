package snack.repositories;

import org.springframework.stereotype.Repository;
import snack.domain.Discount;
import snack.domain.Ingredient;
import snack.domain.Meal;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by alien on 6/7/17.
 */

@Repository
public class DiscountRepository {

    private IngredientRepository ingredientRepository;

    public DiscountRepository() {
        ingredientRepository = new IngredientRepository();
    }

    public Set<Discount> applyDiscounts(Meal meal) {

        meal.setDiscounts(new HashSet<>());

        /*
         * array of ingredient ids and counter
         */
        int[] ingredientCounter = new int[ingredientRepository.all().size() + 1];

        for (Ingredient ingredient : meal.getIngredients())
            ingredientCounter[ingredient.getId().intValue()]++;

        /*
         * Light discount rule
         */
        if (ingredientCounter[ingredientRepository.idOf("Alface")] > 0 && ingredientCounter[ingredientRepository.idOf("Bacon")] == 0)
            meal.getDiscounts().add(new Discount(1L, "Light"));

        /*
         * Muita carne rule
         */
        for (int i = 0; i < ingredientCounter[ingredientRepository.idOf("Hambúrguer de carne")] / 3; i++)
            meal.getDiscounts().add(new Discount(2L, "Muita carne"));

        /*
         * Muito queijo rule
         */
        for (int i = 0; i < ingredientCounter[ingredientRepository.idOf("Queijo")] / 3; i++)
            meal.getDiscounts().add(new Discount(3L, "Muito queijo"));

        return meal.getDiscounts();
    }

}
