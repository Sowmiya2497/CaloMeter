package com.example.android.calometer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.android.calometer.R.id.current_weight;

public class SignupActivity extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        Button b = (Button) findViewById(R.id.subdet);
        b.setOnClickListener(
                new View.OnClickListener() {
                    @Override

                    public void onClick(View v) {

                        String sid = getIntent().getStringExtra("sessionid");
                        int id = Integer.parseInt(sid);
                        EditText currwt = (EditText) findViewById(current_weight);
                        EditText tarwt = (EditText) findViewById(R.id.target_weight);
                        EditText tardays = (EditText) findViewById(R.id.target_days);

                        int current_wt = Integer.parseInt(currwt.getText().toString());
                        int target_wt = Integer.parseInt(tarwt.getText().toString());
                        int target_days = Integer.parseInt(tardays.getText().toString());
                        String start_date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

                        int cal = ((current_wt - target_wt) * 7700) / target_days;

                        target_details td = new target_details();
                        td.setUser_id(id);
                        td.setCurrent_wt(current_wt);
                        td.setTarget_wt(target_wt);
                        td.setTarget_days(target_days);
                        td.setCalories_per_day(cal);
                        td.setStartDate(start_date);

                        int val = helper.insertDetails(td);
                        if (val >= 0) {
                            Toast temp = Toast.makeText(SignupActivity.this, "Inserted details", Toast.LENGTH_SHORT);
                            temp.show();
                            Intent menu = new Intent(SignupActivity.this, MenuActivity.class);
                            menu.putExtra("sessionid", Integer.toString(id));
                            startActivity(menu);
                        }
                    }

                });
    }
}
