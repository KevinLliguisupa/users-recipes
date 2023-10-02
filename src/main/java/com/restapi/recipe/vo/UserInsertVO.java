package com.restapi.recipe.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class UserInsertVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "usrNickname can not null")
    private String usrNickname;

    @NotNull(message = "usrEmail can not null")
    private String usrEmail;

    @NotNull(message = "usrPassword can not null")
    private String usrPassword;

    @NotNull(message = "usrAvatar can not null")
    private String usrAvatar;

    @NotNull(message = "usrName can not null")
    private String usrName;

}
