package com.restapi.recipe.UtilTest;

import com.restapi.recipe.util.encryptionUtility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class EncryptionTest {

    @Test
    public void encryptPasswordTest(){
        String originalPassword = "password";

        Assertions.assertNotNull(encryptionUtility.encryptPassword(originalPassword));
    }

    @Test
    public void comparePasswordsTest(){
        String originalPassword = "password";
        String HashPassword = "$2a$10$hak6W08XGinad4ZBoEV1leskYJEvZ2KVc/S89WxJNqtlINL7Npn3u";

        Assertions.assertTrue((encryptionUtility.comparePasswords(originalPassword, HashPassword)));
    }

}
