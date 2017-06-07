package snack.domain.repositories;

import org.springframework.stereotype.Repository;
import snack.domain.repositories.IngredientRepository;
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

    public Set<Meal> all() {
        return this.meals;
    }
}