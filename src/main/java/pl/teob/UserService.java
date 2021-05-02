package pl.teob;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class UserService {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Autowired
    UserRepository userRepository;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("/users")
    public ResponseEntity getUsers() throws JsonProcessingException {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(objectMapper.writeValueAsString(users));
    }

    @PostMapping("/users")
    public ResponseEntity addUser(@RequestBody User user){
        Optional<User> userUsernameDB = userRepository.findByUsername(user.getUsername());
        Optional<User> userEmailDB = userRepository.findByEmail(user.getEmail());
        Matcher matcher;

        if(!userUsernameDB.isEmpty() || !userEmailDB.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        if(user.getPassword().isEmpty() || !validate(user.getEmail())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }


        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
}
