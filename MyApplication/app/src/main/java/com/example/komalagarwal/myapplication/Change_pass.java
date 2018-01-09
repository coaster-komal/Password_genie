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


public class Change_pass extends ActionBarActivity {
    EditText et,et1;
    DBHelperregister mydb;
    Button bt;
    Intent tohome;
    public static final String MyPREFERENCESfp = "MyPrefsfp" ;
    public static final String nameforfp = "namefp";
    SharedPreferences sharedPreferencesfp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        sharedPreferencesfp = getSharedPreferences(MyPREFERENCESfp, Context.MODE_PRIVATE);

        addListenerOnButton();

    }

    private void addListenerOnButton()
    {
        mydb = new DBHelperregister(this);
        et=(EditText)findViewById(R.id.et);
        et1=(EditText)findViewById(R.id.et1);
        bt=(Button)findViewById(R.id.bt);

    bt.setOnClickListener(new View.OnClickListener() {
        public void onClick(View view) {

           String s="",s1="";
            s = et.getText().toString();
            s1 = et1.getText().toString();
            int a = 0, b = 0;

            if (s.equals("") && s1.equals("") ) {
                b = 1;
                Toast.makeText(getApplicationContext(), "FILL ALL FIELDS", Toast.LENGTH_LONG).show();
            }
            if ((s.length()) < 6 && b==0) {
                b = 1;
                Toast.makeText(getApplicationContext(), "PASSWORD LENGTH SHOULD BE MINIMUM 6", Toast.LENGTH_LONG).show();
            }
            if (s.length() >= 6) {
                a = passverify(s);
                System.out.println("value of a=" + a);
                if (a == 0)
                    Toast.makeText(getApplicationContext(), "PASSWORD SHOULD CONTAIN AT LEAST 1 NUMBER, 1 CAPITAL CHARACTER & 1 SMALL CHARACTERS ", Toast.LENGTH_LONG).show();
                else {
                    b = 0;
                    Toast.makeText(getApplicationContext(), "PASSWORD VERIFIED ", Toast.LENGTH_LONG).show();
                }
            }
            if (!s.equals(s1) && b==0) {
                b = 1;
                Toast.makeText(getApplicationContext(), "PASSWORD DOES NOT MATCHED", Toast.LENGTH_LONG).show();
            }

            if (b == 0) {
                String x="";
                x=sharedPreferencesfp.getString(nameforfp,"");
                mydb.updatepass(x,s);
                Toast.makeText(getApplicationContext(), "PASSWORD CHANGED!!", Toast.LENGTH_LONG).show();
                tohome = new Intent(Change_pass.this, Login.class);
                startActivity(tohome);
            }
        }

    });
}

    private int passverify(String s1)
    {
        int flag=0;
        for(int i=0;i<s1.length();i++)
        {
            for(int j=48;j<=57;j++)
            {
                if(s1.charAt(i)==j)
                {
                    flag=1;
                    break;
                }
            }
        }
        System.out.println("flag after 1st loop="+flag);
        if(flag==1) {
            for (int i = 0; i < s1.length(); i++)
            {
                for(int j=65;j<=90;j++)
                {
                    if(s1.charAt(i)==j)
                    {
                        flag=2;
                        break;
                    }
                }
            }
        }
        System.out.println("flag after 2nd loop="+flag);
        if(flag==2)
        {
            for (int i = 0; i < s1.length(); i++)
            {
                for(int j=97;j<=122;j++)
                {
                    if(s1.charAt(i)==j)
                    {
                        flag=3;
                        break;
                    }
                }
            }
        }
        System.out.println("flag after 3rd loop="+flag);
        char s=' ';
        boolean sp=false;
        if(flag==3)
        {
            for(int i=0;i<s1.length();i++)
            {
                if(s1.charAt(i)==s)
                {
                    sp=true;

                }

            }
            if(!sp)
            {
                flag=4;
            }

        }
        System.out.println("flag after 4th loop="+flag);

        if(flag==4)
            return 1;

        else
            return 0;


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_change_pass, menu);
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
