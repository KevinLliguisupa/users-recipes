package com.restapi.recipe.dto;

import lombok.Data;

@Data
public class UsersInfoDTO {
    private static final long serialVersionUID = 1L;
    private Long usrId;

    private String usrNickname;

    private String usrEmail;

    private String usrAvatar;

    private String usrName;

    private Boolean usrState;

}
