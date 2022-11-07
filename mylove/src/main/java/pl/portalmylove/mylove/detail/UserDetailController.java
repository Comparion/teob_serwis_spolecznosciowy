package pl.portalmylove.mylove.detail;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
/** Klasa odpowiedzialna za obsluge zadan */
@RestController
public class UserDetailController {
    private final UserDetailService userDetailService;

    @Autowired
    public UserDetailController(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @PostMapping("/detail")
    public ResponseEntity addDetailUser(@RequestBody UserDetailDTO userDetailDTO){
        return userDetailService.addDetailUser(Optional.ofNullable(userDetailDTO));
    }

    @GetMapping("/finduser")
    public ResponseEntity findUsers(@RequestParam(value = "likes", required=false) String likes, @RequestParam(value = "username", required=false) String username) throws JsonProcessingException {
        return userDetailService.findUsers(likes,username);
    }

    @GetMapping("/suggestionsuser")
    public ResponseEntity suggestionsUsers(@RequestParam(value = "username", required=false) String username) throws JsonProcessingException {
        return userDetailService.suggestionsUsers(username);
    }


    @GetMapping("/detail/{username}")
    public ResponseEntity getDetail(@PathVariable("username") String username) throws JsonProcessingException {
        return userDetailService.getDetail(username);
    }

    @PutMapping("/updatedetail/{username}")
    public ResponseEntity updateDetailUser(@PathVariable("username") String username, @RequestBody UserDetailDTO userDetailDTO){
        return userDetailService.updateDetailUser(Optional.ofNullable(userDetailDTO), username);
    }
}
