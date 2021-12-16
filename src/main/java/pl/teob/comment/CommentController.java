package pl.teob.comment;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.teob.interest.InterestDTO;
import pl.teob.interest.InterestService;

@RestController
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comments")
    public ResponseEntity addComment(@RequestBody CommentDTO commentDTO){
        return commentService.addComment(commentDTO);
    }

    @GetMapping("/getcomments")
    public ResponseEntity getComment(@RequestParam(value = "idPost") long idPost) throws JsonProcessingException {
        return commentService.getComments(idPost);

    }
}
