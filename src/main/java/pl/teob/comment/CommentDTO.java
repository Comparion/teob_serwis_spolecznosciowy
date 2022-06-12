package pl.teob.comment;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private long idComment;
    private long idPost;
    private String username;
    private String firstName;
    private String secondName;
    private String contents;
}
