package pl.portalmylove.mylove.post;

/** Klasa sluzy do tego, aby w odpowiedni sposob przerobic dane trafiajace do przegladarki*/
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
