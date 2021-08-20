package pl.teob.detail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserDetailController {
    private final UserDetailService userDetailService;

    @Autowired
    public UserDetailController(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/detail")
    public ResponseEntity addDetailUser(@RequestBody UserDetailDTO userDetailDTO){
        //final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        final String currentUserName = "patryk";
        return userDetailService.addDetailUser(Optional.ofNullable(userDetailDTO), currentUserName);
    }
}
