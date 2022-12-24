package com.restapi.recipe.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class encryptUtility {

    public static String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static Boolean comparePasswords(String originalPassword, String hashPassword) {

        return BCrypt.checkpw(originalPassword, hashPassword);
    }
}
