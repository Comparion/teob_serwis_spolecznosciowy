package pl.teob.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/users")
    public ResponseEntity getUsers() throws JsonProcessingException {
        return userService.getUsers();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/registration")
    public ResponseEntity addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/detail")
    public ResponseEntity addDetailUser(@RequestBody UserDetailDTO userDetailDTO){
        //final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        final String currentUserName = "patryk";
        return userService.addDetailUser(Optional.ofNullable(userDetailDTO), currentUserName);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "/registration/confirm")
    public String confirm(@RequestParam("token") String token) {
        return userService.confirmToken(token);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user) {
        return userService.login(user);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping(path = "/delete/{userId}")
    public ResponseEntity deleteUser(@PathVariable("userId") Long id) {
        return userService.deleteUser(id);
    }

}
