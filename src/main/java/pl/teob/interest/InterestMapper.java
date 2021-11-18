package pl.teob.interest;

import pl.teob.detail.UserDetail;
import pl.teob.post.Post;
import pl.teob.post.PostDTO;

public class InterestMapper {
    public static InterestDTO InterestToInterestDTO(Interest interest, UserDetail userDetail){
        return InterestDTO.builder()
                .idPost(interest.getPost().getId())
                .username(interest.getUser().getUsername())
                .firstName(userDetail.getFirstName())
                .secondName(userDetail.getSecondName())
                .build();
    }
}
