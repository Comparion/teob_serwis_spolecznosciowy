package pl.teob.post;

import lombok.*;
import pl.teob.interest.InterestDTO;

import java.util.List;

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
    //private List<InterestDTO> interests;
    private long interests;
    private boolean interestUser;
}
