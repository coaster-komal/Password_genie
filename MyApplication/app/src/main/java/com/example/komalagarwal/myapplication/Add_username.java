package com.example.komalagarwal.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Add_username extends ActionBarActivity {

    EditText et,et1,et2;
    DBHelperaccount mydb;
    Button bt;
    Intent tohome;
    public static final String MyPREFERENCESun = "MyPrefsun" ;
    public static final String Name = "nameKeyun";
    SharedPreferences sharedpreferencesun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_username);
        sharedpreferencesun = getSharedPreferences(MyPREFERENCESun, Context.MODE_PRIVATE);
        addListenerOnButton();

    }
    private void addListenerOnButton() {
        bt=(Button)findViewById(R.id.add);
        mydb= new DBHelperaccount(this);
        et=(EditText)findViewById(R.id.uname);
        et1=(EditText)findViewById(R.id.pass);
        et2=(EditText)findViewById(R.id.service);
        bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String s="", s1="", s2="", s3="",s4="",s5="";
                s = et.getText().toString();
                s1 = et1.getText().toString();
                s2 = et2.getText().toString();
                if(s.equals("")||s1.equals("")||s2.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "FILL ALL FIELDS", Toast.LENGTH_LONG).show();
                }
                else {
                    s3 = encryption(s1);
                    s5 = sharedpreferencesun.getString(Name, "");
                    System.out.println("Name =" + s5 + " username=" + s + " password=" + s3 + " service name=" + s2);
                    mydb.insert(s5, s, s3, s2);
                    tohome = new Intent(Add_username.this, Home_Page.class);
                    startActivity(tohome);
                }
            }

        });
    }
    public String encryption(String str)
    {
        String str1="",str2="",str3="";
        Boolean b=false;
        char d='a';
        int i,len,len1;
        len=str.length();
        int a=1;
        for(i=0;i<len;i++)
        {
            char ch=str.charAt(i);
            d=(char)(ch+(2*a));
            a=a*(-1);
            str1=str1+d;
        }
        int mid=len/2;
        for(i=(mid-1);i>=0;i--)
        {
            char ch=str1.charAt(i);
            str2=str2+ch;
        }
        for(i=len-1;i>=(mid);i--)
        {
            char ch=str1.charAt(i);
            str3=str3+ch;
        }
        len1=str3.length();
        for(i=0;i<len1;i++)
        {
            char ch=str3.charAt(i);
            str2=str2+ch;
        }
        System.out.println("encrypted "+str2);
        return str2;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_username, menu);
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
