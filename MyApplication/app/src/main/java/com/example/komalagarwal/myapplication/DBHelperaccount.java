package com.example.komalagarwal.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;

/**
 * Created by komalagarwal on 4/25/2016.
 */
public class DBHelperaccount extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MyDBAccount.db";
    public static final String ACCOUNT_TABLE_NAME = "account";
    public static final String ACCOUNT_COLUMN_NAME = "name";
    public static final String ACCOUNT_COLUMN_USERNAME = "username";
    public static final String ACCOUNT_COLUMN_PASSWORD = "password";
    public static final String ACCOUNT_COLUMN_SERVICE = "service";

    private HashMap hp;
    public DBHelperaccount(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // TODO Auto-generated method stub
        try {

            db.execSQL("create table account (name text,username text,password text,service text)");
        }
        catch(Exception ev)
        {
            System.out.println(ev.getMessage());
        }
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS account");
        onCreate(db);
    }
    public boolean insert (String name,String uname, String password,String service)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("username", uname);
        contentValues.put("password", password);
        contentValues.put("service", service);
        db.insert("account", null, contentValues);
        System.out.println("VALUES INSERTED IN TABLE");
        return true;
    }
    public Cursor getservice(String uname)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select distinct(service) from account where name='" + uname + "'", null);
        return res;
    }
    public void insertinitial(String name)
    {
        String s=" ",s1=" ",s2=" " ;
        s2="SELECT SERVICE";
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("username",s);
        contentValues.put("password", s1);
        contentValues.put("service", s2);
        db.insert("account", null, contentValues);
        System.out.println("inserted");
    }
    public void insertinitialuname(String name)
    {
        String s=" ",s1=" ",s2=" " ;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("username",s);
        contentValues.put("password", s1);
        contentValues.put("service", s2);
        db.insert("account", null, contentValues);
        System.out.println("inserted");
    }
    public Cursor getusername(String uname,String service)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String s=" ";
        Cursor res = db.rawQuery("select distinct(username) from account where name='" + uname + "' and service ='"+ service+ "' or service='" + s + "'", null);
        return res;
    }
    public Cursor getpass(String uname,String name)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select password from account where name='" + uname + "' and username ='" + name + "'", null);
        return res;
    }
    public boolean update (String name,String uname1,String uname, String pass)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String s="";
        s="TRUE";
        contentValues.put("username",uname );
        contentValues.put("password",pass );
        db.update("account", contentValues, " name=? and username=?"  , new String[] { name,uname1} );
        return true;
    }
    public Integer delete (String name,String uname)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("account", "name = ? and username=? ", new String[] { name,uname });
    }
}
