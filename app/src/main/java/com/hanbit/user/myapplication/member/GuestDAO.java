package com.hanbit.user.myapplication.member;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by 1027 on 2016-07-30.
 */
public class GuestDAO extends SQLiteOpenHelper {
    public GuestDAO(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "hanbitdb", null, 1);
    }

    public GuestDAO(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, "hanbitdb", factory, 1, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.getWritableDatabase();
        String sql = "create table guest() ( _id integer primary key autoincrement name text, phone text);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists guest;");
        this.onCreate(db);
    }

    public void add(MemberBean guest) {
        String sql = "insert into guest(name, phone)" + String.format("values('%s', '%s');", guest.getName(), guest.getPhone());
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
    }

    public ArrayList<MemberBean> list() {
        ArrayList<MemberBean> list = new ArrayList<MemberBean>();
        String sql = "select _id as, name, phone id from guest;";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            Log.d("친구 목록 조회", "SUCCESS");
            cursor.moveToFirst();
            do {
                MemberBean temp = new MemberBean();
                temp.setId(String.valueOf(cursor.getInt(0)));
                temp.setName(cursor.getString(1));
                temp.setPhone(cursor.getString(2));
                list.add(temp);
            } while (cursor.moveToNext());
        } else {
            Log.d("친구 목록 실패", "FAIL");
        }

        return list;
    }
}
