package com.example.a2fa_dhjetor_12;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
public class LoginActivity extends Activity{
    private DBHelper dbHelper;
    private EditText emailEditText, passwordEditText;
    private String generatedOTP;
    private String recipientEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHelper = new DBHelper(this);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
    }
    private static class SendEmailTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            String recipientEmail = params[0];
            String otp = params[1];
            EmailHelper.sendOTPEmail(recipientEmail, otp);
            return null;
        }
    }

    public void onLogin(View view){
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if(dbHelper.checkUserExists(email, password)){
            recipientEmail = email;
            generatedOTP = OTPHelper.generateOTP();
            new SendEmailTask().execute(email, generatedOTP);
            Toast.makeText(this, "OTP sent to email", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, OTPVerificationActivity.class);
            intent.putExtra("generatedOTP", generatedOTP);
            intent.putExtra("email", recipientEmail);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
        }
    }
    public void onForgotPassword(View view){
        Intent intent = new Intent(LoginActivity.this,ForgotPasswordActivity.class);
        startActivity(intent);
    }
}
