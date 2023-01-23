package com.restapi.recipe.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserLoginVO {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "usrEmail can not null")
    private String usrEmail;

    @NotNull(message = "usrPassword can not null")
    private String usrPassword;

}
