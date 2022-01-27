package pl.teob.detail;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.teob.user.User;
import pl.teob.user.UserRepository;

import java.util.*;

@Service
@AllArgsConstructor
public class UserDetailService {

    @Autowired
    private final ObjectMapper objectMapper;


    @Autowired
    UserRepository userRepository;
    @Autowired
    UserDetailRepository userDetailRepository;

    public ResponseEntity addDetailUser(Optional<UserDetailDTO> userDetailDTO) {
        Optional<User> userDB = userRepository.findByUsername(userDetailDTO.get().getUsername());
        if(userDB.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if(userDB.get().getEnabled() == false){
            return ResponseEntity.status(409).build();
        }

        UserDetail userDetail = new UserDetail();
        userDetail.setUser(userDB.get());

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

        userDetailRepository.updateUserDetailById(userDetailDTO.get().getFirstName(),userDetailDTO.get().getSecondName(),userDetailDTO.get().getNumberPhone(),userDetailDTO.get().getInterests(),userDetailDTO.get().getDescription(),userDetailDTO.get().getProfilePhotoURL(),userDetailDB.get().getId());

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

    public ResponseEntity findUsers(String firstName, String secondName, String username) throws JsonProcessingException {
        List<UserDetail> userDetails;

        if(username != null){
            userDetails = userDetailRepository.findByUserUsernameIgnoreCaseContaining(username);
        } else {
            userDetails = userDetailRepository.findAll();
        }

        List<UserDetailDTO> userDetailDTOS = new ArrayList<>();
        for (UserDetail userDetail : userDetails) {
            if (firstName != null && secondName != null) {
                if (userDetail.getFirstName().toLowerCase().contains(firstName.toLowerCase()) && userDetail.getSecondName().toLowerCase().contains(secondName.toLowerCase()))
                    userDetailDTOS.add(UserDetailMapper.userDetailtoDTO(userDetail));
            } else if (firstName != null) {
                if (userDetail.getFirstName().toLowerCase().contains(firstName.toLowerCase()))
                    userDetailDTOS.add(UserDetailMapper.userDetailtoDTO(userDetail));
            } else if (secondName != null) {
                if (userDetail.getSecondName().toLowerCase().contains(secondName.toLowerCase()))
                    userDetailDTOS.add(UserDetailMapper.userDetailtoDTO(userDetail));
            } else
                userDetailDTOS.add(UserDetailMapper.userDetailtoDTO(userDetail));
        }

        return ResponseEntity.ok(objectMapper.writeValueAsString(userDetailDTOS));
    }
}
