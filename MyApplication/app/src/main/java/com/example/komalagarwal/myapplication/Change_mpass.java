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
import android.widget.Toast;


public class Change_mpass extends ActionBarActivity {
    EditText et,et1,et2;
    Button bt;
    Intent tohome;
    public static final String MyPREFERENCESun = "MyPrefsun" ;
    public static final String Name = "nameKeyun";
    SharedPreferences sharedpreferencesun;
    DBHelperregister mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_mpass);
        sharedpreferencesun = getSharedPreferences(MyPREFERENCESun, Context.MODE_PRIVATE);

        addListenerOnButton();


    }

    private void addListenerOnButton()
    {
        et=(EditText)findViewById(R.id.cpass);
        mydb=new DBHelperregister(this);
        et1=(EditText)findViewById(R.id.npass);
        et2=(EditText)findViewById(R.id.cnpass);
        bt=(Button)findViewById(R.id.sc);
        bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String s = "",s1="",s2="",s3="",s4="";
                s = sharedpreferencesun.getString(Name, "");
                Cursor c=null;
                c=mydb.getmasterpass(s);
                c.moveToFirst();
                s1=c.getString(c.getColumnIndex(DBHelperregister.REGISTER_COLUMN_MASTERPASS));
                s2=et.getText().toString();
                s3=et1.getText().toString();
                s4=et2.getText().toString();
                if(s1.equals(s2)) {
                    int a = 0, b = 0;

                    if (s3.equals("") && s4.equals("")) {
                        b = 1;
                        Toast.makeText(getApplicationContext(), "FILL ALL FIELDS", Toast.LENGTH_LONG).show();
                    }
                    if ((s3.length()) < 5 && b == 0) {
                        b = 1;
                        Toast.makeText(getApplicationContext(), "PASSWORD LENGTH SHOULD BE MINIMUM 5", Toast.LENGTH_LONG).show();
                    }

                    if (!s3.equals(s4) && b == 0) {
                        b = 1;
                        Toast.makeText(getApplicationContext(), "PASSWORD DOES NOT MATCHED", Toast.LENGTH_LONG).show();
                    }

                    if (b == 0) {
                        String x = "";
                        mydb.updatempass(s, s3);
                        Toast.makeText(getApplicationContext(), "PASSWORD CHANGED!!", Toast.LENGTH_LONG).show();
                        tohome = new Intent(Change_mpass.this, Home_Page.class);
                        startActivity(tohome);
                    }
                }
                else
                { Toast.makeText(getApplicationContext(), "INCORRECT PASSWORD", Toast.LENGTH_LONG).show();
                }
            }

        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_change_mpass, menu);
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
