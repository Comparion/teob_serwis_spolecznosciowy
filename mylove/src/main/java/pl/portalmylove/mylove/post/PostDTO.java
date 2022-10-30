package pl.portalmylove.mylove.post;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private long id;
    private String username;
    private String body;
    private String town;
    private String subject;
    private long interests;
    private boolean interestUser;
}
