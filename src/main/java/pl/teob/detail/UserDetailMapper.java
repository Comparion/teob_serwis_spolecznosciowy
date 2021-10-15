package pl.teob.detail;

public class UserDetailMapper {

    public static UserDetailDTO userDetailtoDTO(UserDetail userDetail){
        return UserDetailDTO.builder()
                .firstName(userDetail.getFirstName())
                .secondName(userDetail.getSecondName())
                .numberPhone(userDetail.getNumberPhone())
                .interests(userDetail.getInterests())
                .description(userDetail.getDescription())
                .profilePhotoURL(userDetail.getProfilePhotoURL())
                .build();
    }
}
