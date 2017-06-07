package snack.configurations;

import org.glassfish.jersey.message.GZipEncoder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.EncodingFilter;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

/**
 * Created by alien on 6/5/17.
 */

@Configuration
@ApplicationPath("/snack/resources")
public class Jersey extends ResourceConfig {

    public Jersey() {
        packages(true, "snack.services");

        register(RolesAllowedDynamicFeature.class);
        register(CORSFilter.class);

        EncodingFilter.enableFor(this, GZipEncoder.class);
    }
}
