package com.lidong.dbdome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidong.dbdome.dao.User;
import com.lidong.dbdome.dao.UserDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText ed_name;
    private EditText ed_sex;
    private Button btn_insert;
    private Button btn_delete;
    private Button btn_delete_all;
    private Button btn_update;
    private TextView tv_date;
    private UserDao userDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed_name = findViewById(R.id.ed_name);
        ed_sex = findViewById(R.id.ed_sex);
        btn_insert = findViewById(R.id.btn_insert);
        btn_delete = findViewById(R.id.btn_delete);
        btn_delete_all = findViewById(R.id.btn_delete_all);
        btn_update = findViewById(R.id.btn_update);

        tv_date = findViewById(R.id.tv_date);

        btn_insert.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_delete_all.setOnClickListener(this);
        btn_update.setOnClickListener(this);
        userDao = new UserDao(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_insert:

                userDao.inserUserDao(new User(ed_name.getText().toString(), ed_sex.getText().toString()));
                tv_date.setText(new Gson().toJson(userDao.CorsorList()));
                break;
            case R.id.btn_update:

                userDao.updateUserDao(new User("hai", "男"), ed_name.getText().toString());
                tv_date.setText(new Gson().toJson(userDao.CorsorList()));
                break;
            case R.id.btn_delete:
                String name = ed_name.getText().toString().trim();
                Log.e("删除数据", "onClick: "+"删除数据" );
                userDao.deleteDate(name);
                tv_date.setText(new Gson().toJson(userDao.CorsorList()));
                break;
            case R.id.btn_delete_all:

                userDao.deleteDate();
                tv_date.setText(new Gson().toJson(userDao.CorsorList()));
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userDao.close();
    }
}
