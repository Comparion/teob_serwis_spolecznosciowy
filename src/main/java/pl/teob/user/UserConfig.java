package pl.teob.user;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.teob.detail.UserDetail;
import pl.teob.detail.UserDetailRepository;
import pl.teob.post.Post;
import pl.teob.post.PostRepository;
import pl.teob.post.PostService;
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
    private final PostRepository postRepository;
    private final UserDetailRepository userDetailRepository;

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
                    "kasia@o2.pl",
                    AppUserRole.USER
            );

            userRepository.saveAll(List.of(user1,user2,user3,user4));
            String token = UUID.randomUUID().toString();
            ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), user2);
            confirmationTokenService.saveConfirmationToken(confirmationToken);
            userService.confirmToken(token);

            String token2 = UUID.randomUUID().toString();
            ConfirmationToken confirmationToken2 = new ConfirmationToken(token2, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), user3);
            confirmationTokenService.saveConfirmationToken(confirmationToken2);
            userService.confirmToken(token2);

            Post post1 = new Post(
                    "Czesc poszukuje kogos do biegania",
                    "Kielce",
                    "Bieganie",
                    user2
            );

            Post post2 = new Post(
                    "To mój drugi post na tym serwisie!",
                    "Warszawa",
                    "Jazda na Rowerze",
                    user2
            );

            Post post3 = new Post(
                    "Tym razem szachy",
                    "Kielce",
                    "szachy",
                    user2
            );

            UserDetail userDetail1 = new UserDetail(
                    1234,
                    "Patryk",
                    "Owsiak",
                    null,
                    "sport",
                    "jestem Patryk",
                    "https://www.lego.com/cdn/cs/set/assets/blt81f6da976343c245/Hulk-Sidekick-Tall-1.jpg?fit=crop&format=jpg&quality=80&width=800&height=600&dpr=1",
                    user2
            );

            //System.out.println(post2.toString());

            postRepository.saveAll(List.of(post1, post2, post3));
            userDetailRepository.save(userDetail1);

        };
    }
}
