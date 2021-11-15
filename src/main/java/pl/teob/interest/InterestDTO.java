package pl.teob.interest;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InterestDTO {
    private long idPost;
    private String username;
}
