package com.example.android.calometer;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class ExerciseActivity extends AppCompatActivity {

    String ex=new String();
    DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        String sid = getIntent().getStringExtra("sessionid");
        final int id = Integer.parseInt(sid);

        Spinner spinner= (Spinner)findViewById(R.id.Exspinner);
        String[] spinnerArray=new String[]{};
        Cursor curex = helper.getDropDownEx();
        List<String> ExSpinner = new ArrayList<String>();
        if(curex!=null && curex.moveToFirst()) {
            do {
                ExSpinner.add(curex.getString(curex.getColumnIndex("ex_name")));
            }while (curex.moveToNext());
        }
        ArrayAdapter<String> ExAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,ExSpinner);
        ExAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(ExAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ex=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        Button b1 = (Button) findViewById(R.id.addEx);
        b1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        EditText b = (EditText) findViewById(R.id.dur);
                        int dur = Integer.parseInt(b.getText().toString());

                        int cal = helper.getExCalories(ex);
                        ExItem ex_item = new ExItem();
                        ex_item.setExItem(id, ex, dur, (cal * dur));
                        int check = helper.insertExItem(ex_item);
                    }
                });

        Button b2 = (Button) findViewById(R.id.displayEx);
        b2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Cursor cur = helper.getExRows(id);
                        String[] exList = new String[]{DatabaseHelper.COLUMN_USER_EX_EX_NAME, DatabaseHelper.COLUMN_USER_EX_TIME};
                        int[] toView = new int[]{R.id.ex_name, R.id.ex_amount};
                        SimpleCursorAdapter myCursorAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.ex_item, cur, exList, toView, 0);
                        ListView myList = (ListView) findViewById(R.id.exList);
                        myList.setAdapter(myCursorAdapter);


                    }
                });

    }


}

