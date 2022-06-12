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

    @PostMapping("/detail")
    public ResponseEntity addDetailUser(@RequestBody UserDetailDTO userDetailDTO){
        return userDetailService.addDetailUser(Optional.ofNullable(userDetailDTO));
    }

    @GetMapping("/finduser")
    public ResponseEntity findUsers(@RequestParam(value = "firstname", required=false) String firstName, @RequestParam(value = "secondname", required=false) String secondName, @RequestParam(value = "username", required=false) String username) throws JsonProcessingException {
        return userDetailService.findUsers(firstName,secondName,username);
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
