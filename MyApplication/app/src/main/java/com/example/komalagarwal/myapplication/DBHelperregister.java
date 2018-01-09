package com.example.komalagarwal.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;

/**
 * Created by komalagarwal on 3/15/2016.
 */
public class DBHelperregister extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String REGISTER_TABLE_NAME = "register";
    public static final String REGISTER_COLUMN_USERNAME = "username";
    public static final String REGISTER_COLUMN_PASSWORD = "password";
    public static final String REGISTER_COLUMN_MASTERPASS = "mpass";
    public static final String REGISTER_COLUMN_PHONE = "phone";
    public static final String REGISTER_COLUMN_EMAIL = "email";
    public static final String REGISTER_COLUMN_CODE = "code";
    public static final String REGISTER_COLUMN_VERIFY = "verify";

    private HashMap hp;
    public DBHelperregister(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // TODO Auto-generated method stub
        try {

            db.execSQL("create table register (username text primary key,password text,mpass text,phone text,email text,code text,verify text)");
        }
        catch(Exception ev)
        {
            System.out.println(ev.getMessage());
        }
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS register");
        //     db.execSQL("DROP TABLE IF EXISTS trans");
        onCreate(db);
    }
    public boolean insertContact (String name, String password, String mpass, String phone, String email,String code,String verify)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", name);
        contentValues.put("password", password);
        contentValues.put("mpass", mpass);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("code", code);
        contentValues.put("verify", verify);
        db.insert("register", null, contentValues);
        System.out.println("VALUES INSERTED IN TABLE");
        return true;
    }
    public boolean update (String name,String verify)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String s="";
        s="TRUE";
        contentValues.put("verify",s );
        db.update("register", contentValues, " username=? "  , new String[] { name} );
        return true;
    }
    public boolean updatepass (String name,String pass)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password",pass );
        db.update("register", contentValues, " username=? "  , new String[] { name} );
        return true;
    }
    public boolean updatempass (String name,String mpass)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("mpass",mpass );
        db.update("register", contentValues, " username=? "  , new String[] { name} );
        return true;
    }
    public boolean updatedata (String name,String phone,String email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("phone",phone);
        contentValues.put("email",email);
        db.update("register", contentValues, " username=? "  , new String[] { name} );
        return true;
    }
    public Cursor getpass(String uname)
    {

        Cursor res=null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
             res = db.rawQuery("select password from register where username='" + uname + "'", null);

        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return res;
    }
    public Cursor getmobno(String uname)
    {

        Cursor res=null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            res = db.rawQuery("select phone from register where username='" + uname + "'", null);

        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return res;
    }
    public Cursor getemailid(String uname)
    {

        Cursor res=null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            res = db.rawQuery("select email from register where username='" + uname + "'", null);

        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return res;
    }
    public Cursor getverify(String uname)
    {
        Cursor res =null;
        SQLiteDatabase db = this.getReadableDatabase();

              res=  db.rawQuery("select verify from register where username='" + uname + "'", null);
        return res;
    }
    public Cursor getmasterpass(String uname)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = null;
           res=     db.rawQuery("select mpass from register where username='" + uname + "'", null);
        return res;
    }
    public Cursor getid()
    {
        Cursor r=null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            r = db.rawQuery("select max(code) from register", null);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return r;
    }
    public Cursor getid1(String uname)
    {
        Cursor r=null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            r = db.rawQuery("select max(code) from register", null);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return r;
    }
}
