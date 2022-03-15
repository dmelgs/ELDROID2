package com.example.melgodominic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ViewRecord extends AppCompatActivity {
    TextView counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_record);
        ref();
        countRecords();

    }
    public void ref(){
        counter = findViewById(R.id.RecordCounter_textView);

    }
    public void countRecords(){
        int recordCount = new TableControllerStudent(this).count();
        counter.setText(recordCount + " Records Found");
    }
}