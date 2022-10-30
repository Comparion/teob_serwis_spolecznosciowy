package pl.portalmylove.mylove.post;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.portalmylove.mylove.detail.UserDetail;
import pl.portalmylove.mylove.user.User;
import pl.portalmylove.mylove.user.UserRepository;

import java.util.*;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    private final ObjectMapper objectMapper;


    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository,ObjectMapper objectMapper) {
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
        postRepository.save(newPost);

        return ResponseEntity.ok("ok");
    }

    public ResponseEntity getPosts(String username,String town, String subject) throws JsonProcessingException {
        List<Post> posts = postRepository.findAll();
        List<PostDTO> postDTOs = new ArrayList<>();
        boolean interestUser;
        int countInterests = 0;
        for (Post post : posts) {
            interestUser = false;
            if (town != null && subject != null) {
                if (post.getTown().equalsIgnoreCase(town) && post.getSubject().equalsIgnoreCase(subject))
                    postDTOs.add(PostMapper.PostToPostDTO(post, countInterests, interestUser));
            } else if (town != null) {
                if (post.getTown().equalsIgnoreCase(town))
                    postDTOs.add(PostMapper.PostToPostDTO(post, countInterests, interestUser));
            } else if (subject != null) {
                if (post.getSubject().equalsIgnoreCase(subject))
                    postDTOs.add(PostMapper.PostToPostDTO(post, countInterests, interestUser));
            } else
                postDTOs.add(PostMapper.PostToPostDTO(post, countInterests, interestUser));
        }
        Collections.reverse(postDTOs);
        return ResponseEntity.ok(objectMapper.writeValueAsString(postDTOs));
    }

    public ResponseEntity updatePost(Optional<PostDTO> postDTO) {
        Optional<Post> postDB = postRepository.findById(postDTO.get().getId());
        if(postDB.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if(!Objects.isNull(postDTO.get().getBody())){
            postDB.get().setBody("");
        }
        if(!Objects.isNull(postDTO.get().getSubject())){
            postDB.get().setSubject("");
        }
        if(!Objects.isNull(postDTO.get().getTown())){
            postDB.get().setTown("");
        }

        postRepository.updatePostById(postDTO.get().getTown(), postDTO.get().getSubject(), postDTO.get().getBody(), postDB.get().getId());

        return ResponseEntity.ok("ok");
    }
}
