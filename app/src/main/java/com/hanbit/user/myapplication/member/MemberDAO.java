package com.hanbit.user.myapplication.member;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by 1027 on 2016-07-16.
 */
public class MemberDAO extends SQLiteOpenHelper {
    public static final String ID="id";
    public static final String PW="PW";
    public static final String NAME="name";
    public static final String EMAIL="email";
    public static final String PHONE="phone";
    public static final String PHOTO="photo";
    public static final String ADDR="addr";
    public static final String TABLE_NAME="member";
    public MemberDAO(Context context) {
        super(context, "hanbitdb", null, 1);
        this.getWritableDatabase();
        Log.d("DAO진입여부","====OK====");
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table if not exists "+TABLE_NAME+" ( "
                +"id text primary key, "
                +"pw text, "
                +"name text, "
                +"email text, "
                +"phone text, "
                +"photo text, "
                +"addr text); ");
        db.execSQL("insert into member(id, pw, name, email, phone, photo, addr) values ('hong1', '1', '홍일동', 'hong1@gmail.com','010-1234-5678','--','서울1' );");
        db.execSQL("insert into member(id, pw, name, email, phone, photo, addr) values ('hong2', '1', '홍이동', 'hong2@gmail.com','010-1234-5678','--','서울2' );");
        db.execSQL("insert into member(id, pw, name, email, phone, photo, addr) values ('hong3', '1', '홍삼동', 'hong3@gmail.com','010-1234-5678','--','서울3' );");
        db.execSQL("insert into member(id, pw, name, email, phone, photo, addr) values ('hong4', '1', '홍사동', 'hong4@gmail.com','010-1234-5678','--','서울4' );");
        db.execSQL("insert into member(id, pw, name, email, phone, photo, addr) values ('hong5', '1', '홍오동', 'hong5@gmail.com','010-1234-5678','--','서울5' );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_NAME);
        this.onCreate(db);
    }
    public void insert(MemberBean bean){
        String sql = "insert into member( "
                +"id,pw,name,email,phone,photo,addr) "
                +"values( "
                +"'"+bean.getId()+"','"+bean.getPw()+"','"+bean.getName()+"','"+bean.getEmail()+"', "
                +"'"+bean.getPhone()+"','"+bean.getPhoto()+"','"+bean.getAddr()+"'); "
                ;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
        db.close();
    }
    public MemberBean login(MemberBean param){
        MemberBean member = new MemberBean();
        SQLiteDatabase db = this.getReadableDatabase();
        Log.d("DB진입 전 ID : ",param.getId());
        Cursor cursor = db.rawQuery("select * from member " +
                "where id = '"+param.getId()+"' and pw = '"+param.getPw()+"';",null);
        if(cursor.moveToNext()){
            member.setId(cursor.getString(1));
            member.setPw(cursor.getString(2));
        }else{
            member.setId("fail");
        }
        Log.d("DB결과 ID : ",member.getId());

        return member;
    }
    public int count(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select count(*) as count from member;";
        Cursor cursor = db.rawQuery(sql,null);
        int count = 0;
        if(cursor.moveToNext()){
            count = cursor.getInt(cursor.getColumnIndex("count"));
        }
        return count;
    }
    public ArrayList<MemberBean> list(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select "
                +String.format("%s,%s,%s,%s,%s,%s,%s",
                ID,PW,NAME,EMAIL,PHONE,PHOTO,ADDR
        )
                +" from member;";
        Cursor cursor = db.rawQuery(sql,null);
        ArrayList<MemberBean> templist = new ArrayList<MemberBean>();
        if(cursor != null){
            Log.d("DAO LIST 조회결과 : ","SUCCESS");
            cursor.moveToFirst();
        }
        do{
            MemberBean temp = new MemberBean();
            temp.setId(cursor.getString(0));
            temp.setPw(cursor.getString(1));
            temp.setName(cursor.getString(2));
            temp.setEmail(cursor.getString(3));
            temp.setPhone(cursor.getString(4));
            temp.setPhoto(cursor.getString(5));
            temp.setAddr(cursor.getString(6));
            templist.add(temp);
        }while(cursor.moveToNext());
        return templist;
    }
    public ArrayList<MemberBean> findByName(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from "+TABLE_NAME+" where "+NAME+" = '"+name+"';";
        Cursor cursor = db.rawQuery(sql,null);
        ArrayList<MemberBean> templist = new ArrayList<MemberBean>();
        while(cursor.moveToNext()){
            MemberBean temp = new MemberBean();
            temp.setId(cursor.getString(cursor.getColumnIndex(ID)));
            temp.setPw(cursor.getString(cursor.getColumnIndex(PW)));
            temp.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            temp.setEmail(cursor.getString(cursor.getColumnIndex(EMAIL)));
            temp.setPhone(cursor.getString(cursor.getColumnIndex(PHONE)));
            temp.setPhoto(cursor.getString(cursor.getColumnIndex(PHOTO)));
            temp.setAddr(cursor.getString(cursor.getColumnIndex(ADDR)));
            templist.add(temp);
        }
        return templist;
    }
    public MemberBean findById(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select "
                +String.format("%s,%s,%s,%s,%s,%s,%s",
                ID,PW,NAME,EMAIL,PHONE,PHOTO,ADDR)
                +" from "
                +TABLE_NAME
                +" where "+ID+" = '"+id+"';";
        Cursor cursor = db.rawQuery(sql,null);
        MemberBean temp = null;
        if(cursor.moveToNext()){
            temp = new MemberBean();
            temp.setId(cursor.getString(0));
            temp.setPw(cursor.getString(1));
            temp.setName(cursor.getString(2));
            temp.setEmail(cursor.getString(3));
            temp.setPhone(cursor.getString(4));
            temp.setPhoto(cursor.getString(5));
            temp.setAddr(cursor.getString(6));
        }
        return temp;
    }
    public void update(MemberBean bean){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql="update "+TABLE_NAME+" set " +
                PW+"='"+bean.getPw()+"',"+EMAIL+"='"+bean.getEmail()+"',"
                +PHOTO+"='"+bean.getPhoto()+"',"+ADDR+"='"+bean.getAddr()+"'" +
                "where "+ID+"='"+bean.getId()+"';";
        db.execSQL(sql);
        db.close();
    }
    public void delete(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "delete from "+TABLE_NAME+" where "+ID+"='"+id+"';";
        db.execSQL(sql);
        db.close();
    }

}
/*
public class MemberDAO extends SQLiteOpenHelper {
    public static final String ID="id";
    public static final String PW="PW";
    public static final String NAME="name";
    public static final String EMAIL="email";
    public static final String PHONE="phone";
    public static final String PHOTO="photo";
    public static final String ADDR="addr";
    public static final String TABLE_NAME="member";
    public MemberDAO(Context context) {
        super(context, "hanbitdb", null, 1);
        this.getWritableDatabase();
        Log.d("DAO진입여부","====OK====");
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table if not exists "+TABLE_NAME+" ( "
                +"id text primary key, "
                +"pw text, "
                +"name text, "
                +"email text, "
                +"phone text, "
                +"photo text, "
                +"addr text); ");
        db.execSQL("insert into member(id, pw, name, email, phone, photo, addr) values ('hong1', '1', '홍일동', 'hong1@gmail.com','010-1234-5678','--','서울1' );");
        db.execSQL("insert into member(id, pw, name, email, phone, photo, addr) values ('hong2', '1', '홍이동', 'hong2@gmail.com','010-1234-5678','--','서울2' );");
        db.execSQL("insert into member(id, pw, name, email, phone, photo, addr) values ('hong3', '1', '홍삼동', 'hong3@gmail.com','010-1234-5678','--','서울3' );");
        db.execSQL("insert into member(id, pw, name, email, phone, photo, addr) values ('hong4', '1', '홍사동', 'hong4@gmail.com','010-1234-5678','--','서울4' );");
        db.execSQL("insert into member(id, pw, name, email, phone, photo, addr) values ('hong5', '1', '홍오동', 'hong5@gmail.com','010-1234-5678','--','서울5' );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_NAME);
        this.onCreate(db);
    }
    public void insert(MemberBean bean){
        String sql = "insert into member( "
                +"id,pw,name,email,phone,photo,addr) "
                +"values( "
                +"'"+bean.getId()+"','"+bean.getPw()+"','"+bean.getName()+"','"+bean.getEmail()+"', "
                +"'"+bean.getPhone()+"','"+bean.getPhoto()+"','"+bean.getAddr()+"'); "
                ;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
        db.close();
    }
    public MemberBean login(MemberBean param){
        MemberBean member = new MemberBean();
        SQLiteDatabase db = this.getReadableDatabase();
        Log.d("DB진입 전 ID : ",param.getId());
        Cursor cursor = db.rawQuery("select * from member " +
                "where id = '"+param.getId()+"' and pw = '"+param.getPw()+"';",null);
        if(cursor.moveToNext()){
            member.setId(cursor.getString(1));
            member.setPw(cursor.getString(2));
        }else{
            member.setId("fail");
        }
        Log.d("DB결과 ID : ",member.getId());

        return member;
    }
    public int count(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select count(*) as count from member;";
        Cursor cursor = db.rawQuery(sql,null);
        int count = 0;
        if(cursor.moveToNext()){
            count = cursor.getInt(cursor.getColumnIndex("count"));
        }
        return count;
    }
    public ArrayList<MemberBean> list(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select "
                +String.format("%s,%s,%s,%s,%s,%s,%s",
                ID,PW,NAME,EMAIL,PHONE,PHOTO,ADDR
        )
                +" from member;";
        Cursor cursor = db.rawQuery(sql,null);
        ArrayList<MemberBean> templist = new ArrayList<MemberBean>();
        if(cursor != null){
            Log.d("DAO LIST 조회결과 : ","SUCCESS");
            cursor.moveToFirst();
        }
        do{
            MemberBean temp = new MemberBean();
            temp.setId(cursor.getString(0));
            temp.setPw(cursor.getString(1));
            temp.setName(cursor.getString(2));
            temp.setEmail(cursor.getString(3));
            temp.setPhone(cursor.getString(4));
            temp.setPhoto(cursor.getString(5));
            temp.setAddr(cursor.getString(6));
            templist.add(temp);
        }while(cursor.moveToNext());
        return templist;
    }
    public ArrayList<MemberBean> findByName(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from "+TABLE_NAME+" where "+NAME+" = '"+name+"';";
        Cursor cursor = db.rawQuery(sql,null);
        ArrayList<MemberBean> templist = new ArrayList<MemberBean>();
        while(cursor.moveToNext()){
            MemberBean temp = new MemberBean();
            temp.setId(cursor.getString(cursor.getColumnIndex(ID)));
            temp.setPw(cursor.getString(cursor.getColumnIndex(PW)));
            temp.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            temp.setEmail(cursor.getString(cursor.getColumnIndex(EMAIL)));
            temp.setPhone(cursor.getString(cursor.getColumnIndex(PHONE)));
            temp.setPhoto(cursor.getString(cursor.getColumnIndex(PHOTO)));
            temp.setAddr(cursor.getString(cursor.getColumnIndex(ADDR)));
            templist.add(temp);
        }
        return templist;
    }
    public MemberBean findById(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select "
                +String.format("%s,%s,%s,%s,%s,%s,%s",
                ID,PW,NAME,EMAIL,PHONE,PHOTO,ADDR)
                +" from "
                +TABLE_NAME
                +" where "+ID+" = '"+id+"';";
        Cursor cursor = db.rawQuery(sql,null);
        MemberBean temp = null;
        if(cursor.moveToNext()){
            temp = new MemberBean();
            temp.setId(cursor.getString(0));
            temp.setPw(cursor.getString(1));
            temp.setName(cursor.getString(2));
            temp.setEmail(cursor.getString(3));
            temp.setPhone(cursor.getString(4));
            temp.setPhoto(cursor.getString(5));
            temp.setAddr(cursor.getString(6));
        }
        return temp;
    }
    public void update(MemberBean bean){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql="update "+TABLE_NAME+" set " +
                PW+"='"+bean.getPw()+"',"+EMAIL+"='"+bean.getEmail()+"',"
                +PHOTO+"='"+bean.getPhoto()+"',"+ADDR+"='"+bean.getAddr()+"'" +
                "where "+ID+"='"+bean.getId()+"';";
        db.execSQL(sql);
        db.close();
    }
    public void delete(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "delete from "+TABLE_NAME+" where "+ID+"='"+id+"';";
        db.execSQL(sql);
        db.close();
    }

}

/*
public class MemberDAO extends SQLiteOpenHelper {
    public static final String ID = "id";
    public static final String PW = "pw";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";
    public static final String PHOTO = "photo";
    public static final String ADDR = "addr";
    public static final String TABLE_NAME = "member";


    // DB관련 상수 선언
    public MemberDAO(Context context) {
        super(context, "member.db", null, 1);
    }

    public void insert(MemberBean memberBean) {
        String sql = "insert into member(id, pw, name, email, phone, photo, addr) values( "
                + "'" + memberBean.getId() + "','" + memberBean.getPw() + "','" + memberBean.getName() + "','" + memberBean.getEmail()
                + "','" + memberBean.getPhone() + "','" + memberBean.getPhoto() + "','" + memberBean.getAddr() + "');";
        SQLiteDatabase db =  this.getWritableDatabase();
        db.execSQL(sql);
        db.close();
    }

    public int count() {
        SQLiteDatabase db =  this.getReadableDatabase();
        String sql = "select count(*) as count from member;";
        Cursor cursor = db.rawQuery(sql,null);
        int count =0;
        if(cursor.moveToNext()){
            count = cursor.getInt(cursor.getColumnIndex("count"));

        }
        return count;
    }

    public List<MemberBean> show() {
        SQLiteDatabase db =  this.getReadableDatabase();
        String sql = "select * from member";
        Cursor cursor = db.rawQuery(sql,null);
        List<MemberBean> templist = new ArrayList<MemberBean>();
        while(cursor.moveToNext()){
            MemberBean temp = new MemberBean();
            temp.setId(cursor.getString(cursor.getColumnIndex(ID)));
            temp.setPw(cursor.getString(cursor.getColumnIndex(PW)));
            temp.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            temp.setEmail(cursor.getString(cursor.getColumnIndex(EMAIL)));
            temp.setPhone(cursor.getString(cursor.getColumnIndex(PHONE)));
            temp.setPhoto(cursor.getString(cursor.getColumnIndex(PHOTO)));
            temp.setAddr(cursor.getString(cursor.getColumnIndex(ADDR)));
            templist.add(temp);
        }
        return templist;
    }

    public List<MemberBean> findByName(String name) {
        SQLiteDatabase db =  this.getReadableDatabase();
        String sql = "select * from " + TABLE_NAME + " where " + ID + "= '" + name + "' ;";
        Cursor cursor = db.rawQuery(sql,null);
        List<MemberBean> templist = new ArrayList<MemberBean>();
        while(cursor.moveToNext()){
            MemberBean temp = new MemberBean();
            temp.setId(cursor.getString(cursor.getColumnIndex(ID)));
            temp.setPw(cursor.getString(cursor.getColumnIndex(PW)));
            temp.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            temp.setEmail(cursor.getString(cursor.getColumnIndex(EMAIL)));
            temp.setPhone(cursor.getString(cursor.getColumnIndex(PHONE)));
            temp.setPhoto(cursor.getString(cursor.getColumnIndex(PHOTO)));
            temp.setAddr(cursor.getString(cursor.getColumnIndex(ADDR)));
            templist.add(temp);
        }
        return templist;
    }

    public boolean update(MemberBean memberBean) {
        SQLiteDatabase db =  this.getWritableDatabase();
        String sql = "update " + TABLE_NAME + " set " + PW + "= '" + memberBean.getPw() + "'," + EMAIL + " = " +
                "'" + memberBean.getEmail() + "', " + PHOTO + "= '" + memberBean.getPhoto() + "', " + ADDR + "= '" + memberBean.getAddr() +
                "' where id = '" + memberBean.getId() + "';";
        db.execSQL(sql);
        Cursor cursor = db.rawQuery(sql, null);
        MemberBean temp = new MemberBean();
        if(cursor.moveToNext()){
            temp.setId(cursor.getString(cursor.getColumnIndex(ID)));
            temp.setPw(cursor.getString(cursor.getColumnIndex(PW)));
            temp.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            temp.setEmail(cursor.getString(cursor.getColumnIndex(EMAIL)));
            temp.setPhone(cursor.getString(cursor.getColumnIndex(PHONE)));
            temp.setPhoto(cursor.getString(cursor.getColumnIndex(PHOTO)));
            temp.setAddr(cursor.getString(cursor.getColumnIndex(ADDR)));
        }else{
            temp.setId("none");
        }
        db.close();
        return true;
    }

    public void delete(String id) {
        SQLiteDatabase db =  this.getWritableDatabase();
        String sql = "delete from member where id ='" + id + "';";
        db.execSQL(sql);
        db.close();
    }

    public MemberBean findByID(String id) {
        SQLiteDatabase db =  this.getReadableDatabase();
        String sql = "select * from " + TABLE_NAME + " where " + ID + "= '" + id + "' ;";
        Cursor cursor = db.rawQuery(sql,null);
        MemberBean temp = new MemberBean();
        if(cursor.moveToNext()){
            temp.setId(cursor.getString(cursor.getColumnIndex(ID)));
            temp.setPw(cursor.getString(cursor.getColumnIndex(PW)));
            temp.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            temp.setEmail(cursor.getString(cursor.getColumnIndex(EMAIL)));
            temp.setPhone(cursor.getString(cursor.getColumnIndex(PHONE)));
            temp.setPhoto(cursor.getString(cursor.getColumnIndex(PHOTO)));
            temp.setAddr(cursor.getString(cursor.getColumnIndex(ADDR)));
        }else{
            temp.setId("none");
        }
        return temp;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists " + TABLE_NAME + "  (id text primary key, pw text, name text, email text, phone text, photo text, addr text);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists member");
        this.onCreate(db);

    }

    public MemberBean login(MemberBean param) {
        MemberBean memberBean = new MemberBean();
        //SQLiteDatabaseHelper helper = new MyDatabaseHelper(this);
        try {
            SQLiteDatabase db = this.getReadableDatabase();

            Log.d("DB진입전 ID", param.getId());
            //디비릴 읽을 수 있는 권한 주기
            Cursor cursor = db.rawQuery("select * from member where id = '" + param.getId() + "' and pw ='" + param.getPw() + "'", null);
            //Cursor cursor = db.rawQuery("select * from member", null);
            if (cursor.moveToNext()) {
                memberBean.setId(cursor.getString(0));
                memberBean.setPw(cursor.getString(1));
            } else {
                memberBean.setId("fail");
                Log.d("로그인 실패", "다시하세요");
            }
        } catch (Exception ex) {
            Log.d("error", ex.getMessage());
        }
      //  Log.d("DB의 결과 ID", memberBean.getId());
        return memberBean;
    }
}
*/