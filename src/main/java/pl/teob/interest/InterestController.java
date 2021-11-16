package pl.teob.interest;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class InterestController {

    private final InterestService interestService;

    @Autowired
    public InterestController(InterestService interestService) {
        this.interestService = interestService;
    }

    @PostMapping("/interests")
    public ResponseEntity addInterest(@RequestBody InterestDTO interestDTO){
        return interestService.addInterest(interestDTO);
    }

    @GetMapping("/getinterests")
    public ResponseEntity getInterests(@RequestParam(value = "id") long idPost) throws JsonProcessingException {
        return interestService.getInterests(idPost);

    }
}
