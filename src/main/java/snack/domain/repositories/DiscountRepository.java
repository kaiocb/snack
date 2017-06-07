package snack.domain.repositories;

import org.apache.commons.math3.util.Precision;
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

    private Set<Discount> discounts;

    public DiscountRepository() {

        ingredientRepository = new IngredientRepository();

        this.discounts = new HashSet<>(3);
        this.discounts.add(new Discount(1L, "Light"));
        this.discounts.add(new Discount(2L, "Muita carne"));
        this.discounts.add(new Discount(3L, "Muito queijo"));
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
         * Muita carne rule
         */
        for (int i = 0; i < ingredientCounter[ingredientRepository.idOf("Hambúrguer de carne")] / 3; i++) {
            meal.getDiscounts().add(this.get("Muita carne"));
            meal.subIngredient(ingredientRepository.get("Hambúrguer de carne"));
        }

        /*
         * Muito queijo rule
         */
        for (int i = 0; i < ingredientCounter[ingredientRepository.idOf("Queijo")] / 3; i++) {
            meal.getDiscounts().add(this.get("Muito queijo"));
            meal.subIngredient(ingredientRepository.get("Queijo"));
        }

         /*
         * Light discount rule - final discount because giver 10 percent discount on entire price
         */
        if (ingredientCounter[ingredientRepository.idOf("Alface")] > 0 && ingredientCounter[ingredientRepository.idOf("Bacon")] == 0) {
            meal.getDiscounts().add(this.get("Light"));
            meal.setPrice(Precision.round(meal.getPrice().doubleValue() * 0.9, 2));
        }

        return meal.getDiscounts();
    }

    public Discount get(String name) {

        if (name != null && !name.equals("")) {

            for (Discount discount : this.discounts) {
                if (discount.getName().toLowerCase().equals(name.toLowerCase())) return discount;
            }
        }

        return null;
    }

}
