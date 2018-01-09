package com.example.komalagarwal.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class cnfrmmobno extends ActionBarActivity {
    RadioButton r1,r2;
    RadioGroup rg;
    Button b1,b2,b3,b;
    EditText et;
    Intent tolog;
    String dd;
    DBHelperregister mydb;
    public static final String MyPREFERENCESfp = "MyPrefsfp" ;
    public static final String nameforfp = "namefp";
    SharedPreferences sharedpreferencesfp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cnfrmmobno);


        sharedpreferencesfp = getSharedPreferences(MyPREFERENCESfp, Context.MODE_PRIVATE);
        addListenerRadioButton();
    }
    private void addListenerRadioButton()
    {
        rg = (RadioGroup) findViewById(R.id.rg1);


        b1 = (Button) findViewById(R.id.send);
        b2 = (Button) findViewById(R.id.resend);
        b3 = (Button) findViewById(R.id.validate);



        mydb = new DBHelperregister(this);
        final Double d,d1,d2,d3,d4;
        d=Math.random();
        dd="";
        String str="";
        str=String.valueOf(d);
        for(int i=3;i<=6;i++)
        {
            dd=dd+(str.charAt(i));
        }
        System.out.println("CODE +"+dd);
        et=(EditText)findViewById(R.id.entercode);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                //   r1 = (RadioButton) findViewById(R.id.email);
                //   r2 = (RadioButton) findViewById(R.id.phone);

                int selected =rg.getCheckedRadioButtonId();
                r2 = (RadioButton) findViewById(selected);
                Toast.makeText(cnfrmmobno.this, r2.getText(), Toast.LENGTH_SHORT).show();
                String s="",s1="",s2="";
                s1="PHONE NO";
                s2="EMAIL ID";
                s=r2.getText().toString();
                if(s.equals(s1))
                {
                    String x="",y="",z="",u="";
                    u=sharedpreferencesfp.getString(nameforfp,"");
                    Cursor c=null;
                    c=mydb.getmobno(u);
                    if(c!=null)
                    {
                        c.moveToNext();
                    y = c.getString(c.getColumnIndex(DBHelperregister.REGISTER_COLUMN_PHONE));
                    System.out.println(" CODE ="+dd);
                    z="YOUR CODE TO CHANGE YOUR PASSWORD IS :"+dd+"  FROM PASSWORD GENIE";
                    sendSMSMessage(z,y);}
                    else
                    {
                        Toast.makeText(getApplicationContext(), "USERNAME NOT EXIST", Toast.LENGTH_LONG).show();
                    }
                }
                if(s.equals(s2))
                {
                    String x="",y="",z="";
                    z=sharedpreferencesfp.getString(nameforfp,"");
                    Cursor c=null;
                    c=mydb.getemailid(z);
                    c.moveToFirst();
                    x = c.getString(c.getColumnIndex(DBHelperregister.REGISTER_COLUMN_EMAIL));
                    System.out.println(" CODE ="+dd);
                    y="YOUR CODE TO CHANGE YOUR PASSWORD IS :"+dd+"  FROM PASSWORD GENIE";
                    sendEmail(x,y);

                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int selected =rg.getCheckedRadioButtonId();
                r2 = (RadioButton) findViewById(selected);
                Toast.makeText(cnfrmmobno.this,r2.getText(), Toast.LENGTH_SHORT).show();
                String s="",s1="",s2="";
                s1="PHONE NO";
                s2="EMAIL ID";
                s=r2.getText().toString();


                if(s.equals(s1))
                {
                    String x="",y="",z="",u="";
                    u=sharedpreferencesfp.getString(nameforfp,"");
                    Cursor c=null;
                    c=mydb.getmobno(u);
                    c.moveToFirst();
                    y = c.getString(c.getColumnIndex(DBHelperregister.REGISTER_COLUMN_PHONE));


                    System.out.println(" CODE ="+dd);
                    z="YOUR CODE TO CHANGE YOUR PASSWORD IS :"+dd+"  FROM PASSWORD GENIE";
                    sendSMSMessage(z,y);
                }
                if(s.equals(s2))
                {
                    String x="",y="",z="";
                    z=sharedpreferencesfp.getString(nameforfp,"");
                    Cursor c=null;
                    c=mydb.getemailid(z);
                    c.moveToFirst();
                    x = c.getString(c.getColumnIndex(DBHelperregister.REGISTER_COLUMN_EMAIL));

                    System.out.println(" CODE ="+dd);
                    y="YOUR CODE TO CHANGE YOUR PASSWORD IS :"+dd+"  FROM PASSWORD GENIE";
                    sendEmail(x,y);

                }
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String s1 = "";
                s1 = et.getText().toString();
                if(s1.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "ENTER CODE", Toast.LENGTH_LONG).show();
                }
                else if (s1.equals(dd)) {
                    Toast.makeText(getApplicationContext(), "CODE MATCHED NOW CHANGE YOUR PASSWORD", Toast.LENGTH_LONG).show();
                      tolog = new Intent(cnfrmmobno.this, Change_pass.class);
                      startActivity(tolog);
                }
                else {
                    Toast.makeText(getApplicationContext(), "INCORRECT CODE", Toast.LENGTH_LONG).show();
                }
            }

        });

    }
    protected void sendSMSMessage(String message,String phoneNo)
    {
        Log.i("Send SMS", "");
        try
        {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS SENT", Toast.LENGTH_LONG).show();
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "SMS failed, please try again.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
    protected void sendEmail(String email,String code) {
        Log.i("Send email", "");
        String[] TO = {email};
        String s="";
        s="CODE TO VERIFY";
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, s);
        emailIntent.putExtra(Intent.EXTRA_TEXT, code);
        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished", "");
        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(cnfrmmobno.this,"There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cnfrmmobno, menu);
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
