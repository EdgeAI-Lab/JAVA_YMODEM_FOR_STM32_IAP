package cn.fhc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CalMD5 {

    public static String getMD5(String pathname){
        byte[] buff = new byte[8192];
        int len = 0;

        try {
            FileInputStream fileInputStream = new FileInputStream(new File(pathname));
            MessageDigest md = MessageDigest.getInstance("MD5");

            while ((len=fileInputStream.read(buff))!=-1){
                md.update(buff,0,len);
            }

            byte[] messageDigest = md.digest();

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }



}
