package pl.portalmylove.mylove.detail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {
    Optional<UserDetail> findByUserId(long idUser);
    UserDetail findByUserUsername(String username);

    @Transactional
    @Modifying
    @Query("update UserDetail u set u.firstName = ?1, u.secondName = ?2, u.numberPhone = ?3, u.likes = ?4, u.description = ?5, u.profilePhotoURL = ?6, u.gender = ?7, u.lookingFor = ?8 where u.id = ?9")
    int updateUserDetailById(String firstname, String second_name, String number_phone, String interests, String description, String profile_photourl, char gender, char looking_For, long id);

    List<UserDetail> findByUserUsernameIgnoreCaseContaining(String username);
    List<UserDetail> findByGender(Character gender);
}
