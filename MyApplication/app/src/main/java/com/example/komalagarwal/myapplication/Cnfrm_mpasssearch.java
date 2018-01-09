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


public class Cnfrm_mpasssearch extends ActionBarActivity {
    Button bt;
    EditText et;
    DBHelperregister mydb;
    Intent toview;

    public static final String MyPREFERENCESun = "MyPrefsun" ;
    public static final String Name = "nameKeyun";
    SharedPreferences sharedpreferencesun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cnfrm_mpasssearch);
        sharedpreferencesun = getSharedPreferences(MyPREFERENCESun, Context.MODE_PRIVATE);
        addListenerOnButton();
    }
    private void addListenerOnButton() {
        bt = (Button) findViewById(R.id.bt);
        et=(EditText)findViewById(R.id.mp);
        mydb=new DBHelperregister(this);

        bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String s="",s1="",s2="";
                s=sharedpreferencesun.getString(Name,"");
                s2=et.getText().toString();
                Cursor c=null;
                c=mydb.getmasterpass(s);
                c.moveToFirst();
                s1 = c.getString(c.getColumnIndex(DBHelperregister.REGISTER_COLUMN_MASTERPASS));
                System.out.println(" master password frm database" + s1);
                if(s2.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "ENTER MASTER PASSWORD", Toast.LENGTH_LONG).show();
                }
                else if(s1.equals(s2))
                {
                    Toast.makeText(getApplicationContext(), "CORRECT PASSWORD", Toast.LENGTH_SHORT).show();
                    toview = new Intent(Cnfrm_mpasssearch.this, View_details2.class);
                    startActivity(toview);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "INCORRECT PASSWORD", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cnfrm_mpasssearch, menu);
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
