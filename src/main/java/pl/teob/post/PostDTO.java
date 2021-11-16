package pl.teob.post;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private long id;
    private long interests;
    private String username;
    private String body;
    private String town;
    private String subject;
}
