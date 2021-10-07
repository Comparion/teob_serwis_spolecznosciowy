package pl.teob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/users").allowedOrigins("http://localhost:4200");
//                registry.addMapping("/login").allowedOrigins("http://localhost:4200");
//                registry.addMapping("/posts").allowedOrigins("http://localhost:4200");
//                registry.addMapping("/registration").allowedOrigins("http://localhost:4200");
//                registry.addMapping("/delete/{userId}").allowedOrigins("http://localhost:4200");
//                registry.addMapping("/registration/confirm").allowedOrigins("http://localhost:4200");
//            }
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }
}
