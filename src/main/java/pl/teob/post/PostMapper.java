package pl.teob.post;

public class PostMapper {
    public static PostDTO PostToPostDTO(Post post, Integer interests, boolean interestUser, Integer comments){
        return PostDTO.builder()
                .id(post.getId())
                .username(post.getUser().getUsername())
                .body(post.getBody())
                .town(post.getTown())
                .subject(post.getSubject())
                .interests(interests)
                .comments(comments)
                .interestUser(interestUser)
                .build();
    }
}
