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
import android.widget.Spinner;
import android.widget.Toast;


public class Delete extends ActionBarActivity {
    DBHelperaccount mydb;
    Button bt;
    Spinner sp1;
    Intent tomass;

    public static final String MyPREFERENCESun = "MyPrefsun" ;
    public static final String Name = "nameKeyun";
    SharedPreferences sharedpreferencesun;
    public static final String MyPREFERENCESsn = "MyPrefssn" ;
    public static final String SERVICEName = "nameKeysn";
    SharedPreferences sharedpreferencessn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        mydb=new DBHelperaccount(this);
        sharedpreferencesun = getSharedPreferences(MyPREFERENCESun, Context.MODE_PRIVATE);
        sharedpreferencessn = getSharedPreferences(MyPREFERENCESsn, Context.MODE_PRIVATE);
        String s="",s1="";
        String[] s2;

        s=sharedpreferencesun.getString(Name,"");
        Spinner sp=(Spinner)findViewById(R.id.sp);
        Cursor c=null;
        c=mydb.getservice(s);
        c.moveToFirst();
        int countr=0;
        while(c.moveToNext())
        {
            countr++;
        }
        s2=new String[countr];
        int i=0;
        c.moveToFirst();
        while(c.moveToNext())
        {

            s1 = c.getString(c.getColumnIndex(DBHelperaccount.ACCOUNT_COLUMN_SERVICE));
            s2[i]=s1;
            i++;
        }
        for(int j=0;i<countr;j++)
        {
            System.out.println(s2[j]);
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,s2);
        sp.setAdapter(adapter);
        addListenerOnButton();
    }

    private void addListenerOnButton() {
        bt = (Button) findViewById(R.id.but);
        sp1 = (Spinner) findViewById(R.id.sp);

        bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String s="";
                s = sp1.getSelectedItem().toString();
                if(s.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "SELECT SERVICE NAME", Toast.LENGTH_LONG).show();
                }
                else {
                    SharedPreferences.Editor editor = sharedpreferencessn.edit();
                    editor.putString(SERVICEName, s);
                    editor.commit();
                    tomass = new Intent(Delete.this, Cnfrm_mpassdelete.class);
                    startActivity(tomass);
                }
            }

        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_delete, menu);
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
