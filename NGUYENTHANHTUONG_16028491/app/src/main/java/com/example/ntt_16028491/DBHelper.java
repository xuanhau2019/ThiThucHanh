package com.example.ntt_16028491;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "ThuongKy2", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE EMPLOYEES (id_employee integer primary key, " +
                "name_employee text," +
                "gioiTinh_employee text," +
                "phone_number_employee integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS EMPLOYEES");
    }

    public int insertEmployee(ArrayList<Employee> list){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues con = new ContentValues();
        for (Employee e : list){
            con.put("id_employee", e.getId());
            con.put("name_employee", e.getName());
            con.put("gioiTinh_employee", e.getGioiTinh());
            con.put("phone_number_employee", e.getPhoneNumber());
            db.insert("EMPLOYEES", null, con);
        }
        db.close();
        return 1;
    }

    public ArrayList<Employee> getAllEmployee(){
        ArrayList<Employee> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM EMPLOYEES", null);
        if(cursor != null){
            cursor.moveToFirst();
        } while (cursor.isAfterLast() == false){
            list.add(new Employee(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3)));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }
}
