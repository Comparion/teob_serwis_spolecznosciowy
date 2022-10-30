package pl.portalmylove.mylove.post;

public class PostMapper {
    public static PostDTO PostToPostDTO(Post post, Integer interests, boolean interestUser){
        return PostDTO.builder()
                .id(post.getId())
                .username(post.getUser().getUsername())
                .body(post.getBody())
                .town(post.getTown())
                .subject(post.getSubject())
                .interests(interests)
                .interestUser(interestUser)
                .build();
    }
}