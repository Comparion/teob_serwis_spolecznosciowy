package pl.teob.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findById(Integer userId);
    void deleteById(Integer userId);

    @Transactional
    @Modifying
    @Query("UPDATE User a " + "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);
}
