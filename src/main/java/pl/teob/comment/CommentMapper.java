package pl.teob.comment;

import pl.teob.detail.UserDetail;
import pl.teob.interest.Interest;
import pl.teob.interest.InterestDTO;

public class CommentMapper {
    public static CommentDTO CommentToCommentDTO(Comment comment, UserDetail userDetail){
        return CommentDTO.builder()
                .idComment(comment.getId())
                .idPost(comment.getPost().getId())
                .username(comment.getUser().getUsername())
                .firstName(userDetail.getFirstName())
                .secondName(userDetail.getSecondName())
                .contents(comment.getContents())
                .build();
    }
}
