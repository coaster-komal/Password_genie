package com.example.komalagarwal.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class View_acc extends ActionBarActivity {
    public static final String MyPREFERENCESun = "MyPrefsun";
    public static final String Name = "nameKeyun";
    SharedPreferences sharedpreferencesun;
    DBHelperregister mydb;
    TextView t,t1,t2;
    Button bt;
    Intent tohome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_acc);
        sharedpreferencesun = getSharedPreferences(MyPREFERENCESun, Context.MODE_PRIVATE);
        mydb=new DBHelperregister(this);
        String s="",s1="",s2="";
        s=sharedpreferencesun.getString(Name,"");
        Cursor c=null;
        c=mydb.getmobno(s);
        c.moveToFirst();
        s1 = c.getString(c.getColumnIndex(DBHelperregister.REGISTER_COLUMN_PHONE));
        Cursor c1=null;
        c1=mydb.getemailid(s);
        c1.moveToFirst();
        s2 = c1.getString(c1.getColumnIndex(DBHelperregister.REGISTER_COLUMN_EMAIL));
        t=(TextView)findViewById(R.id.t);
        t1=(TextView)findViewById(R.id.t1);
        t2=(TextView)findViewById(R.id.t2);
        t.setText(s);
        t1.setText(s1);
        t2.setText(s2);


        addListenerOnButton();
    }
    private void addListenerOnButton() {
        bt = (Button) findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                tohome = new Intent(View_acc.this, Home_Page.class);
                startActivity(tohome);

            }

        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_acc, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
