package com.restapi.recipe.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * $table.getTableComment()
 */
@Data
@Entity
@Table(name = "users")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "usr_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usrId;

    @Column(name = "usr_nickname", nullable = false)
    private String usrNickname;

    @Column(name = "usr_email", nullable = false)
    private String usrEmail;

    @Column(name = "usr_password", nullable = false)
    private String usrPassword;

    @Column(name = "usr_avatar", nullable = false)
    private String usrAvatar;

    @Column(name = "usr_name", nullable = false)
    private String usrName;

    @Column(name = "usr_state", nullable = false)
    private Boolean usrState;

}
