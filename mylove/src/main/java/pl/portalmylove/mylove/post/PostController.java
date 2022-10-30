package pl.portalmylove.mylove.post;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.portalmylove.mylove.detail.UserDetailDTO;

import java.util.Optional;

@RestController
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/posts")
    public ResponseEntity addPost(@RequestBody PostDTO postDTO){
        return postService.addPost(postDTO);
    }

    @GetMapping("/posts")
    public ResponseEntity getPosts(@RequestParam(value = "username", required=false) String username, @RequestParam(value = "town",
            required=false) String town, @RequestParam(value = "subject", required=false) String subject) throws JsonProcessingException {
        return postService.getPosts(username, town, subject);
    }

    @PutMapping("/updatepost")
    public ResponseEntity updatepost(@RequestBody PostDTO postDTO){
        return postService.updatePost(Optional.ofNullable(postDTO));
    }
}
