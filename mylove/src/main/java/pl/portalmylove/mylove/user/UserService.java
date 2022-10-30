package pl.portalmylove.mylove.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.portalmylove.mylove.detail.UserDetail;
import pl.portalmylove.mylove.detail.UserDetailRepository;
import pl.portalmylove.mylove.post.Post;
import pl.portalmylove.mylove.post.PostRepository;
import pl.portalmylove.mylove.user.token.ConfirmationToken;
import pl.portalmylove.mylove.user.token.ConfirmationTokenRepository;
import pl.portalmylove.mylove.user.token.ConfirmationTokenService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final String USER_NOT_FOUND = "User with email %s not found";

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final ObjectMapper objectMapper;

    @Autowired
    private final ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private final UserDetailRepository userDetailRepository;

    @Autowired
    private final PostRepository postRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    public ResponseEntity getUsers() throws JsonProcessingException {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(objectMapper.writeValueAsString(users));
    }


    public ResponseEntity addUser(User user){
        Optional<User> userUsernameDB = userRepository.findByUsername(user.getUsername());
        Optional<User> userEmailDB = userRepository.findByEmail(user.getEmail());

        if(!userUsernameDB.isEmpty() || !userEmailDB.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        if(user.getPassword().isEmpty() || !validate(user.getEmail())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }


        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setAppUserRole(AppUserRole.USER);
        user.setEnabled(true);

        User savedUser = userRepository.save(user);
        UserDetail userDetail = new UserDetail(
                123,
                "",
                "",
                "",
                "",
                "",
                "https://ocdn.eu/pulscms-transforms/1/OkBk9kqTURBXy9lMDg4NjY3NWNhNWQzNTI2MzY5MDhlOGEyOGVlYzllMy5qcGVnkZUCzQMUAMLDgaEwBQ",
                ' ',
                ' ',
                user);
        userDetailRepository.save(userDetail);
        return ResponseEntity.ok("ok");
    }

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public ResponseEntity login(User user) {
        Optional<User> userFromDb = userRepository.findByUsername(user.getUsername());

        if (userFromDb.isEmpty() || !wrongPassword(userFromDb, user)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok("ok");
    }

    private boolean wrongPassword(Optional<User> userFromDb, User user) {
        return bCryptPasswordEncoder.matches(user.getPassword(),userFromDb.get().getPassword());

    }

    public ResponseEntity deleteUser(String username) {
        Optional<User> userDB = userRepository.findByUsername(username);
        if(userDB.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Optional<ConfirmationToken> tokenDB = confirmationTokenRepository.findByUserId(userDB.get().getId());
        if(!tokenDB.isEmpty()){
            confirmationTokenRepository.deleteById(tokenDB.get().getId());
        }

        List<Post> postDB = postRepository.findAllByUserId(userDB.get().getId());
        if(!postDB.isEmpty()){
            for(Post post: postDB) {
                postRepository.deleteById(post.getId());
            }
        }

        Optional<UserDetail> userDetailsDB = userDetailRepository.findByUserId(userDB.get().getId());
        if(!userDetailsDB.isEmpty()){
           userDetailRepository.deleteById(userDetailsDB.get().getId());
        }

        userRepository.deleteById(userDB.get().getId());
        return ResponseEntity.ok().build();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiredAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        userRepository.enableAppUser(confirmationToken.getUser().getEmail());
        return "confirmed";
    }
}
