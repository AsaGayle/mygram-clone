package com.example.mygram_clone;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LOGIN ACTIVITY";
    private EditText etUsername;
    private EditText etPassword;
    private Button btnSignUp;
    private Button   btnLogin;
    ParseUser currentUser = ParseUser.getCurrentUser();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(currentUser != null){
            goMainActivity();
        } else {
            setContentView(R.layout.activity_login);
        }

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnSignUp  = findViewById(R.id.btnSignup);
        btnLogin  = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                login(username, password);
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goSignUpActivity();
            }
        });
    }

    private void login(final String username, String password){
        // TODO: navigate to new activity if the user has signed properly
        Log.i(TAG, "LOGGING IN " + username);
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(user != null){
                    goMainActivity();
                } else {
                    int length = Toast.LENGTH_SHORT;
                    String message = "LOG IN FAIL";

                    Toast.makeText(getApplicationContext(), message, length).show();
                    Log.e(TAG, "ERROR: Username " + username + " not found");
                    e.printStackTrace();
                    return;
                }
            }

        });
    }

    private void goMainActivity(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private void goSignUpActivity(){
        Intent i = new Intent(this, SignUpActivity.class);
        startActivity(i);
    }
}
