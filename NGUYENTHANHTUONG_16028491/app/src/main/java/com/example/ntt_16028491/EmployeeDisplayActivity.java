package com.example.ntt_16028491;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import java.util.ArrayList;

public class EmployeeDisplayActivity extends AppCompatActivity {

    private EditText edtId;
    private Button btnView, btnExit;

    GridView gvDisplay;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_display);

        edtId = (EditText) findViewById(R.id.edtId);

        gvDisplay = (GridView) findViewById(R.id.gv_display);
        dbHelper = new DBHelper(this);

        btnExit = (Button) findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnView = (Button) findViewById(R.id.btnView);
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select();
            }
        });
    }

    public void select(){
        ArrayList<Employee> list = new ArrayList<>();
        ArrayList<String> list_string = new ArrayList<>();
        if(edtId.getText().toString().equals("")){
            list = dbHelper.getAllEmployee();
        }
        if (list.size() > 0){
            for (Employee e: list){
                list_string.add(e.getId()+"");
                list_string.add(e.getName());
                list_string.add(e.getGioiTinh());
                list_string.add(e.getPhoneNumber()+"");
            }
            ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list_string);
            gvDisplay.setAdapter(adapter);
        }
    }
}