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
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.jar.Attributes;


public class Login extends ActionBarActivity {
    ImageButton reg,fp;
    Button log,credit;
    Intent toreg,tohome;
    EditText et,et1;
    DBHelperregister mydb;
    DBHelperaccount mydb1;
    public static final String MyPREFERENCESun = "MyPrefsun" ;
    public static final String Name = "nameKeyun";
    SharedPreferences sharedpreferencesun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_login);
        mydb1=new DBHelperaccount(this);
        mydb=new DBHelperregister(this);
        et=(EditText)findViewById(R.id.name);
        et1=(EditText)findViewById(R.id.pass1);
        et.setText("");
        et1.setText("");

        sharedpreferencesun = getSharedPreferences(MyPREFERENCESun, Context.MODE_PRIVATE);
        addListenerOnButton();


    }

    private void addListenerOnButton()
    {


        credit=(Button)findViewById(R.id.credit);
        reg = (ImageButton) findViewById(R.id.register);
        fp=(ImageButton)findViewById(R.id.forgetpass);
        reg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


                toreg = new Intent(Login.this, Register.class);
                startActivity(toreg);
            }

        });
        credit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


                toreg = new Intent(Login.this, Credit_page.class);
                startActivity(toreg);
            }

        });
        fp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


                toreg = new Intent(Login.this, Forget_password.class);
                startActivity(toreg);
            }

        });
        log=(Button)findViewById(R.id.button);
        log.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
              //  String s="",s1="";
            //    s="komal";
           //     s1=sharedpreferences.getString(Name,"");
            //    System.out.println("name ="+s1);
              //  mydb1.insertinitial(s);
            //    tohome = new Intent(Login.this, Home_Page.class);
            //    startActivity(tohome);
                String s="",s1="";
                s=et.getText().toString();
                s1=et1.getText().toString();
                Cursor rs=null,rs1=null;
                String pwd="",v="",v1="";
                v1="TRUE";
                rs   = mydb.getpass(s);
                rs.moveToFirst();
                int a=1;
                if(s.equals("")||s1.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "ENTER USERNAME AND PASSWORD", Toast.LENGTH_SHORT).show();
                    a=0;
                }
                 if(rs==null||a==0)
                    Toast.makeText(getApplicationContext(), "INCORRECT USERNAME", Toast.LENGTH_SHORT).show();
                else  {
                    pwd = rs.getString(rs.getColumnIndex(DBHelperregister.REGISTER_COLUMN_PASSWORD));
                    System.out.println("password frm database" + pwd);
                     a=1;
                }
                    rs1=mydb.getverify(s);
                    rs1.moveToFirst();
                if(rs1==null||a==0)
                    Toast.makeText(getApplicationContext(), "INCORRECT USERNAME OR PASSWORD OR USER NOT VERIFIED", Toast.LENGTH_SHORT).show();
                else {

                    v = rs1.getString(rs1.getColumnIndex(DBHelperregister.REGISTER_COLUMN_VERIFY));

                    System.out.println("verify frm database" + v);
                    a=1;
                }
                    if(pwd.equals(" ")||s1.equals(" "))
                        Toast.makeText(getApplicationContext(), "INCORRECT USERNAME OR PASSWORD", Toast.LENGTH_SHORT).show();
                    if (s1.equals(pwd)&&(v.equals(v1))&&(a==1))
                    {
                            SharedPreferences.Editor editor = sharedpreferencesun.edit();
                            editor.putString(Name, s);
                            editor.commit();
                             mydb1.insertinitial(s);
                             mydb1.insertinitialuname(s);
                            Toast.makeText(getApplicationContext(), "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                           tohome = new Intent(Login.this, Home_Page.class);
                            startActivity(tohome);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "USER IS NOT VERIFIED", Toast.LENGTH_SHORT).show();
                        }


                    }



        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
