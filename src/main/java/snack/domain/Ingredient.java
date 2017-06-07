package snack.domain;

/**
 * Created by alien on 6/5/17.
 */

/**
 * Food ingredient used to make meals
 */

public class Ingredient {

    private Long id;

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

    public Ingredient(Long id, String name, Number value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

