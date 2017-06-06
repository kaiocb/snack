package snack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by alien on 6/5/17.
 */

/**
 * Snack Server snack.Application
 */

@SpringBootApplication
public class Application {

    /**
     * Starts spring application
     *
     * @param args
     * @throws Exception
     */

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
