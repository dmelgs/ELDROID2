package com.example.melgodominic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class ViewRecord extends AppCompatActivity {
    TextView counter;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_record);
        ref();
        readRecords();
        countRecords();

    }
    public void ref(){
        counter = findViewById(R.id.RecordCounter_textView);


    }
    public void countRecords(){
        int recordCount = new TableControllerStudent(this).count();
        counter.setText(recordCount + " Records Found");
    }
    public void readRecords() {
        LinearLayout linearLayoutRecords = (LinearLayout) findViewById(R.id.linearRecords);
        linearLayoutRecords.removeAllViews();
        List<ObjectStudent> students = new TableControllerStudent(this).read();
        if (students.size() > 0) {
            for (ObjectStudent obj : students) {

                int id = obj.getId();
                String studentFirstname = obj.getFirstname();
                String studentLastname = obj.getLastname();
                String studentAddress = obj.getAddress();
                String studentGender = obj.getGender();
                String studentCourse = obj.getCourse();
                int y= students.size();
                String textViewContents = studentLastname + ", " +studentFirstname + "-\t "+ studentAddress +"-\t\t" + studentGender + "-\t " + studentCourse;
                TextView textViewStudentItem= new TextView(this);
                textViewStudentItem.setPadding(4, 16, 4, 16);
                textViewStudentItem.setTextSize(20);
                textViewStudentItem.setText(textViewContents);
                textViewStudentItem.setTag(Integer.toString(id));
                linearLayoutRecords.addView(textViewStudentItem);
                textViewStudentItem.setOnLongClickListener(new OnClickEditRecord());
            }
        }
        else {
            TextView locationItem = new TextView(this);
            locationItem.setPadding(8, 8, 8, 8);
            locationItem.setText("No records yet.");
            linearLayoutRecords.addView(locationItem);
        }
    }
    public void spnr(){
        //creating a list of items for the spinner.
        String[] items = new String[]{"","BSIT", "BSED", "BSCE", "BSMA", "BSCRIM", "BSNURSING"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        spinner.setAdapter(adapter);
    }
}