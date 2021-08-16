package pl.teob.user;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.teob.user.token.ConfirmationToken;
import pl.teob.user.token.ConfirmationTokenService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Configuration
public class UserConfig {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final UserService userService;

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository){
        return args -> {
            User user1 = new User(
                    "damian1",
                    bCryptPasswordEncoder.encode("dam123"),
                    "damian@gmail.com",
                    AppUserRole.USER
            );

            User user2 = new User(
                    "patryk",
                    bCryptPasswordEncoder.encode("pat123"),
                    "patryk@o2.pl",
                    AppUserRole.USER
            );

            User user3 = new User(
                    "daniel",
                    bCryptPasswordEncoder.encode("dan123"),
                    "daniel@o2.pl",
                    AppUserRole.USER
            );

            User user4 = new User(
                    "kasia",
                    bCryptPasswordEncoder.encode("kas123"),
                    "kasia@wp.pl",
                    AppUserRole.USER
            );

            userRepository.saveAll(List.of(user1,user2,user3,user4));
            String token = UUID.randomUUID().toString();
            ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), user2);
            confirmationTokenService.saveConfirmationToken(confirmationToken);
            userService.confirmToken(token);
        };
    }
}
