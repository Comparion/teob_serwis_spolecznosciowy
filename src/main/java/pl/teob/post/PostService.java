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

import java.util.ArrayList;
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

    public ResponseEntity addPost(PostDTO postDTO){
        Optional<User> userFromDB =  userRepository.findByUsername(postDTO.getUsername());
        if(userFromDB.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if(userFromDB.get().getEnabled() == false){
            return ResponseEntity.status(409).build();
        }

        Post newPost = new Post(postDTO.getBody(), postDTO.getTown(), postDTO.getSubject(), userFromDB.get());
        Post savedPost = postRepository.save(newPost);

        return ResponseEntity.ok("ok");
    }

//    public ResponseEntity getPosts() throws JsonProcessingException {
//        List<Post> posts = postRepository.findAll();
//        List<PostDTO> postDTOs = new ArrayList<>();
//
//        for(Post post: posts){
//            postDTOs.add(PostMapper.PostToPostDTO(post));
//        }
//        return ResponseEntity.ok(objectMapper.writeValueAsString(postDTOs));
//    }

    public ResponseEntity getPosts(String town, String subject) throws JsonProcessingException {
        List<Post> posts = postRepository.findAll();
        List<PostDTO> postDTOs = new ArrayList<>();

        for (Post post : posts) {
            if (town != null && subject != null) {
                if (post.getTown().equalsIgnoreCase(town) && post.getSubject().equalsIgnoreCase(subject))
                    postDTOs.add(PostMapper.PostToPostDTO(post));
            } else if (town != null) {
                if (post.getTown().equalsIgnoreCase(town))
                    postDTOs.add(PostMapper.PostToPostDTO(post));
            } else if (subject != null) {
                if (post.getSubject().equalsIgnoreCase(subject))
                    postDTOs.add(PostMapper.PostToPostDTO(post));
            } else
                postDTOs.add(PostMapper.PostToPostDTO(post));
        }

        return ResponseEntity.ok(objectMapper.writeValueAsString(postDTOs));
    }

//    public ResponseEntity updatePost(PostDTO postDTO) {
//        // TODO: sprawdzenie czy post edytuje wlasciciel
//
//        Post savedPost = postRepository.save(postDTO);
//        return ResponseEntity.ok(savedPost);
//    }


}
