package com.example.backend.dto;

import com.example.backend.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class UserPublicDto {

    private Integer id;

    private String userName;

    private String nickName;

    private String profileUrl;

    private Integer authority;

    public static UserPublicDto of(User user){
        UserPublicDto userPublicDto = new UserPublicDto();
        userPublicDto.id = user.getId();
        userPublicDto.userName = user.getUserName();
        userPublicDto.nickName = user.getNickName();
        userPublicDto.profileUrl = user.getProfileUrl();

        return userPublicDto;
    }
}
