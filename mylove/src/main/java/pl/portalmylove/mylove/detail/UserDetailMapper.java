package pl.portalmylove.mylove.detail;

/** Klasa sluzy do tego, aby w odpowiedni sposob przerobic dane trafiajce do przegladarki*/
public class UserDetailMapper {

    public static UserDetailDTO userDetailtoDTO(UserDetail userDetail){
        return UserDetailDTO.builder()
                .firstName(userDetail.getFirstName())
                .secondName(userDetail.getSecondName())
                .numberPhone(userDetail.getNumberPhone())
                .likes(userDetail.getLikes())
                .description(userDetail.getDescription())
                .profilePhotoURL(userDetail.getProfilePhotoURL())
                .username(userDetail.getUser().getUsername())
                .gender(userDetail.getGender())
                .lookingFor((userDetail.getLookingFor()))
                .build();
    }
}
