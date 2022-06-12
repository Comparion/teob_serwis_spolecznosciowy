package pl.teob.post;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.teob.comment.CommentRepository;
import pl.teob.interest.Interest;
import pl.teob.interest.InterestRepository;
import pl.teob.user.User;
import pl.teob.user.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final InterestRepository interestRepository;
    private final ObjectMapper objectMapper;
    private final CommentRepository commentRepository;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository,InterestRepository interestRepository,
                       CommentRepository commentRepository,ObjectMapper objectMapper) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.interestRepository = interestRepository;
        this.commentRepository = commentRepository;
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
        List<Interest> interests;
        boolean interestUser;
        int countInterests = 0;
        int countComments = 0;
        for (Post post : posts) {
            countInterests = interestRepository.countByPostId(post.getId());
            countComments = commentRepository.countByPostId(post.getId());
            interests = interestRepository.findAllByPostId(post.getId());
            interestUser = false;
            for(Interest interest : interests){
                if(interest.getUser().getUsername().equals(username))
                    interestUser = true;
            }
            if (town != null && subject != null) {
                if (post.getTown().equalsIgnoreCase(town) && post.getSubject().equalsIgnoreCase(subject))
                    postDTOs.add(PostMapper.PostToPostDTO(post, countInterests, interestUser, countComments));
            } else if (town != null) {
                if (post.getTown().equalsIgnoreCase(town))
                    postDTOs.add(PostMapper.PostToPostDTO(post, countInterests, interestUser, countComments));
            } else if (subject != null) {
                if (post.getSubject().equalsIgnoreCase(subject))
                    postDTOs.add(PostMapper.PostToPostDTO(post, countInterests, interestUser, countComments));
            } else
                postDTOs.add(PostMapper.PostToPostDTO(post, countInterests, interestUser, countComments));
        }
        Collections.reverse(postDTOs);
        return ResponseEntity.ok(objectMapper.writeValueAsString(postDTOs));
    }
}
