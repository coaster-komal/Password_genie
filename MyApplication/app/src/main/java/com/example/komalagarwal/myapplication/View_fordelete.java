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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class View_fordelete extends ActionBarActivity {
    public static final String MyPREFERENCESun = "MyPrefsun";
    public static final String Name = "nameKeyun";
    SharedPreferences sharedpreferencesun;
    public static final String MyPREFERENCESsn = "MyPrefssn";
    public static final String SERVICEName = "nameKeysn";
    SharedPreferences sharedpreferencessn;
    DBHelperaccount mydb;
    Spinner sp;
    Button b,b1;
    TextView et,et1;
    Intent tomass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_fordelete);
        mydb=new DBHelperaccount(this);
        sharedpreferencesun = getSharedPreferences(MyPREFERENCESun, Context.MODE_PRIVATE);
        sharedpreferencessn = getSharedPreferences(MyPREFERENCESsn, Context.MODE_PRIVATE);
        String s="",s1="",s3="";
        String[] s2;
        s2=new String[]{};
        s=sharedpreferencesun.getString(Name,"");
        s3=sharedpreferencessn.getString(SERVICEName,"");
        sp=(Spinner)findViewById(R.id.sp);
        Cursor c=null;
        c=mydb.getusername(s,s3);
        int countr=0;
        c.moveToFirst();
        while(c.moveToNext())
        {
            countr++;
        }
        s2=new String[countr];
        int i=0;
        c.moveToFirst();
        while(c.moveToNext())
        {

            s1 = c.getString(c.getColumnIndex(DBHelperaccount.ACCOUNT_COLUMN_USERNAME));
            s2[i]=s1;
            i++;
        }
        for(int j=0;j<countr;j++)
        {
            System.out.println(s2[j]);
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,s2);
        sp.setAdapter(adapter);
        addListenerOnButton();
    }
    private void addListenerOnButton() {
        b = (Button) findViewById(R.id.view);
        et=(TextView)findViewById(R.id.un);
        et1=(TextView)findViewById(R.id.pass);
        b1=(Button)findViewById(R.id.save);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String s="";
                s=sp.getSelectedItem().toString();
                System.out.println("USERNAME FRM SPINNER =" + s);
                et.setText(s);
                String s1 = "",s2="";
                s2 = sharedpreferencesun.getString(Name, "");
                Cursor c = null;
                c = mydb.getpass(s2, s);
                c.moveToFirst();
                s1 = c.getString(c.getColumnIndex(DBHelperaccount.ACCOUNT_COLUMN_PASSWORD));
                System.out.println("PASSWORD frm database =" + s1);
                String pass = "";
                pass = decryption(s1);
                System.out.println("PASSWORD AFTER DECRYPTION=" + pass);
                et1.setText(pass);

            }

        });
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String s="",s1="",s2="",s3="";
                s=et.getText().toString();
                s1=et1.getText().toString();
                s2 = sp.getSelectedItem().toString();
                int a=0;
                s3=sharedpreferencesun.getString(Name,"");
                if(s2.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "SELECT USERNAME", Toast.LENGTH_LONG).show();
                }
                else {
                    a = mydb.delete(s3, s2);
                    if (a == 1) {
                        Toast.makeText(getApplicationContext(), "SUCCESSFULLY DELETED", Toast.LENGTH_LONG).show();
                        tomass = new Intent(View_fordelete.this, Home_Page.class);
                        startActivity(tomass);
                    }
                }
            }

        });
    }
    public String decryption(String s)
    {
        String str="",str1="",str2="";
        int a,i;
        char d;
        int len=s.length();
        int mid=len/2;
        for(i=(mid-1);i>=0;i--)
        {
            char ch=s.charAt(i);
            str=str+ch;
        }
        for(i=len-1;i>=(mid);i--)
        {
            char ch=s.charAt(i);
            str1=str1+ch;
        }
        int len1=str1.length();
        for(i=0;i<len1;i++)
        {
            char ch=str1.charAt(i);
            str=str+ch;
        }
        System.out.println("before dec"+str);
        a=-1;
        for(i=0;i<len;i++)
        {
            char ch=str.charAt(i);
            d=(char)(ch+(2*a));
            System.out.println("car "+d);
            a=a*(-1);
            str2=str2+d;
        }
        System.out.println("decrypted"+str2);
        return str2;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_fordelete, menu);
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
