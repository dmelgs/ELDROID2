package com.example.melgodominic;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class OnClickEditRecord implements View.OnLongClickListener {
    Context context;
    String id;

    @Override
    public boolean onLongClick(View view) {
        context = view.getContext();
        id = view.getTag().toString();
        final CharSequence[] items = { "Edit", "Delete" };
        new AlertDialog.Builder(context).setTitle("Student Record")
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        dialog.dismiss();
                        if (item == 0) {
                            editRecord(Integer.parseInt(id));
                        }
                        else if (item == 1) {
                            boolean deleteSuccessful = new TableControllerStudent(context).delete(Integer.parseInt(id));
                            if (deleteSuccessful){
                                Toast.makeText(context, "Student record was deleted.", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, "Unable to delete student record.", Toast.LENGTH_SHORT).show();
                            }
                            ((ViewRecord) context).countRecords();
                            ((ViewRecord) context).readRecords();
                        }
                    }
                }).show();

        return false;
    }
    public void editRecord(final int studentId) {
        final TableControllerStudent tableControllerStudent = new TableControllerStudent(context);
        ObjectStudent objectStudent = tableControllerStudent.readSingleRecord(studentId);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.inputform, null, false);
        final EditText editTextStudentFirstname = (EditText) formElementsView.findViewById(R.id.editTextStudentFirstname);
        final EditText editTextStudentLastname = (EditText) formElementsView.findViewById(R.id.editTextStudentLastname);
        final EditText editTextStudentAddress= (EditText) formElementsView.findViewById(R.id.editTextStudentAddress);
        final EditText editTextStudentCourse= (EditText) formElementsView.findViewById(R.id.editTextStudentCourse);
        final TextView textViewStudentGender = (TextView) formElementsView.findViewById(R.id.gender_textView2);

        editTextStudentFirstname.setText(objectStudent.getFirstname());
        editTextStudentLastname.setText(objectStudent.getLastname());
        editTextStudentAddress.setText(objectStudent.getAddress());
        editTextStudentCourse.setText(objectStudent.getCourse());
        textViewStudentGender.setText(objectStudent.getGender());

        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Edit Record")
                .setPositiveButton("Save Changes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                if(editTextStudentFirstname.getText().toString().isEmpty() || editTextStudentLastname.getText().toString().isEmpty() ||
                                        editTextStudentAddress.getText().toString().isEmpty() || editTextStudentCourse.getText().toString().isEmpty()){
                                    Toast.makeText(context, "Some fields are missing", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                ObjectStudent objectStudent = new ObjectStudent();
                                objectStudent.setId(studentId);
                                objectStudent.setFirstname(editTextStudentFirstname.getText().toString());
                                objectStudent.setLastname(editTextStudentLastname.getText().toString());
                                objectStudent.setAddress(editTextStudentAddress.getText().toString());
                                objectStudent.setGender(textViewStudentGender.getText().toString());
                                objectStudent.setCourse(editTextStudentCourse.getText().toString());

                                boolean updateSuccessful = tableControllerStudent.update(objectStudent);
                                if(updateSuccessful){
                                    Toast.makeText(context, "Student record was updated.", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context, "Unable to update student record.", Toast.LENGTH_SHORT).show();
                                }
                                ((ViewRecord) context).countRecords();
                                ((ViewRecord) context).readRecords();
                            }
                        }).show();
    }


}

