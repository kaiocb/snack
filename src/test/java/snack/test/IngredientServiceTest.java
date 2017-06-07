package snack.test;

/**
 * Created by alien on 6/7/17.
 */

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
import snack.domain.Ingredient;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * TESTS FOR @see {@link snack.services.IngredientService}
 */

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class IngredientServiceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * GET list of ingredients.
     * Test '/meal/list' @see {@link snack.services.IngredientService#list()} return list of @see {@link snack.domain.Ingredient}
     *
     * @result Return @see {@link HttpStatus#OK}.
     */

    @Test
    public void testIngredientList() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<Ingredient[]> entity = this.restTemplate.getForEntity("/snack/resources/ingredient/list", Ingredient[].class);

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).isNotNull();
        assertThat(entity.getBody()).isNotEmpty();
    }
}
