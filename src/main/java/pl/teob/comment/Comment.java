package pl.teob.comment;

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
@Table(name="comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NonNull
    private String contents;
    @NonNull
    @ManyToOne
    @JoinColumn(nullable = false, name="posts_id")
    private Post post;
    @NonNull
    @ManyToOne
    @JoinColumn(nullable = false, name="users_id")
    private User user;
}
