package com.example.android.calometer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button b = (Button) findViewById(R.id.sublog);
        b.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
    /*Authenticates User(Connect to database)*/
                        EditText a = (EditText) findViewById(R.id.username);
                        String uname = a.getText().toString();
                        EditText b = (EditText) findViewById(R.id.password);
                        String upass = b.getText().toString();

                        int id = helper.checkLogin(uname, upass);
                        if (id >= 0) {
                            Intent menu = new Intent(LoginActivity.this, MenuActivity.class);
                            menu.putExtra("sessionid", Integer.toString(id));
                            startActivity(menu);
                        } else if (id == -1) {
                            Toast temp = Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT);
                            temp.show();
                        } else {
                            Toast temp = Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT);
                            temp.show();
                        }
                    }


                });
    }
}