package pl.teob.detail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.teob.user.User;
import pl.teob.user.UserRepository;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserDetailService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserDetailRepository userDetailRepository;

    public ResponseEntity addDetailUser(Optional<UserDetailDTO> userDetailDTO, String currentUserName) {
        Optional<User> userDB = userRepository.findByUsername(currentUserName);
        if(userDB.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if(userDB.get().getEnabled() == false){
            return ResponseEntity.status(409).build();
        }

        UserDetail userDetail = new UserDetail();
        userDetail.setUser(userDB.get());

        // TODO: zabezpieczenie aby użytkownik mógł tylko modyfikowac dane w bazie, a nie dodawac kolejnego rekordu, dodatkowo tylko użytkownik z potwerdzonym emailem może dodać szczegóły o sobie


        if(!Objects.isNull(userDetailDTO.get().getFirstName())){
            userDetail.setFirstName(userDetailDTO.get().getFirstName());
        }
        if(!Objects.isNull(userDetailDTO.get().getSecondName())){
            userDetail.setSecondName(userDetailDTO.get().getSecondName());
        }
        if(!Objects.isNull(userDetailDTO.get().getNumberPhone())){
            userDetail.setNumberPhone(userDetailDTO.get().getNumberPhone());
        }
        if(!Objects.isNull(userDetailDTO.get().getInterests())){
            userDetail.setInterests(userDetailDTO.get().getInterests());
        }
        if(!Objects.isNull(userDetailDTO.get().getDescription())){
            userDetail.setDescription(userDetailDTO.get().getDescription());
        }
        if(!Objects.isNull(userDetailDTO.get().getProfilePhotoURL())){
            userDetail.setProfilePhotoURL(userDetailDTO.get().getProfilePhotoURL());
        }

        userDetailRepository.save(userDetail);

        return ResponseEntity.ok(userDetail);
    }
}
