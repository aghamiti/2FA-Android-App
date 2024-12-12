package com.example.a2fa_dhjetor_12;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
public class OTPVerificationActivity extends Activity{
    private String generatedOTP;
    private EditText otpEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);
        otpEditText = findViewById(R.id.otpEditText);
        generatedOTP = getIntent().getStringExtra("generatedOTP");
    }
    public void onVerifyOTP(View view){
        String enteredOTP = otpEditText.getText().toString();
        if(enteredOTP.equals(generatedOTP)){
            Toast.makeText(this, "OTP verified successfully!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(this, "Invalid OTP, please try again.", Toast.LENGTH_SHORT).show();
        }
    }
    public void onBack(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
