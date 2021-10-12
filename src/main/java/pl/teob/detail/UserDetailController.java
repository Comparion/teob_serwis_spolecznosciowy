package pl.teob.detail;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserDetailController {
    private final UserDetailService userDetailService;

    @Autowired
    public UserDetailController(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    //@CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/detail")
    public ResponseEntity addDetailUser(@RequestBody UserDetailDTO userDetailDTO){
        //final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        final String currentUserName = "patryk";
        return userDetailService.addDetailUser(Optional.ofNullable(userDetailDTO), currentUserName);
    }

    @GetMapping("/detail/{username}")
    public ResponseEntity getDetail(@PathVariable("username") String username) throws JsonProcessingException {
        return userDetailService.getDetail(username);
    }
}
