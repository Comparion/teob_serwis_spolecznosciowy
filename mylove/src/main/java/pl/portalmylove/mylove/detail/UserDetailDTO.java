package pl.portalmylove.mylove.detail;

import lombok.*;

/**Klasa ktora pozwala przesylac na frontend obiekty, ktore nie zawieraja w sobie nadmiarowych danych lub takich, ktore moglyby zagrazac bezpieczenstwu */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailDTO {
    private String firstName;
    private String secondName;
    private String numberPhone;
    private String likes;
    private String description;
    private String profilePhotoURL;
    private String username;
    private Character gender;
    private Character lookingFor;
}
