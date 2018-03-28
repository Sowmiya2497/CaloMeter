package com.example.android.calometer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*Opens Login Page*/
    public void openLogin(View view) {
        Intent l_intent = new Intent(this, LoginActivity.class);
        startActivity(l_intent);
    }

    /*Opens Signup Page*/
    public void openSignUp(View view) {
        Intent s_intent = new Intent(this,FirstSignUpActivity.class);
        startActivity(s_intent);
    }
}

