package kr.co.haesungds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ECocApplication extends SpringBootServletInitializer { /* extends SpringBootServletInitializer*/

    public static void main(String[] args) {
        SpringApplication.run(ECocApplication.class, args);
        //new SpringApplicationBuilder(HsqApplication.class)
        //        //.web(WebApplicationType.NONE)
        //        .build()
        //        .run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ECocApplication.class);
    }
}
