package pl.teob.post;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    //@CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/posts")
    public ResponseEntity addPost(@RequestBody PostDTO postDTO){
        //final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        final String currentUserName = "patryk";
        return postService.addPost(postDTO, currentUserName);
    }

//    @CrossOrigin(origins = "http://localhost:4200")
//    @PutMapping("/postupdate")
//    public ResponseEntity updatePost(@RequestBody PostDTO postDTO){
//        return postService.updatePost(postDTO);
//    }

    //@CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getposts")
    public ResponseEntity getUsers() throws JsonProcessingException {
        return postService.getPosts();

    }
//    public ResponseEntity addPost(@RequestHeader("username") String username, @RequestBody String postBody){
//        return postService.addPost(username, postBody);
//        final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
//    }


}
