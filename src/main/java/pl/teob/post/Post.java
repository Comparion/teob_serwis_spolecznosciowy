package pl.teob.post;

import lombok.*;
import pl.teob.user.User;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name="posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String body;
    @NonNull
    @ManyToOne
    @JoinColumn(nullable = false, name="users_id")
    private User user;
}
