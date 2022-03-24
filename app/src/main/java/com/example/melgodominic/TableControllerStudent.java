package com.example.melgodominic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TableControllerStudent extends  DataBaseHandler{
    public TableControllerStudent(Context context) {
        super(context);
    }
    public boolean create(ObjectStudent objectStudent) {
        ContentValues values = new ContentValues();
        values.put("firstname", objectStudent.getFirstname());
        values.put("lastname", objectStudent.getLastname());
        values.put("address", objectStudent.getAddress());
        values.put("gender", objectStudent.getGender());
        values.put("course", objectStudent.getCourse());
        SQLiteDatabase db = this.getWritableDatabase();
        boolean createSuccessful = db.insert("students", null, values) > 0;
        db.close();
        return createSuccessful;
    }
    public int count() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM students";
        int recordCount = db.rawQuery(sql, null).getCount();
        db.close();
        return recordCount;
    }
    public List<ObjectStudent> read() {
        List<ObjectStudent> recordsList = new ArrayList<>();
        String sql = "SELECT * FROM students ORDER BY id DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                String studentFirstname = cursor.getString(cursor.getColumnIndex("firstname"));
                String studentLastname = cursor.getString(cursor.getColumnIndex("lastname"));
                String studentAddress = cursor.getString(cursor.getColumnIndex("address"));
                String studentGender = cursor.getString(cursor.getColumnIndex("gender"));
                String studentCourse = cursor.getString(cursor.getColumnIndex("course"));
                ObjectStudent objectStudent = new ObjectStudent();
                objectStudent.setId(id);
                objectStudent.setFirstname(studentFirstname);
                objectStudent.setLastname(studentLastname);
                objectStudent.setAddress(studentAddress);
                objectStudent.setGender(studentGender);
                objectStudent.setCourse(studentCourse);
                recordsList.add(objectStudent);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return recordsList;
    }
    public ObjectStudent readSingleRecord(int studentId) {
        ObjectStudent objectStudent = null;
        String sql = "SELECT * FROM students WHERE id = " + studentId;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
            String studentFirstname = cursor.getString(cursor.getColumnIndex("firstname"));
            String studentLastname = cursor.getString(cursor.getColumnIndex("lastname"));
            String studentAddress = cursor.getString(cursor.getColumnIndex("address"));
            String studentGender = cursor.getString(cursor.getColumnIndex("gender"));
            String studentCourse = cursor.getString(cursor.getColumnIndex("course"));
            objectStudent = new ObjectStudent();
            objectStudent.setId(id);
            objectStudent.setFirstname(studentFirstname);
            objectStudent.setLastname(studentLastname);
            objectStudent.setAddress(studentAddress);
            objectStudent.setGender(studentGender);
            objectStudent.setCourse(studentCourse);
        }
        cursor.close();
        db.close();
        return objectStudent;
    }

    public boolean update(ObjectStudent objectStudent) {
        ContentValues values = new ContentValues();
        values.put("firstname", objectStudent.getFirstname());
        values.put("lastname", objectStudent.getLastname());
        values.put("address", objectStudent.getAddress());
        values.put("gender", objectStudent.getGender());
        values.put("course", objectStudent.getCourse());
        String where = "id = ?";
        String[] whereArgs = { Integer.toString(objectStudent.getId()) };
        SQLiteDatabase db = this.getWritableDatabase();
        boolean updateSuccessful = db.update("students", values, where, whereArgs) > 0;
        db.close();
        return updateSuccessful;
    }
    public boolean delete(int id) {
        boolean deleteSuccessful = false;
        SQLiteDatabase db = this.getWritableDatabase();
        deleteSuccessful = db.delete("students", "id ='" + id + "'", null) > 0;
        db.close();
        return deleteSuccessful;
    }
    public boolean columnExists(int studentId) {
        String sql = "SELECT EXISTS (SELECT * FROM students WHERE id='"+studentId+"' LIMIT 1)";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        // cursor.getInt(0) is 1 if column with value exists
        if (cursor.getInt(0) == 1) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }
}
