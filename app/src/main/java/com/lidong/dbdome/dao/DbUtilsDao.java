package com.lidong.dbdome.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DbUtilsDao extends SQLiteOpenHelper {

    private Context mContext;
    private final static int DB_VERSION = 1;
    private final static String TABLE_NAME = "database.db";
    private final static String TABLE_1 = "person";
    public static String CREATE_TABLE = "create table " + TABLE_1 + "(id integer primary key autoincrement" +
            " ,name varchar(30)" +
            ",address varchar(30))";

    public DbUtilsDao(Context context) {
        super(context, TABLE_NAME, null, DB_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Toast.makeText(mContext, "创建数据库", Toast.LENGTH_SHORT).show();
        db.execSQL(CREATE_TABLE);
        db.execSQL("create table user(id integer primary key autoincrement ,name varchar(30),sex varchar(4))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
