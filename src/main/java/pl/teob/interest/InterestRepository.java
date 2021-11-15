package pl.teob.interest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.teob.post.Post;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface InterestRepository extends JpaRepository<Interest, Long> {

    //Optional<Interest> findByUserId(long usersId);
    //Optional<Interest> findByPostId(long postsId);
    Optional<Interest> findAllByPostIdAndUserId(long postsId, long usersId);

//    @Transactional
//    @Modifying
//    @Query("select i from Interest i where i.posts_id = ?1 and i.users_id = ?2")
//    Interest findByPostUser(long post_id, long user_id);
}
