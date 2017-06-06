package snack.repositories;

import org.springframework.stereotype.Repository;
import snack.domain.Ingredient;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by alien on 6/5/17.
 */

@Repository
public class IngredientRepository {

    private Set<Ingredient> ingredients;

    public IngredientRepository() {
        this.ingredients = new HashSet<Ingredient>(5);
        this.ingredients.add(new Ingredient("Alface", 0.40));
        this.ingredients.add(new Ingredient("Bacon", 2.00));
        this.ingredients.add(new Ingredient("Hambúrguer de carne", 3.00));
        this.ingredients.add(new Ingredient("Ovo", 0.80));
        this.ingredients.add(new Ingredient("Queijo", 1.50));
    }

    public Ingredient get(int index) {
        return (index > -1 && index < 6) ? (Ingredient) this.ingredients.toArray()[index] : null;
    }

    public Set<Ingredient> get(String... names) {

        Set<Ingredient> selection = new HashSet<Ingredient>(names.length);

        for (String name : names) {
            selection.add(this.get(name));
        }

        return selection;
    }

    public Ingredient get(String name) {

        if (name != null && !name.equals("")) {

            for (Ingredient ingredient : this.ingredients) {
                if (ingredient.getName().toLowerCase().equals(name.toLowerCase())) return ingredient;
            }
        }

        return null;
    }

    public Set<Ingredient> all() {
        return this.ingredients;
    }
}
