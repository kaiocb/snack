package snack.services;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by alien on 6/5/17.
 */

@Component
@Path("/status")
@Produces(MediaType.APPLICATION_JSON)
public class StatusService {

    @GET
    public String health() {
        return "Jersey: Up and Running!";
    }
}