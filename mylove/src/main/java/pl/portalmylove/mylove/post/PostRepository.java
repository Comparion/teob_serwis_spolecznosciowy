package pl.portalmylove.mylove.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/** Klasa odpowiedzialna za dostep do bazy danych */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByUserId(long id);
    @Transactional
    @Modifying
    @Query("update Post p set p.town = ?1, p.subject = ?2, p.body = ?3 where p.id = ?4")
    int updatePostById(String town, String subject, String body, long id);
}
