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

    /**
     * Check user's order.
     * Test '/order/check' @see {@link snack.services.OrderService#check(Set)} service against a empty set of Ingredients @see {@link snack.domain.Ingredient}
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

    // TODO check valid return / value / integrity

}
