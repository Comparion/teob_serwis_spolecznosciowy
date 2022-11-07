package pl.portalmylove.mylove.detail;

import lombok.*;
import pl.portalmylove.mylove.user.User;

import javax.persistence.*;

/**Tworzonie encji reprezentujaca klase UserDetail. Bedzie ona odwzorowywac tabele w bazie danych.  */
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
    private String likes;
    private String description;
    private String profilePhotoURL;
    private Character gender;
    private Character lookingFor;
    @NonNull
    @OneToOne
    @JoinColumn(nullable = false, name="users_id")
    private User user;

}
