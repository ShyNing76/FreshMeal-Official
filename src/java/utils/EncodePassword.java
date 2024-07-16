/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.security.MessageDigest;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 *
 * @author ADMIN
 */
public class EncodePassword {
    
    public static String toSHA1(String password) {
        String salt = "";
        String result = null;
        password = password + salt;
        try {
            byte[] dataBytes = password.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            result = Base64.encodeBase64String(md.digest(dataBytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
