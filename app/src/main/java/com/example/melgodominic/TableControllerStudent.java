package com.example.melgodominic;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class TableControllerStudent extends  DataBaseHandler{
    public TableControllerStudent(Context context) {
        super(context);
    }
    public boolean create(ObjectStudent objectStudent) {
        ContentValues values = new ContentValues();
        values.put("firstname", objectStudent.firstname);
        values.put("lastname", objectStudent.lastname);
        values.put("address", objectStudent.address);
        values.put("gender", objectStudent.gender);
        values.put("course", objectStudent.course);
        SQLiteDatabase db = this.getWritableDatabase();
        boolean createSuccessful = db.insert("students", null, values) > 0;
        db.close();
        return createSuccessful;
    }
}
