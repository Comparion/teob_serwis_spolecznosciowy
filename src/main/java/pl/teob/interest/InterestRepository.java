package pl.teob.interest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InterestRepository extends JpaRepository<Interest, Long> {

    //Optional<Interest> findByUserId(long usersId);
    //Optional<Interest> findByPostId(long postsId);
    Optional<Interest> findAllByPostIdAndUserId(long postsId, long usersId);
    List<Interest> findAllByPostId(long postsId);
    int countByPostId(long id);

    List<Interest> findAllByUserId(long id);

//    @Transactional
//    @Modifying
//    @Query("select i from Interest i where i.posts_id = ?1 and i.users_id = ?2")
//    Interest findByPostUser(long post_id, long user_id);
}
