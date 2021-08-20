package pl.teob.post;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import pl.teob.user.User;
import pl.teob.user.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    private final ObjectMapper objectMapper;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository, ObjectMapper objectMapper) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/posts")
    public ResponseEntity addPost(PostDTO postDTO, String currentUsername){
        Optional<User> userFromDB =  userRepository.findByUsername(currentUsername);
        if(userFromDB.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if(userFromDB.get().getEnabled() == false){
            return ResponseEntity.status(409).build();
        }

        // TODO: posty może dodawać użytkownik który potwierdził adres emial
        Post newPost = new Post(postDTO.getBody(), postDTO.getTown(), postDTO.getSubject(), userFromDB.get());
        Post savedPost = postRepository.save(newPost);

        return ResponseEntity.ok(savedPost);
    }

    public ResponseEntity getPosts() throws JsonProcessingException {
        List<Post> posts = postRepository.findAll();
        return ResponseEntity.ok(objectMapper.writeValueAsString(posts));
    }
}
