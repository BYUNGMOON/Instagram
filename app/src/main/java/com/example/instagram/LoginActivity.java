package com.example.instagram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

    public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edtEmail, edtPassword;
    Button btnLogin, btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("LOG IN");

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);

        edtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                Log.i("KBM", keyCode + "" + ", event - " + event.getAction() + "");

                if (keyCode == KeyEvent.KEYCODE_ENTER &&

                        event.getAction() == KeyEvent.ACTION_DOWN) {
                    onClick(btnLogin);
                }
                return false;
            }
        });



        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);

        btnLogin.setOnClickListener(this);
        btnSignup.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null) {
//            ParseUser.getCurrentUser().logOut();
            transitionToSocialMediaActivity();
        }

//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                ParseUser.logInInBackground(edtEmail.getText().toString(),
//                        edtPassword.getText().toString(),
//                        new LogInCallback() {
//                            @Override
//                            public void done(ParseUser user, ParseException e) {
//
//                                if (user != null && e == null) {
//                                    Toast.makeText(LoginActivity.this,
//                                            edtEmail.getText() + " is signed up successfully",
//                                            Toast.LENGTH_LONG).show();
//
//                                } else {
//                                    Toast.makeText(LoginActivity.this,
//                                            edtEmail.getText() + "can't be singed up" + "\n" + e.getMessage(),
//                                            Toast.LENGTH_LONG).show();
//                                }
//                            }
//                        });
//            }
//        });
//
//        btnSignup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LoginActivity.this, SignUpLoginActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    @Override
    public void onClick(View v) {

        switch( v.getId()) {

            case R.id.btnLogin:

                if (edtEmail.getText().toString().equals("") ||
                        edtPassword.getText().toString().equals("")) {

                    Toast.makeText(LoginActivity.this,
                            "Email, Username, password is required!",
                            Toast.LENGTH_LONG).show();
                    break;
                }

                ParseUser.logInInBackground(edtEmail.getText().toString(),
                        edtPassword.getText().toString(),
                        new LogInCallback() {
                            @Override
                            public void done(ParseUser user, ParseException e) {

                                if (user != null && e == null) {
                                    Toast.makeText(LoginActivity.this,
                                            edtEmail.getText() + " is signed up successfully",
                                            Toast.LENGTH_LONG).show();

                                    transitionToSocialMediaActivity();

                                } else {
                                    Toast.makeText(LoginActivity.this,
                                            edtEmail.getText() + "can't be singed up" + "\n" + e.getMessage(),
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                break;

            case R.id.btnSignup:
                Intent intent = new Intent(LoginActivity.this, SignUpLoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void rootLayoutTapped(View v) {
        try {
            InputMethodManager inputMethodManager =
                    (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void transitionToSocialMediaActivity() {
        Intent intent = new Intent(LoginActivity.this, SocialMediaActivity.class);
        startActivity(intent);
    }

}
