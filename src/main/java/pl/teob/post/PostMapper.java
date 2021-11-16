package pl.teob.post;

public class PostMapper {
    public static PostDTO PostToPostDTO(Post post, int countInterests){
        return PostDTO.builder()
                .id(post.getId())
                .interests(countInterests)
                .username(post.getUser().getUsername())
                .body(post.getBody())
                .town(post.getTown())
                .subject(post.getSubject())
                .build();
    }
}
