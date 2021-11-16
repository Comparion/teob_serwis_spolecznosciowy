package pl.teob.detail;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailDTO {
    private String firstName;
    private String secondName;
    private String numberPhone;
    private String interests;
    private String description;
    private String profilePhotoURL;
    private String username;
}
