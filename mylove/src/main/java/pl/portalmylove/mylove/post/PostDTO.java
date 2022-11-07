package pl.portalmylove.mylove.post;

import lombok.*;

/**Klasa ktora pozwala przesylac na frontend obiekty, ktore nie zawieraja w sobie nadmiarowych danych lub takich, ktore moglyby zagrazac bezpieczenstwu */

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
