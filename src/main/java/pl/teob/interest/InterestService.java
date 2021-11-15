package pl.teob.interest;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.teob.post.Post;
import pl.teob.post.PostRepository;
import pl.teob.user.User;
import pl.teob.user.UserRepository;

import java.util.Optional;

@Service
public class InterestService {

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    private final InterestRepository interestRepository;

    private final ObjectMapper objectMapper;

    @Autowired
    public InterestService(PostRepository postRepository, UserRepository userRepository, InterestRepository interestRepository, ObjectMapper objectMapper) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.interestRepository = interestRepository;
        this.objectMapper = objectMapper;
    }

    public ResponseEntity addInterest(InterestDTO interestDTO){
        Optional<User> userFromDB =  userRepository.findByUsername(interestDTO.getUsername());
        if(userFromDB.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if(userFromDB.get().getEnabled() == false){
            return ResponseEntity.status(409).build();
        }

        Optional<Post> postFromDB =  postRepository.findById(interestDTO.getIdPost());
        if(userFromDB.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        //Optional<Interest> interestFromDB = Optional.ofNullable(interestRepository.findByPostUser(postFromDB.get().getId(), userFromDB.get().getId()));
        //Optional<Interest> interestFromDB = interestRepository.findByUserId(userFromDB.get().getId());
        //Optional<Interest> interestFromDB = interestRepository.findByPostId(postFromDB.get().getId());
        Optional<Interest> interestFromDB = interestRepository.findAllByPostIdAndUserId(postFromDB.get().getId(), userFromDB.get().getId());

        if(interestFromDB.isEmpty()){
            Interest newInterest = new Interest(postFromDB.get(), userFromDB.get());
            interestRepository.save(newInterest);
            return ResponseEntity.ok("ok");
        } else {
            interestRepository.deleteById(interestFromDB.get().getId());
            return ResponseEntity.ok("delete");
        }




    }

}
