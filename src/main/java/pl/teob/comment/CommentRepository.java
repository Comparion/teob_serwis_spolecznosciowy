package pl.teob.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPostId(long postsId);
    int countByPostId(long id);
    List<Comment> findAllByUserId(long id);
}
