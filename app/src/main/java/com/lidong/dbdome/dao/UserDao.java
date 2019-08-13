package com.lidong.dbdome.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private DbUtilsDao dbUtilsDao;

    public UserDao(Context context) {
        dbUtilsDao = new DbUtilsDao(context);
    }

    public void inserUserDao(User user) {
        SQLiteDatabase db = null;
        try {
            db = dbUtilsDao.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name", user.getName());
            values.put("sex", user.getSex());
            db.insert("user", null, values);
        } catch (Exception e) {

        } finally {
            if (null != db) {
                db.close();
            }
        }

    }

    public void updateUserDao(User user, String postion) {
        SQLiteDatabase db = null;
        try {
            db = dbUtilsDao.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name", user.getName());
            values.put("sex", user.getSex());
            db.update("user", values, "name=?", new String[]{postion});
        } catch (Exception e) {

        } finally {
            if (null != db) {
                db.close();
            }
        }
    }

    public List<User> CorsorList() {
        List<User> list = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = dbUtilsDao.getReadableDatabase();
            cursor = db.rawQuery("select id,name,sex from user ", null);
            while (cursor.moveToNext()) {
                User user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex("id")));
                user.setName(cursor.getString(cursor.getColumnIndex("name")));
                user.setSex(cursor.getString(cursor.getColumnIndex("sex")));
                list.add(user);
            }

        } catch (Exception e) {

        } finally {
            if (null != cursor) {
                cursor.close();
            }
            if (null != db) {
                db.close();
            }
        }

        return list;
    }

    public void deleteDate(String name) {
        SQLiteDatabase db = null;

        try {
            db = dbUtilsDao.getWritableDatabase();
            db.delete("user", "name=?", new String[]{name});
        } catch (Exception e) {

        } finally {
            if (null != db) {
                db.close();
            }
        }
    }


    public void deleteDate() {
        SQLiteDatabase db = null;
        try {
            db = dbUtilsDao.getWritableDatabase();
            db.delete("user", null, null);
        } catch (Exception e) {

        } finally {
            if (null != db) {
                db.close();
            }
        }
    }


    public void close() {
        dbUtilsDao.getWritableDatabase().close();
        dbUtilsDao.close();
    }

}
