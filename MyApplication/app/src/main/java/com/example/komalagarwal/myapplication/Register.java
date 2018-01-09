package com.example.komalagarwal.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class Register extends ActionBarActivity {
    EditText et,et1,et2,et3,et4,et5,et6;
    Intent tohome;
    ImageButton img;
    DBHelperregister mydb;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    public static final String code = "codeKey";
    public static final String mobno = "mobKey";
    public static final String emailid = "emailKey";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        addListenerOnButton();
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
    }

    private void addListenerOnButton()
    {
        mydb = new DBHelperregister(this);
        img = (ImageButton) findViewById(R.id.reg);
        img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                et = (EditText) findViewById(R.id.uname);
                et1 = (EditText) findViewById(R.id.pass);
                et2 = (EditText) findViewById(R.id.phone);
                et3 = (EditText) findViewById(R.id.cnfrmpass);
                et4 = (EditText) findViewById(R.id.mpass);
                et5 = (EditText) findViewById(R.id.email);
                System.out.println("name" + et);
                System.out.println("pass" + et1);
                System.out.println("phone" + et2);
                // et3=(EditText) findViewById(R.id.uname);
                String s = "", s1 = "", s2 = "", s3 = "", s4 = "", s5 = "";
                s = et.getText().toString();
//                mydbtran.insertinitial(s);
                s1 = et1.getText().toString();
                s2 = et2.getText().toString();
                s3 = et3.getText().toString();
                s4 = et4.getText().toString();
                s5 = et5.getText().toString();
                System.out.println("string name" + s);
                System.out.println("string pass" + s1);
                System.out.println("string confirm pass" + s3);
                System.out.println("master mpass"+s4);
                System.out.println("string pone" + s2);
                System.out.println("email "+s5);
                int a = 0, b = 0;

                if (s.equals("") && s1.equals("") && s2.equals("") && s3.equals("") && s4.equals("") && s5.equals("")) {
                    b = 1;
                    Toast.makeText(getApplicationContext(), "FILL ALL FIELDS", Toast.LENGTH_LONG).show();
                }
                if (s.length() < 5 && b==0) {
                    b = 1;
                    Toast.makeText(getApplicationContext(), "USERNAME LENGTH SHOULD BE MINIMUM 5", Toast.LENGTH_LONG).show();
                }
                if ((s1.length()) < 6 && b==0) {
                    b = 1;
                    Toast.makeText(getApplicationContext(), "PASSWORD LENGTH SHOULD BE MINIMUM 6", Toast.LENGTH_LONG).show();
                }
                if (s1.length() >= 6) {
                    a = passverify(s1);
                    System.out.println("value of a=" + a);
                    if (a == 0)
                        Toast.makeText(getApplicationContext(), "PASSWORD SHOULD CONTAIN AT LEAST 1 NUMBER, 1 CAPITAL CHARACTER & 1 SMALL CHARACTERS ", Toast.LENGTH_LONG).show();
                    else {
                        b = 0;
                        Toast.makeText(getApplicationContext(), "PASSWORD VERIFIED ", Toast.LENGTH_LONG).show();
                    }
                }
                if (!s3.equals(s1) && b==0) {
                    b = 1;
                    Toast.makeText(getApplicationContext(), "PASSWORD DOES NOT MATCHED", Toast.LENGTH_LONG).show();
                }
                if (s2.length() != 10 && b==0) {
                    b = 1;
                    Toast.makeText(getApplicationContext(), "ENTER CORRECT PHONE NUMBER", Toast.LENGTH_LONG).show();
                }

                    if (b == 0) {

                        int len = s2.length();
                        int len1 = s5.length();
                        String x = "", y = "";
                        y = "FALSE";
                        char z;
                        for (int i = len - 3; i < len; i++) {
                            z = s2.charAt(i);
                            x = x + z;
                        }
                        for (int i = 0; i < 3; i++) {
                            z = s5.charAt(i);
                            x = x + z;
                        }
                        System.out.println("CODE ="+ x);
                        mydb.insertContact(s, s1, s4, s2, s5, x, y);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString(Name, s);
                        editor.putString(code, x);
                        editor.putString(mobno, s2);
                        editor.putString(emailid, s5);
                        editor.commit();
                        Toast.makeText(getApplicationContext(), "VERIFY YOUR DETAILS", Toast.LENGTH_SHORT).show();
                        tohome = new Intent(Register.this, Verify.class);
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
        getMenuInflater().inflate(R.menu.menu_register, menu);
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
