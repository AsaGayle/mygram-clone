package com.example.mygram_clone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {

    public static final String TAG = "SignUpActivity";

    EditText etSignEmail;
    EditText etSignUName;
    EditText etSignPassword;
    EditText etSignPhonenum;
    Button btnSignUpSubmit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);

        etSignEmail = findViewById(R.id.etSignEmail);
        etSignPassword = findViewById(R.id.etSignPassword);
        etSignUName = findViewById(R.id.etSignUName);
        etSignPhonenum = findViewById(R.id.etSignPhoneNum);
        btnSignUpSubmit = findViewById(R.id.btnSignUpSubmit);
        btnSignUpSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etSignEmail.getText().toString();
                String password = etSignPassword.getText().toString();
                String username = etSignUName.getText().toString();
                String phonenum = etSignPhonenum.getText().toString();

                signUp(email, password, username, phonenum);
            }
        });

    }

    private void signUp(String email, String password, String username, String phonenum){
        // Make new user
        ParseUser user = new ParseUser();

        //Set values for new user
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.put("phone", phonenum);
        user.put("handle", username);

        Intent i = new Intent(this, LoginActivity.class);
        Toast.makeText(SignUpActivity.this, "Sign up successs!", Toast.LENGTH_SHORT).show();
        startActivity(i);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null){
                    Log.i(TAG, "SIGN UP SUCCESS!!!");
                    //DO STUFF
                } else {
                    e.getStackTrace();
                }
            }
        });
    }
}
