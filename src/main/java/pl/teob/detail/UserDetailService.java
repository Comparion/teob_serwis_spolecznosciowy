package pl.teob.detail;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.teob.user.User;
import pl.teob.user.UserRepository;
import pl.teob.user.token.ConfirmationToken;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailService {

    @Autowired
    private final ObjectMapper objectMapper;


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

        // TODO: zabezpieczenie aby użytkownik mógł tylko modyfikowac dane w bazie, a nie dodawac kolejnego rekordu
        // TODO: dodawanie i edycja szczegółów powinna wykonywać się najprawdpoobniej w jendym miesjcu

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

    public ResponseEntity updateDetailUser(Optional<UserDetailDTO> userDetailDTO, String username) {
        Optional<User> userDB = userRepository.findByUsername(username);
        if(userDB.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if(userDB.get().getEnabled() == false){
            return ResponseEntity.status(409).build();
        }

        Optional<UserDetail> userDetailDB = userDetailRepository.findByUserId(userDB.get().getId());
        if(userDetailDB.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


        if(!Objects.isNull(userDetailDTO.get().getFirstName())){
            userDetailDB.get().setFirstName("");
        }
        if(!Objects.isNull(userDetailDTO.get().getSecondName())){
            userDetailDB.get().setSecondName("");
        }
        if(!Objects.isNull(userDetailDTO.get().getNumberPhone())){
            userDetailDB.get().setNumberPhone("");
        }
        if(!Objects.isNull(userDetailDTO.get().getInterests())){
            userDetailDB.get().setInterests("");
        }
        if(!Objects.isNull(userDetailDTO.get().getDescription())){
            userDetailDB.get().setDescription("");
        }
        if(!Objects.isNull(userDetailDTO.get().getProfilePhotoURL())){
            userDetailDB.get().setProfilePhotoURL("");
        }

        //userDetailRepository.save(userDetailDB);

        userDetailRepository.updateUserDetailById(userDetailDTO.get().getFirstName(),userDetailDTO.get().getSecondName(),userDetailDTO.get().getNumberPhone(),userDetailDTO.get().getInterests(),userDetailDTO.get().getDescription(),userDetailDTO.get().getProfilePhotoURL(),userDetailDB.get().getId());
        //userDetailRepository.updateUserDetailById(userDetailDTO.get().getFirstName(),userDetailDTO.get().getProfilePhotoURL(), userDetailDTO.get().getSecondName(), userDetailDB.get().getId());


        return ResponseEntity.ok("ok");
    }

    public ResponseEntity getDetail(String username) throws JsonProcessingException {
        Optional<User> userDB = userRepository.findByUsername(username);
        if(userDB.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Optional<UserDetail> userDetailDB = userDetailRepository.findByUserId(userDB.get().getId());
        if(userDetailDB.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(objectMapper.writeValueAsString(UserDetailMapper.userDetailtoDTO(userDetailDB.get())));
    }
}
