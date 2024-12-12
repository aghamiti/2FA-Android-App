package com.example.a2fa_dhjetor_12;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends Activity{
    private DBHelper dbHelper;
    private EditText emailEditText, passwordEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        dbHelper=new DBHelper(this);
        emailEditText=findViewById(R.id.emailEditText);
        passwordEditText=findViewById(R.id.passwordEditText);
    }

    public void onSignUp(View view){
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        if(!email.isEmpty()&&!password.isEmpty()){
            dbHelper.addUser(email, password);
            Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show();
        }
    }
    public void redirectToLogin(View view) {
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
