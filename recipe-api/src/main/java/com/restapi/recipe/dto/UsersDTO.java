package com.restapi.recipe.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class UsersDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long usrId;

    private String usrNickname;

    private String usrEmail;

    private String usrPassword;

    private String usrAvatar;

    private String usrName;

    private Boolean usrState;

}
