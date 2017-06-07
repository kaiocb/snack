package snack.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import snack.domain.Discount;
import snack.domain.Ingredient;
import snack.domain.Meal;
import snack.domain.repositories.DiscountRepository;
import snack.domain.repositories.IngredientRepository;
import snack.domain.repositories.MealRepository;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by alien on 6/7/17.
 */

/**
 * TESTS FOR @see snack.services.OrderService
 */

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class OrderServiceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private DiscountRepository discountRepository;

    /**
     * Test: Check user's order.
     * Service: '/order/check' @see {@link snack.services.OrderService#check(Set)}
     * Input: a empty Set of Ingredients @see {@link snack.domain.Ingredient}
     *
     * @result Return @see {@link HttpStatus#BAD_REQUEST}.
     */
    @Test
    public void testCheckWithEmptyIngredientSet() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<String> entity = this.restTemplate.postForEntity("/snack/resources/order/check", new HashSet<>(), String.class, headers);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    /**
     * Test: Check user's meal price.
     * Service: '/order/check' @see {@link snack.services.OrderService#check(Set)}.
     * Input: a Set of Ingredients @see {@link Ingredient} from meals repository @see {@link MealRepository#all()}
     *
     * @result Return @see {@link HttpStatus#OK}, valid Meal.
     */
    @Test
    public void testCheckAllMealsPrices() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Test all menu meals
        for (Meal standardMeal : mealRepository.all()) {

            Number price = 0;

            // ingredient values direct from repository e.q (db, cache, heap)
            for (Ingredient ingredient : standardMeal.getIngredients()) {
                price = price.doubleValue() + ingredient.getValue().doubleValue();
            }

            ResponseEntity<Meal> entity = this.restTemplate.postForEntity("/snack/resources/order/check", standardMeal.getIngredients(), Meal.class, headers);

            assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(entity.getBody()).isInstanceOf(Meal.class);
            assertThat(entity.getBody().getPrice()).isNotNull();
            assertThat(entity.getBody().getPrice()).isEqualTo(price);
        }
    }

    /**
     * Check user's meal with no discounts.
     * Service: '/order/check' @see {@link snack.services.OrderService#check(Set)}.
     * Input: a Set of Ingredients @see {@link Ingredient} with no discount @see {@link snack.domain.Discount}
     *
     * @result Return @see {@link HttpStatus#OK}, valid Meal with no Discount.
     */
    @Test
    public void testCheckNoDiscount() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // No discount test
        Meal standardMeal = new Meal();

        standardMeal.add(ingredientRepository.get("Alface", true))
                .add(ingredientRepository.get("Bacon", true))
                .add(ingredientRepository.get("Hambúrguer de carne", true));

        ResponseEntity<Meal> entity = this.restTemplate.postForEntity("/snack/resources/order/check", standardMeal.getIngredients(), Meal.class, headers);

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).isInstanceOf(Meal.class);
        assertThat(entity.getBody().getPrice()).isNotNull();
        assertThat(entity.getBody().getDiscounts()).isEmpty();
    }


    /**
     * Check user's meal with light discount.
     * Test '/order/check' @see {@link snack.services.OrderService#check(Set)} service against a Set of Ingredients with light discount @see {@link Discount}
     *
     * @result Return @see {@link HttpStatus#OK}, valid Meal with Light Discount.
     */
    @Test
    public void testCheckLightDiscount() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // No discount test
        Meal standardMeal = new Meal();

        standardMeal.add(ingredientRepository.get("Alface", true))
                .add(ingredientRepository.get("Hambúrguer de carne", true));

        ResponseEntity<Meal> entity = this.restTemplate.postForEntity("/snack/resources/order/check", standardMeal.getIngredients(), Meal.class, headers);

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).isInstanceOf(Meal.class);
        assertThat(entity.getBody().getPrice()).isNotNull();
        assertThat(entity.getBody().getDiscounts()).isNotNull();
        assertThat(((Discount) entity.getBody().getDiscounts().toArray()[0]).getId()).isEqualTo(discountRepository.get("Light").getId());
    }

    /**
     * Check user's meal with lot of cheese discount.
     * Test '/order/check' @see {@link snack.services.OrderService#check(Set)} service against a Set of Ingredients with lot of cheese discount @see {@link Discount}
     *
     * @result Return @see {@link HttpStatus#OK}, valid Meal with Light Discount.
     */
    @Test
    public void testCheckLotOfCheeseDiscount() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Meal standardMeal = new Meal();

        standardMeal.add(ingredientRepository.get("Queijo", true))
                .add(ingredientRepository.get("Queijo", true))
                .add(ingredientRepository.get("Queijo", true));

        ResponseEntity<Meal> entity = this.restTemplate.postForEntity("/snack/resources/order/check", standardMeal.getIngredients(), Meal.class, headers);

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).isInstanceOf(Meal.class);
        assertThat(entity.getBody().getPrice()).isNotNull();
        assertThat(entity.getBody().getDiscounts()).isNotNull();
        assertThat(((Discount) entity.getBody().getDiscounts().toArray()[0]).getId()).isEqualTo(discountRepository.get("Muito queijo").getId());
    }

    /**
     * Check user's meal with lot of meat discount.
     * Test '/order/check' @see {@link snack.services.OrderService#check(Set)} service against a Set of Ingredients with lot of meat discount @see {@link Discount}
     *
     * @result Return @see {@link HttpStatus#OK}, valid Meal with Light Discount.
     */
    @Test
    public void testCheckLotOfMeatDiscount() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Meal standardMeal = new Meal();

        standardMeal.add(ingredientRepository.get("Hambúrguer de carne", true))
                .add(ingredientRepository.get("Hambúrguer de carne", true))
                .add(ingredientRepository.get("Hambúrguer de carne", true));

        ResponseEntity<Meal> entity = this.restTemplate.postForEntity("/snack/resources/order/check", standardMeal.getIngredients(), Meal.class, headers);

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).isInstanceOf(Meal.class);
        assertThat(entity.getBody().getPrice()).isNotNull();
        assertThat(entity.getBody().getDiscounts()).isNotNull();
        assertThat(((Discount) entity.getBody().getDiscounts().toArray()[0]).getId()).isEqualTo(discountRepository.get("Muita carne").getId());
    }
}