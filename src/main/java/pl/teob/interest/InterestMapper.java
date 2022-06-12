package pl.teob.interest;

import pl.teob.detail.UserDetail;

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
