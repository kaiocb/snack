package snack.domain.repositories;

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
        this.ingredients.add(new Ingredient(1L, "Alface", 0.40));
        this.ingredients.add(new Ingredient(2L, "Bacon", 2.00));
        this.ingredients.add(new Ingredient(3L, "Hambúrguer de carne", 3.00));
        this.ingredients.add(new Ingredient(4L, "Ovo", 0.80));
        this.ingredients.add(new Ingredient(5L, "Queijo", 1.50));
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

    public int idOf(String name) {
        return this.get(name).getId().intValue();
    }

    public Number valueOf(String name) {
        return this.get(name).getValue();

    }


    public Ingredient get(String name) {
        return this.get(name, false);
    }

    public Ingredient get(String name, boolean clone) {

        if (name != null && !name.equals("")) {

            for (Ingredient ingredient : this.ingredients) {
                if (ingredient.getName().toLowerCase().equals(name.toLowerCase()))
                    return clone ? new Ingredient(ingredient.getId(), ingredient.getName(), ingredient.getValue()) : ingredient;
            }
        }

        return null;
    }

    public Set<Ingredient> all() {
        return this.ingredients;
    }

    /**
     * Check of ingredient integrity (must have @notNull id
     *
     * @param ingredients
     * @return
     */
    public boolean integrity(Set<Ingredient> ingredients) {

        for (Ingredient ingredient : ingredients) {
            if (ingredient.getId() == null)
                return false;
        }

        return true;
    }
}
