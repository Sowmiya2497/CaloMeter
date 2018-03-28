package com.example.android.calometer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


    /*Open FoodDiaryActivity*/
        Button b1 = (Button) findViewById(R.id.t1);
        b1.setOnClickListener(
                new View.OnClickListener()

                {
                    @Override
                    public void onClick(View v) {
                        String sid = getIntent().getStringExtra("sessionid");
                        int id = Integer.parseInt(sid);
                        Intent f_intent = new Intent(MenuActivity.this, FoodDiary.class);
                        f_intent.putExtra("sessionid", Integer.toString(id));
                        startActivity(f_intent);
                    }
                });

        Button b2 = (Button) findViewById(R.id.t2);
        b2.setOnClickListener(
                new View.OnClickListener()

                {
                    @Override
                    public void onClick(View v) {
                        String sid = getIntent().getStringExtra("sessionid");
                        int id = Integer.parseInt(sid);
                        Intent e_intent = new Intent(MenuActivity.this, ExerciseActivity.class);
                        e_intent.putExtra("sessionid", Integer.toString(id));
                        startActivity(e_intent);
                    }
                });

        Button b3 = (Button) findViewById(R.id.t3);
        b3.setOnClickListener(
                new View.OnClickListener()

                {
                    @Override
                    public void onClick(View v) {
                        String sid = getIntent().getStringExtra("sessionid");
                        int id = Integer.parseInt(sid);

                        Intent d_intent = new Intent(MenuActivity.this, DailyActivity.class);
                        d_intent.putExtra("sessionid", Integer.toString(id));
                        startActivity(d_intent);
                    }
                });

        Button b4 = (Button) findViewById(R.id.t4);
        b4.setOnClickListener(
                new View.OnClickListener()

                {
                    @Override
                    public void onClick(View v) {
                        String sid = getIntent().getStringExtra("sessionid");
                        int id = Integer.parseInt(sid);

                        Intent z_intent = new Intent(MenuActivity.this, GraphActivity.class);
                        z_intent.putExtra("sessionid", Integer.toString(id));
                        startActivity(z_intent);
                    }
                });

    }


}
