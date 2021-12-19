package pl.teob.post;

import pl.teob.interest.InterestDTO;

import java.util.List;

public class PostMapper {
    public static PostDTO PostToPostDTO(Post post, Integer interests, boolean interestUser, Integer comments/*, List<InterestDTO> interestDTOList*/){
        return PostDTO.builder()
                .id(post.getId())
                .username(post.getUser().getUsername())
                .body(post.getBody())
                .town(post.getTown())
                .subject(post.getSubject())
                .interests(interests)
                .comments(comments)
                .interestUser(interestUser)
                //.interests(interestDTOList)
                .build();
    }
}
