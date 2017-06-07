package snack.domain.repositories;

import org.springframework.stereotype.Repository;
import snack.domain.Meal;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by alien on 6/5/17.
 */

@Repository
public class MealRepository {

    private IngredientRepository ingredients;

    private Set<Meal> meals;

    public MealRepository() {

        ingredients = new IngredientRepository();

        this.meals = new HashSet<Meal>(4);
        this.meals.add(new Meal(1L, "X-Bacon").add(ingredients.get("Bacon", "Hambúrguer de carne", "Queijo")));
        this.meals.add(new Meal(2L, "X-Burger").add(ingredients.get("Hambúrguer de carne", "Queijo")));
        this.meals.add(new Meal(3L, "X-Egg").add(ingredients.get("Ovo", "Hambúrguer de carne", "Queijo")));
        this.meals.add(new Meal(4L, "X-Egg Bacon").add(ingredients.get("Ovo", "Bacon", "Hambúrguer de carne", "Queijo")));

    }

    public Meal get(String name) {

        if (name != null && !name.equals("")) {

            for (Meal meal : this.meals) {
                if (meal.getName().toLowerCase().equals(name.toLowerCase())) return meal;
            }
        }

        return null;
    }

    public Set<Meal> all() {
        return this.meals;
    }
}