package pl.teob.interest;

import pl.teob.post.Post;
import pl.teob.post.PostDTO;

public class InterestMapper {
    public static InterestDTO InterestToInterestDTO(Interest interest){
        return InterestDTO.builder()
                .idPost(interest.getPost().getId())
                .username(interest.getUser().getUsername())
                .build();
    }
}
