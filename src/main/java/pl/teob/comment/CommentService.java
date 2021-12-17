package pl.teob.comment;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.teob.detail.UserDetailRepository;
import pl.teob.interest.Interest;
import pl.teob.interest.InterestDTO;
import pl.teob.interest.InterestMapper;
import pl.teob.interest.InterestRepository;
import pl.teob.post.Post;
import pl.teob.post.PostRepository;
import pl.teob.user.User;
import pl.teob.user.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    private final CommentRepository commentRepository;

    private final UserDetailRepository userDetailRepository;

    private final ObjectMapper objectMapper;

    @Autowired
    public CommentService(PostRepository postRepository, UserRepository userRepository, CommentRepository commentRepository, UserDetailRepository userDetailRepository, ObjectMapper objectMapper) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.userDetailRepository = userDetailRepository;
        this.objectMapper = objectMapper;
    }

    public ResponseEntity addComment(CommentDTO commentDTO){
        Optional<User> userFromDB =  userRepository.findByUsername(commentDTO.getUsername());
        if(userFromDB.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if(userFromDB.get().getEnabled() == false){
            return ResponseEntity.status(409).build();
        }

        Optional<Post> postFromDB =  postRepository.findById(commentDTO.getIdPost());
        if(postFromDB.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Comment comment = new Comment(commentDTO.getContents(),postFromDB.get(), userFromDB.get());
        commentRepository.save(comment);
        return ResponseEntity.ok("ok");
    }

    public ResponseEntity getComments(long idPost) throws JsonProcessingException {
        List<Comment> comments= commentRepository.findAllByPostId(idPost);
        List<CommentDTO> commentDTOS = new ArrayList<>();
        for(Comment comment: comments){
            commentDTOS.add(CommentMapper.CommentToCommentDTO(comment, userDetailRepository.findByUserUsername(comment.getUser().getUsername())));
        }
        return ResponseEntity.ok(objectMapper.writeValueAsString(commentDTOS));
    }

    public ResponseEntity deleteComment(long idComment) {
        commentRepository.deleteById(idComment);
        return ResponseEntity.ok("ok");
    }
}
