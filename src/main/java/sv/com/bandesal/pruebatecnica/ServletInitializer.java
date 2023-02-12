package sv.com.bandesal.pruebatecnica;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PruebaTecnicaApplication.class);
    }

    private static Class<PruebaTecnicaApplication> applicationClass = PruebaTecnicaApplication.class;
}

