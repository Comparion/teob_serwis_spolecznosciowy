package pl.portalmylove.mylove.post;

import lombok.*;
import pl.portalmylove.mylove.user.User;

import javax.persistence.*;

/**Tworzonie encji reprezentujacej klase Post. Bedzie ona odwzorowywac tabele w bazie danych.  */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@ToString
@Table(name="posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NonNull
    private String body;
    @NonNull
    private String town;
    @NonNull
    private String subject;
    @NonNull
    @ManyToOne
    @JoinColumn(nullable = false, name="users_id")
    private User user;

}
