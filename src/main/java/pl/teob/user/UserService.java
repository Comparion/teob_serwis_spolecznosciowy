package pl.teob.user;

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
import pl.teob.user.token.ConfirmationToken;
import pl.teob.user.token.ConfirmationTokenService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final String USER_NOT_FOUND = "User with email %s not found";

    @Autowired
    UserRepository userRepository;

    @Autowired
    ObjectMapper objectMapper;

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

        User savedUser = userRepository.save(user);
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), user);
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return ResponseEntity.ok(savedUser);
    }

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public ResponseEntity login(User user) {
        Optional<User> userFromDb = userRepository.findByUsername(user.getUsername());

        if (userFromDb.isEmpty() || wrongPassword(userFromDb, user)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok().build();
    }

    private boolean wrongPassword(Optional<User> userFromDb, User user) {
        return !userFromDb.get().getPassword().equals(user.getPassword());
    }

    public ResponseEntity deleteUser(Integer userId) {
        Optional<User> userIdDB = userRepository.findById(userId);
        if(userIdDB.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        userRepository.deleteById(userId);
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
