package pl.teob.detail;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailDTO {
    private String firstname;
    private String secondname;
    private Long numberPhone;
    private String interests;
    private String description;
    private String profilePhotoURL;
}
