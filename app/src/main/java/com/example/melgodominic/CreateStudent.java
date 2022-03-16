package com.example.melgodominic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.content.Context;

public class CreateStudent extends AppCompatActivity {
    //global variables
    Spinner spinner;
    CheckBox male, female;
    Button add;
    EditText fname, lname, address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_student);

        ref();
        spnr();

        //validator for checkboxes
        male.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!male.isChecked() && !female.isChecked()) {
                    male.setChecked(true);
                    female.setChecked(false);
                }
                if ( female.isChecked() && male.isChecked()){
                    male.setChecked(true);
                    female.setChecked(false);
                }

            }
        });
        female.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!female.isChecked() && !male.isChecked()) {
                    female.setChecked(true);
                    male.setChecked(false);
                }
                if ( female.isChecked() && male.isChecked()){
                    female.setChecked(true);
                    male.setChecked(false);
                }

            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getRootView().getContext();
                ObjectStudent objectStudent = new ObjectStudent();
                objectStudent.setFirstname(fname.getText().toString());
                objectStudent.setLastname(lname.getText().toString());
                objectStudent.setAddress(address.getText().toString());
                objectStudent.setCourse(spinner.getSelectedItem().toString());
                if(female.isChecked()){
                    objectStudent.setGender("Female");
                }
                else if(male.isChecked()){
                    objectStudent.setGender("Male");
                }
                boolean createSuccessful = new TableControllerStudent(context).create(objectStudent);
                if(createSuccessful){
                    Toast.makeText(context, "Student information was saved.", Toast.LENGTH_SHORT).show();
                    fname.getText().clear();
                    lname.getText().clear();
                    address.getText().clear();
                    male.setChecked(false);
                    female.setChecked(false);
                    spinner.setSelection(0);

                }else{
                    Toast.makeText(context, "Unable to save student information.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void ref(){
        spinner = findViewById(R.id.Course_Spinner);
        //
        male = findViewById(R.id.Male_CheckBox);
        female = findViewById(R.id.Female_CheckBox);
        //
        fname = findViewById(R.id.Firstname_EditText);
        lname =findViewById(R.id.Lastname_EditText);
        address = findViewById(R.id.Address_EditText);
        //
        add = findViewById(R.id. Add_Button);
    }
    public void spnr(){
        //creating a list of items for the spinner.
        String[] items = new String[]{"","BSIT", "BSED", "BSCE", "BSMA", "BSCRIM", "BSNURSING"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        spinner.setAdapter(adapter);
    }
}