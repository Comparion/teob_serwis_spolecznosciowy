package pl.teob.interest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InterestRepository extends JpaRepository<Interest, Long> {

    Optional<Interest> findAllByPostIdAndUserId(long postsId, long usersId);
    List<Interest> findAllByPostId(long postsId);
    int countByPostId(long id);
    List<Interest> findAllByUserId(long id);
}
