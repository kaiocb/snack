package snack.domain;

/**
 * Created by alien on 6/5/17.
 */

import java.util.HashSet;
import java.util.Set;

/**
 * Meal prepared with ingredients
 */

public class Meal {

    private String name;

    private Set<Ingredient> ingredients = new HashSet<Ingredient>();

    public Meal() {
    }

    public Meal(String name) {
        this.name = name;
    }

    public Meal(String name, Set<Ingredient> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }


    public Meal add(Ingredient ingredient) {
        this.ingredients.add(ingredient);
        return this;
    }

    public Meal add(Set<Ingredient> ingredients) {
        this.ingredients.addAll(ingredients);
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
