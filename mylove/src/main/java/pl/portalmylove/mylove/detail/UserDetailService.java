package pl.portalmylove.mylove.detail;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.portalmylove.mylove.user.User;
import pl.portalmylove.mylove.user.UserRepository;

import java.util.ArrayList;
import java.util.List;
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
        if(!Objects.isNull(userDetailDTO.get().getLikes())){
            userDetail.setLikes(userDetailDTO.get().getLikes());
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
        if(!Objects.isNull(userDetailDTO.get().getLikes())){
            userDetailDB.get().setLikes("");
        }
        if(!Objects.isNull(userDetailDTO.get().getDescription())){
            userDetailDB.get().setDescription("");
        }
        if(!Objects.isNull(userDetailDTO.get().getProfilePhotoURL())){
            userDetailDB.get().setProfilePhotoURL("");
        }

        userDetailRepository.updateUserDetailById(userDetailDTO.get().getFirstName(),userDetailDTO.get().getSecondName(),userDetailDTO.get().getNumberPhone(),userDetailDTO.get().getLikes(),userDetailDTO.get().getDescription(),userDetailDTO.get().getProfilePhotoURL(), userDetailDTO.get().getGender(), userDetailDTO.get().getLookingFor(), userDetailDB.get().getId());

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

    public ResponseEntity findUsers(String likes, String username) throws JsonProcessingException {
        String regex = "[\\s,]+";
        List<UserDetail> userDetails;
        String likesArray [];
        int counter;

        if(username != null){
            userDetails = userDetailRepository.findByUserUsernameIgnoreCaseContaining(username);
        } else {
            userDetails = userDetailRepository.findAll();
        }

        List<UserDetailDTO> userDetailDTOS = new ArrayList<>();
        for (UserDetail userDetail : userDetails) {
            counter=0;
         if (likes != null) {
             likesArray = likes.split(regex);
             for(String like : likesArray){
                 if(userDetail.getLikes().toLowerCase().contains(like.toLowerCase())) {
                     counter++;
                     if(counter== likesArray.length) {
                         userDetailDTOS.add(UserDetailMapper.userDetailtoDTO(userDetail));
                         break;
                     }
                 }
             }
//                if (userDetail.getLikes().toLowerCase().contains(likes.toLowerCase()))
//                    userDetailDTOS.add(UserDetailMapper.userDetailtoDTO(userDetail));
            } else
                userDetailDTOS.add(UserDetailMapper.userDetailtoDTO(userDetail));
        }

        return ResponseEntity.ok(objectMapper.writeValueAsString(userDetailDTOS));
    }

    public ResponseEntity suggestionsUsers(String username) throws JsonProcessingException {
        List<UserDetail> userDetails;
        String regex = "[\\s,]+";
        String likesArray [];
        int counter;
       // Character gender=' ';
        Optional<User> userDB = userRepository.findByUsername(username);

        if(userDB.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<UserDetail> userDetailDB = userDetailRepository.findByUserId(userDB.get().getId());
        if(userDetailDB.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

//        switch(userDetailDB.get().getGender()){
//            case 'M':
//                gender='K';
//                break;
//            case 'K':
//                gender='M';
//                break;
//            case ' ':
//                break;
//        }

        userDetails = userDetailRepository.findByGender(userDetailDB.get().getLookingFor());

        List<UserDetailDTO> userDetailDTOS = new ArrayList<>();
        for (UserDetail userDetail : userDetails) {
            counter=0;
            if (userDetail.getLikes() != null) {
                likesArray = userDetail.getLikes().split(regex);
                for(String like : likesArray){
                    if(userDetailDB.get().getLikes().toLowerCase().contains(like.toLowerCase())) {
                        counter++;
                        if(counter>0) {
                            userDetailDTOS.add(UserDetailMapper.userDetailtoDTO(userDetail));
                            break;
                        }
                    }
                }
            } else
                userDetailDTOS.add(UserDetailMapper.userDetailtoDTO(userDetail));
        }

        return ResponseEntity.ok(objectMapper.writeValueAsString(userDetailDTOS));
    }
}
