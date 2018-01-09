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
import android.widget.Toast;



public class Fragment extends ActionBarActivity {
    Button bt,bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8;
    Intent toview;
    private SimpleGestureFilter detector;
    public static final String MyPREFERENCESun = "MyPrefsun" ;
    public static final String Name = "nameKeyun";
    SharedPreferences sharedpreferencesun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        sharedpreferencesun = getSharedPreferences(MyPREFERENCESun, Context.MODE_PRIVATE);
        addListenerOnButton();
       // detector= new SimpleGestureFilter(this,this);

    }

    private void addListenerOnButton()
    {
        bt=(Button)findViewById(R.id.view);
        bt1=(Button)findViewById(R.id.edit);
        bt2=(Button)findViewById(R.id.add);
        bt3=(Button)findViewById(R.id.view1);
        bt4=(Button)findViewById(R.id.del);
        bt5=(Button)findViewById(R.id.cp);
        bt6=(Button)findViewById(R.id.mp);
        bt7=(Button)findViewById(R.id.edit1);
        bt8=(Button)findViewById(R.id.lo);

        bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


                toview = new Intent(Fragment.this, View_acc.class);
                startActivity(toview);

            }

        });
        bt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


                toview = new Intent(Fragment.this, Edit_acc.class);
                startActivity(toview);
            }

        });
        bt2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


                toview = new Intent(Fragment.this, Add_username.class);
                startActivity(toview);
            }

        });
        bt3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


                toview = new Intent(Fragment.this, Cnfrm_mpasssearch.class);
                startActivity(toview);
            }

        });
        bt4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


                toview = new Intent(Fragment.this, Cnfrm_mpassdelete.class);
                startActivity(toview);
            }

        });
        bt5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


                toview = new Intent(Fragment.this, Change_pass1.class);
                startActivity(toview);
            }

        });
        bt6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


                toview = new Intent(Fragment.this, Change_mpass.class);
                startActivity(toview);
            }

        });
        bt7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


                toview = new Intent(Fragment.this, Edit.class);
                startActivity(toview);

            }

        });
        bt8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String s="";

                SharedPreferences.Editor editor = sharedpreferencesun.edit();
                editor.putString(Name, s);
                editor.commit();
                toview = new Intent(Fragment.this, Login.class);
                startActivity(toview);

            }

        });



    }
 /*   public void onSwipe(int direction)
    {
        String str="";
        switch(direction)
        {
            case SimpleGestureFilter.Swipe_Right: str="Swipe Right";
                break;
            case SimpleGestureFilter.Swipe_Left: str="Swipe Left";
                break;
            case SimpleGestureFilter.Swipe_Down: str="Swipe Down";
                break;
            case SimpleGestureFilter.Swipe_Up: str="Swipe Up";
                break;
        }
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_, menu);
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
