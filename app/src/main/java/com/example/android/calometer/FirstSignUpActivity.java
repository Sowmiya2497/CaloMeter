package com.example.android.calometer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FirstSignUpActivity extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_sign_up);


        Button b1 = (Button) findViewById(R.id.subuser);
        b1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        EditText name = (EditText) findViewById(R.id.username);
                        EditText pass = (EditText) findViewById(R.id.password);

                        String uname = name.getText().toString();
                        String upass = pass.getText().toString();
                        if(uname == null || upass == null )
                        {
                            Toast temp = Toast.makeText(FirstSignUpActivity.this, "Enter username or password before sign up!", Toast.LENGTH_SHORT);
                            temp.show();
                        }

                        else {

                            Contact c = new Contact();
                            c.setName(uname);
                            c.setPassword(upass);
                            int val = helper.insertUser(c);
                            if (val >= 0) {
                                Toast temp = Toast.makeText(FirstSignUpActivity.this, "Inserted user", Toast.LENGTH_SHORT);
                                temp.show();
                                Intent g_intent = new Intent(FirstSignUpActivity.this, SignupActivity.class);
                                g_intent.putExtra("sessionid", Integer.toString(val));
                                startActivity(g_intent);
                            }
                        }
                    }
                });
    }
}
