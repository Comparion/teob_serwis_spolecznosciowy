package pl.portalmylove.mylove.detail;

import lombok.*;

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
