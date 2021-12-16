package pl.teob.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.teob.interest.Interest;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

//    Optional<Interest> findAllByPostIdAndUserId(long postsId, long usersId);
    List<Comment> findAllByPostId(long postsId);
//    int countByPostId(long id);
//    List<Interest> findAllByUserId(long id);

}
