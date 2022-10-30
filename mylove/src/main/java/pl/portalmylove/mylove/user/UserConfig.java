package pl.portalmylove.mylove.user;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.portalmylove.mylove.detail.UserDetail;
import pl.portalmylove.mylove.detail.UserDetailRepository;
import pl.portalmylove.mylove.post.Post;
import pl.portalmylove.mylove.post.PostRepository;
import pl.portalmylove.mylove.user.token.ConfirmationToken;
import pl.portalmylove.mylove.user.token.ConfirmationTokenService;

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

            User user5 = new User(
                    "danio",
                    bCryptPasswordEncoder.encode("dan123"),
                    "daniel12@o2.pl",
                    AppUserRole.USER
            );

            userRepository.saveAll(List.of(user1,user2,user3,user4, user5));
            String token = UUID.randomUUID().toString();
            ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), user2);
            confirmationTokenService.saveConfirmationToken(confirmationToken);
            userService.confirmToken(token);

            String token2 = UUID.randomUUID().toString();
            ConfirmationToken confirmationToken2 = new ConfirmationToken(token2, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), user3);
            confirmationTokenService.saveConfirmationToken(confirmationToken2);
            userService.confirmToken(token2);

            Post post1 = new Post(
                    "Czesc poszukuje wysokiej blondynki o niebieskich oczach",
                    "Kielce",
                    "Poszukuje kobiety",
                    user2
            );

            Post post2 = new Post(
                    "Czesc, to znowu ja, brunetki też jednak wchodzą w grę!",
                    "Kielce",
                    "Rownież brunetki",
                    user2
            );

            Post post3 = new Post(
                    "Poszukuje dziewczyny z którą mógłby grać w szachy!",
                    "Kielce",
                    "szachy",
                    user2
            );

            Post post4 = new Post(
                    "Może jakaś kobieta jest chętna na wyjazd do Zakopanego?",
                    "Warszawa",
                    "Wycieczka - poszukuje dziewczyny",
                    user3
            );

            Post post5 = new Post(
                    "Poszukuje mężczyzny umiejącego dobrze tańczyć.",
                    "Kielce",
                    "Wesele",
                    user4
            );

            UserDetail userDetail1 = new UserDetail(
                    12,
                    "Patryk",
                    "Kowalski",
                    null,
                    "sport, kobiety, blondynki",
                    "jestem Patryk",
                    "https://www.lego.com/cdn/cs/set/assets/blt81f6da976343c245/Hulk-Sidekick-Tall-1.jpg?fit=crop&format=jpg&quality=80&width=800&height=600&dpr=1",
                    'M',
                    'K',
                    user2
            );

            UserDetail userDetail2 = new UserDetail(
                    123,
                    "Daniel",
                    "Owsiak",
                    "888777666",
                    "filmy, brunetki, sport",
                    "jestem Daniel",
                    "https://bi.im-g.pl/im/e3/12/14/z21048035IBG.jpg",
                    'K',
                    ' ',
                    user3
            );

            UserDetail userDetail3 = new UserDetail(
                    1234,
                    "Daniel",
                    "Student",
                    "888666555",
                    "Piłka",
                    "jestem Daniel",
                    "https://www.focus.pl/media/cache/big/uploads/media/default/0001/29/madry-jak-kura.jpeg",
                    'M',
                    'K',
                    user5
            );

            UserDetail userDetail4 = new UserDetail(
                    1234,
                    "Kasia",
                    "Owsiak",
                    "8886655",
                    "Taniec, Kino, sport",
                    "Lubie balet",
                    "https://i.wpimg.pl/730x0/m.fotoblogia.pl/pexels-photo-227691-12768d02fe73.jpg",
                    'K',
                    'M',
                    user4
            );

            //System.out.println(post2.toString());

            postRepository.saveAll(List.of(post1, post2, post3, post4, post5));
            userDetailRepository.saveAll(List.of(userDetail1, userDetail2, userDetail3, userDetail4));


        };
    }
}