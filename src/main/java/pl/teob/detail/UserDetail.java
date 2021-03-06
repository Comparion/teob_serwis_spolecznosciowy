package pl.teob.detail;

import lombok.*;
import pl.teob.user.User;

import javax.persistence.*;


@Table(name="user_detail")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String secondName;
    private String numberPhone;
    private String interests;
    private String description;
    private String profilePhotoURL;
    @NonNull
    @OneToOne
    @JoinColumn(nullable = false, name="users_id")
    private User user;

}
