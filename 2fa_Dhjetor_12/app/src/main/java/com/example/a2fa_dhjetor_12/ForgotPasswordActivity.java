package com.example.a2fa_dhjetor_12;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPasswordActivity extends Activity {

    private EditText emailEditText, otpEditText, newPasswordEditText;
    private String generatedOTP;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        emailEditText = findViewById(R.id.emailEditText);
        otpEditText = findViewById(R.id.otpEditText);
        newPasswordEditText = findViewById(R.id.newPasswordEditText);

        findViewById(R.id.otpLayout).setVisibility(View.VISIBLE);
        findViewById(R.id.newPasswordLayout).setVisibility(View.VISIBLE);

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
    public void onSendOTP(View view) {
        email = emailEditText.getText().toString();

        if (!email.isEmpty()) {
            generatedOTP = OTPHelper.generateOTP();

            new SendEmailTask().execute(email, generatedOTP);

            Toast.makeText(this, "OTP sent to your email", Toast.LENGTH_SHORT).show();

            emailEditText.setEnabled(false);
            findViewById(R.id.otpLayout).setVisibility(View.VISIBLE);
            findViewById(R.id.newPasswordLayout).setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(this, "Please enter your email address", Toast.LENGTH_SHORT).show();
        }
    }

    public void onResetPassword(View view) {
        String enteredOTP = otpEditText.getText().toString();
        String newPassword = newPasswordEditText.getText().toString();

        if (enteredOTP.equals(generatedOTP)) {
            if (!newPassword.isEmpty()) {
                DBHelper dbHelper = new DBHelper(this);
                boolean updated = dbHelper.updatePassword(email, newPassword);

                if (updated) {
                    Toast.makeText(this, "Password reset successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Error resetting password", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Please enter a new password", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Invalid OTP", Toast.LENGTH_SHORT).show();
        }
    }
}
