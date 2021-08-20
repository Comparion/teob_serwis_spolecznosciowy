package pl.teob.detail;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
public class UserDetailDTO {
    private String firstName;
    private String secondName;
    private Long numberPhone;
    private String interests;
    private String description;
    private String profilePhotoURL;
}
