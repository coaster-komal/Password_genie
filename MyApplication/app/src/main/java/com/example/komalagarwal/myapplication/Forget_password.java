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
import android.widget.ImageButton;
import android.widget.Toast;


public class Forget_password extends ActionBarActivity {
    Button bt;
    Intent toreg;
    EditText et;
    public static final String MyPREFERENCESfp = "MyPrefsfp" ;
    public static final String nameforfp = "namefp";
    SharedPreferences sharedpreferencesfp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        sharedpreferencesfp = getSharedPreferences(MyPREFERENCESfp, Context.MODE_PRIVATE);
        addListenerOnButton();


    }

    private void addListenerOnButton() {

        bt = (Button) findViewById(R.id.sc);
       et=(EditText)findViewById(R.id.et);
        bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String s = "";
                s = et.getText().toString();
                if (s.equals("")) {
                    Toast.makeText(getApplicationContext(), "ENTER USERNAME", Toast.LENGTH_SHORT).show();
                }
                else {
                    SharedPreferences.Editor editor = sharedpreferencesfp.edit();
                    editor.putString(nameforfp, s);
                    editor.commit();
                    toreg = new Intent(Forget_password.this, cnfrmmobno.class);
                    startActivity(toreg);
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_forget_password, menu);
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
