package pl.teob.detail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {
    Optional<UserDetail> findByUserId(long idUser);

    @Transactional
    @Modifying
    @Query("update UserDetail u set u.firstName = ?1, u.secondName = ?2, u.numberPhone = ?3, u.interests = ?4, u.description = ?5, u.profilePhotoURL = ?6 where u.id = ?7")
    int updateUserDetailById(String firstname, String second_name, String number_phone, String interests, String description, String profile_photourl, long id);
}
