package pl.teob.post;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import pl.teob.user.User;
import pl.teob.user.UserRepository;

import java.util.Optional;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/posts")
    public ResponseEntity addPost(String username, String postBody){
        Optional<User> userFromDB =  userRepository.findByUsername(username);
        if(userFromDB.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Post post = new Post(postBody, userFromDB.get());
        Post savedPost = postRepository.save(post);

        return ResponseEntity.ok(savedPost);
    }
}
