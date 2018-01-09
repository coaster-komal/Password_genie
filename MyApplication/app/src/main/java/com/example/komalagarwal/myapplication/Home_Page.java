package com.example.komalagarwal.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;




import java.util.jar.Attributes;


public class Home_Page extends ActionBarActivity {

    ImageButton img,img1,img2,img3,img4,img5,img6,edit,search,del,view,frag;

    Intent toadd;
    Spinner sp;
    DBHelperaccount mydb;
    public static final String MyPREFERENCESun = "MyPrefsun" ;
    public static final String Name = "nameKeyun";
    SharedPreferences sharedpreferencesun;
    public static final String MyPREFERENCESsn = "MyPrefssn" ;
    public static final String SERVICEName = "nameKeysn";
    SharedPreferences sharedpreferencessn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__page);
        mydb=new DBHelperaccount(this);
        sharedpreferencesun = getSharedPreferences(MyPREFERENCESun, Context.MODE_PRIVATE);
        sharedpreferencessn = getSharedPreferences(MyPREFERENCESsn, Context.MODE_PRIVATE);
        String s="",s1;
        String[] s2;

        s=sharedpreferencesun.getString(Name,"");
        System.out.println("name="+s);
        sp=(Spinner)findViewById(R.id.sp);
        Cursor c=mydb.getservice(s);
        c.moveToFirst();
        int countr=0;
        while(c.moveToNext())
        {
            countr++;
        }

        s2=new String[countr];
        int i=0;
        c.moveToFirst();
        System.out.println("counter="+countr);
        while(c.moveToNext())
        {

            s1 = c.getString(c.getColumnIndex(DBHelperaccount.ACCOUNT_COLUMN_SERVICE));
            System.out.println(s1);
            s2[i]=s1;
            i++;
        }
        System.out.println("SERVICE NAME FROM DATABASE");
        for(i=0;i<countr;i++)
        {
            System.out.println(s2[i]);
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.select_dialog_singlechoice,s2);
        sp.setAdapter(adapter);
        addListenerOnButton();

    }
    private void addListenerOnButton() {
        img = (ImageButton) findViewById(R.id.add);
        img1 = (ImageButton) findViewById(R.id.facebook);
        img2 = (ImageButton) findViewById(R.id.twitter);
        img3 = (ImageButton) findViewById(R.id.sbi);
        img4 = (ImageButton) findViewById(R.id.paytm);
        img5 = (ImageButton) findViewById(R.id.gmail);
        img6 = (ImageButton) findViewById(R.id.snapdeal);
        edit = (ImageButton) findViewById(R.id.edit);
        search = (ImageButton) findViewById(R.id.search);
        del = (ImageButton) findViewById(R.id.del);
        view = (ImageButton) findViewById(R.id.view);
        frag = (ImageButton) findViewById(R.id.frag);
        img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


                toadd = new Intent(Home_Page.this, Add_username.class);
                startActivity(toadd);
            }

        });
        img1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String s = "";
                s = "FACEBOOK";
                SharedPreferences.Editor editor = sharedpreferencessn.edit();
                editor.putString(SERVICEName, s);
                editor.commit();
                toadd = new Intent(Home_Page.this, Add.class);
                startActivity(toadd);
            }

        });
        img2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String s = "";
                s = "TWITTER";
                SharedPreferences.Editor editor = sharedpreferencessn.edit();
                editor.putString(SERVICEName, s);
                editor.commit();
                toadd = new Intent(Home_Page.this, Add.class);
                startActivity(toadd);
            }

        });
        img3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String s = "";
                s = "SBI";
                SharedPreferences.Editor editor = sharedpreferencessn.edit();
                editor.putString(SERVICEName, s);
                editor.commit();
                toadd = new Intent(Home_Page.this, Add.class);
                startActivity(toadd);
            }

        });
        img4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String s = "";
                s = "PAYTM";
                SharedPreferences.Editor editor = sharedpreferencessn.edit();
                editor.putString(SERVICEName, s);
                editor.commit();
                toadd = new Intent(Home_Page.this, Add.class);
                startActivity(toadd);
            }

        });
        img5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String s = "", s1 = "", s2 = "";
                s = "GMAIL";
                SharedPreferences.Editor editor = sharedpreferencessn.edit();
                editor.putString(SERVICEName, s);
                editor.commit();
                s1 = sharedpreferencesun.getString(Name, "");
                s2 = sharedpreferencessn.getString(SERVICEName, "");
                System.out.println("NAME =" + s1 + " SERVICE NAME =" + s2);
                toadd = new Intent(Home_Page.this, Add.class);
                startActivity(toadd);
            }

        });
        img6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String s = "";
                s = "SNAPDEAL";
                SharedPreferences.Editor editor = sharedpreferencessn.edit();
                editor.putString(SERVICEName, s);
                editor.commit();
                toadd = new Intent(Home_Page.this, Add.class);
                startActivity(toadd);
            }

        });
        edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                toadd = new Intent(Home_Page.this, Edit.class);
                startActivity(toadd);
            }

        });
        del.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                toadd = new Intent(Home_Page.this, Delete.class);
                startActivity(toadd);
            }

        });
        search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String s = "";
                sp = (Spinner) findViewById(R.id.sp);
                s = sp.getSelectedItem().toString();
                SharedPreferences.Editor editor = sharedpreferencessn.edit();
                editor.putString(SERVICEName, s);
                editor.commit();
                toadd = new Intent(Home_Page.this, Cnfrm_mpasssearch.class);
                startActivity(toadd);
            }

        });


        frag.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


                toadd = new Intent(Home_Page.this, Fragment.class);
                startActivity(toadd);
            }

        });
        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                toadd = new Intent(Home_Page.this, Selectservice_view.class);
                startActivity(toadd);
            }

        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home__page, menu);
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


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) < 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Log.d("CDA", "onKeyDown Called");
            onBackPressed();
        }

        return super.onKeyDown(keyCode, event);
    }

    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Intent setIntent = new Intent(Home_Page.this, Login.class);
        startActivity(setIntent);

        return;
    }
}
