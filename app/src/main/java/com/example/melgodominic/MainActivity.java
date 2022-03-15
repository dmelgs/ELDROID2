package com.example.melgodominic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //variables
    Button CreateStudAct, ViewRecAct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    //instantiate
        CreateStudAct = findViewById(R.id.CreateStudActivity_Button);
        ViewRecAct = findViewById(R.id.ViewRecActivity_Button);

    //
        CreateStudAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenCreateStudActivity();
            }
        });

        ViewRecAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenViewRecActivity();
            }
        });
    }

    //Functions
    public void OpenCreateStudActivity(){
        Intent intent = new Intent(this, CreateStudent.class);
        startActivity(intent);
    }
    public void OpenViewRecActivity(){
        Intent intent = new Intent(this, ViewRecord.class);
        startActivity(intent);
    }
}