package snack.domain;

/**
 * Created by alien on 6/5/17.
 */

/**
 * Food ingredient used to make meals
 */

public class Ingredient {

    /**
     * Name of ingredient
     */
    private String name;

    /**
     * value / price per ingredient
     */
    private Number value;

    public Ingredient() {
    }

    public Ingredient(String name, Number value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Number getValue() {
        return value;
    }

    public void setValue(Number value) {
        this.value = value;
    }
}

