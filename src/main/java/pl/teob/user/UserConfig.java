package pl.teob.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository){
        return args -> {
            User user1 = new User(
                    "damian1",
                    "dam123",
                    "damian@gmail.com",
                    AppUserRole.USER
            );

            User user2 = new User(
                    "patryk",
                    "pat123",
                    "patryk@o2.pl",
                    AppUserRole.USER
            );

            User user3 = new User(
                    "daniel",
                    "dan123",
                    "daniel@o2.pl",
                    AppUserRole.USER
            );

            User user4 = new User(
                    "kasia",
                    "kas123",
                    "kasia@wp.pl",
                    AppUserRole.USER
            );

            userRepository.saveAll(List.of(user1,user2,user3,user4));
        };
    }
}
