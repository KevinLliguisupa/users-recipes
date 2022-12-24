package com.restapi.recipe.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class UsersUpdateVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "usrNickname can not null")
    private String usrNickname;

    @NotNull(message = "usrEmail can not null")
    private String usrEmail;

    @NotNull(message = "usrAvatar can not null")
    private String usrAvatar;

    @NotNull(message = "usrName can not null")
    private String usrName;

}
