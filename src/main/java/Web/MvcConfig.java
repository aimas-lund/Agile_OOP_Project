package Web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/doctor").setViewName("doctor");
        registry.addViewController("/nurse").setViewName("nurse");
        registry.addViewController("/clerk").setViewName("clerk");
        registry.addViewController("/ict").setViewName("ict");
        registry.addViewController("/login").setViewName("login");
    }

}