package pl.portalmylove.mylove.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/** Klasa odpowiedzialna za obsluge zadan */
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity getUsers() throws JsonProcessingException {
        return userService.getUsers();
    }

    @PostMapping("/registration")
    public ResponseEntity addUser(@RequestBody User user){

        return userService.addUser(user);
    }

    @GetMapping(path = "/registration/confirm")
    public String confirm(@RequestParam("token") String token) {
        return userService.confirmToken(token);
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user) {
        return userService.login(user);
    }

    @DeleteMapping(path = "/delete/{username}")
    public ResponseEntity deleteUser(@PathVariable("username") String username) {
        return userService.deleteUser(username);
    }

}
