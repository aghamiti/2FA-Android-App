package com.example.a2fa_dhjetor_12;
import java.util.Random;

public class OTPHelper {
    public static String generateOTP(){
        Random random = new Random();
        String otp = String.format("%06d",random.nextInt(999999));
        return otp;
    }
}
