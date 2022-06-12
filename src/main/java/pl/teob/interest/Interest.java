package pl.teob.interest;

import lombok.*;
import pl.teob.post.Post;
import pl.teob.user.User;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@ToString
@Table(name="interested")
public class Interest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NonNull
    @ManyToOne
    @JoinColumn(nullable = false, name="posts_id")
    private Post post;
    @NonNull
    @ManyToOne
    @JoinColumn(nullable = false, name="users_id")
    private User user;

}
