package com.restapi.recipe.usersTest;

import com.restapi.recipe.util.encryptUtility;

public class encryptConsoleTest {

    public static void main(String[] args){
        String hashPassword = encryptUtility.encryptPassword("1234");
        System.out.println("\n\nThe encrypted password is: \n" + hashPassword);
        String originalPassword = "Scorpion_246";
        String HashPassword = "$2a$10$LJEgTqgwamdl76w40r3tfuQHMHo55WhmUKF7Wivsi3i4iyP.KDIVy";

        System.out.println("\n\nAre the passwords the same: \n" + encryptUtility.comparePasswords(originalPassword, HashPassword));

    }
}
