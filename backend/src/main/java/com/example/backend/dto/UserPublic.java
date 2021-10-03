package com.example.backend.dto;

import com.example.backend.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class UserPublic {

    private Integer id;

    private String userName;

    private String nickName;

    private String profileUrl;

    public static UserPublic of(User user){
        UserPublic userPublic = new UserPublic();
        userPublic.id = user.getId();
        userPublic.userName = user.getUserName();
        userPublic.nickName = user.getNickName();
        userPublic.profileUrl = user.getProfileUrl();

        return userPublic;
    }
}
