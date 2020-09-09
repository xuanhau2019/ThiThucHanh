package com.example.ntt_16028491;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

public class EmployeeActivity extends AppCompatActivity {

    private EditText edtId, edtName, edtPhoneNumber;
    private RadioButton radMale, radFemale;
    private ListView listDisplay;

    private Button btnSave, btnDelete, btnAdd, btnExit;
    Adapter adapter;
    DBHelper dbHelper;

    ArrayList<Employee> list = new ArrayList<>();
    Employee e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        edtId = (EditText) findViewById(R.id.edtId);
        edtName = (EditText) findViewById(R.id.edtName);
        edtPhoneNumber = (EditText) findViewById(R.id.edtPhoneNumber);

        radMale = (RadioButton) findViewById(R.id.radMale);
        radFemale = (RadioButton) findViewById(R.id.radFemale);

        listDisplay = (ListView) findViewById(R.id.lv_display);
        dbHelper = new DBHelper(this);
        listDisplay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                e = list.get(i);
                Toast.makeText(EmployeeActivity.this, ""+e.getId(), Toast.LENGTH_SHORT).show();
            }
        });

        btnExit = (Button) findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert();
            }
        });


        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(e == null)){
                    for (Employee employee:list){
                        if(employee.getId() == e.getId()) {
                            list.remove(employee);
                            e = null;
                            adapter.notifyDataSetChanged();
                        }
                    }
                }else{
                    Toast.makeText(EmployeeActivity.this, "Chon employee", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.insertEmployee(list);
            }
        });
    }

    public void delete() {
//        if (edtId.getText().toString().length() == 0){
//            Toast.makeText(this, "Mã không để trống", Toast.LENGTH_SHORT).show();
//        }else
//            list.remove();
    }

    public void show() {
        adapter = new Adapter(this,R.layout.row,list);
        //ArrayAdapter<Employee> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listDisplay.setAdapter(adapter);
    }

//    public void insert(){
//        Employee e = new Employee();
//        if (!list.contains(e)){
//           if (check()){
//               e.setId(Integer.parseInt(edtId.getText().toString()));
//               e.setName(edtName.getText().toString());
//               if (radMale.isChecked())
//                   e.setGioiTinh("Nam");
//               if (radFemale.isChecked()){
//                   e.setGioiTinh("Nữ");
//               }
//               e.setPhoneNumber(Integer.parseInt(edtPhoneNumber.getText().toString()));
//               list.add(e);
//               show();
//               Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
//           }
//       } else
//                Toast.makeText(this, "Trùng mã", Toast.LENGTH_SHORT).show();
//    }

    public void insert() {
        if (check()) {
            Employee e = new Employee();
            e.setId(Integer.parseInt(edtId.getText().toString()));
            e.setName(edtName.getText().toString());
            if (radMale.isChecked())
                e.setGioiTinh("Nam");
            if (radFemale.isChecked()) {
                e.setGioiTinh("Nữ");
            }
            e.setPhoneNumber(Integer.parseInt(edtPhoneNumber.getText().toString()));
            if (!list.contains(e)) {
                list.add(e);
                show();
                Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(this, "Trùng mã", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean check() {
        if (edtId.getText().toString().length() == 0) {
            Toast.makeText(this, "Mã không để trống!!!", Toast.LENGTH_SHORT).show();
            edtId.requestFocus();
        }
        if (edtName.getText().toString().length() == 0) {
            Toast.makeText(this, "Tên không để trống!!!", Toast.LENGTH_SHORT).show();
            edtName.requestFocus();
        }
        if (edtPhoneNumber.getText().toString().length() == 0) {
            Toast.makeText(this, "Địa chỉ không để trống!!!", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

}