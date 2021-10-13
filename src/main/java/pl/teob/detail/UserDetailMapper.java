package pl.teob.detail;

public class UserDetailMapper {

    public static UserDetailDTO userDetailtoDTO(UserDetail userDetail){
        return UserDetailDTO.builder()
                .firstname(userDetail.getFirstName())
                .secondname(userDetail.getSecondName())
                .numberPhone(userDetail.getNumberPhone())
                .interests(userDetail.getInterests())
                .description(userDetail.getDescription())
                .profilePhotoURL(userDetail.getProfilePhotoURL())
                .build();
    }
}
