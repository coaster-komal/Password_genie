package com.example.komalagarwal.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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


public class Verify extends ActionBarActivity {
    RadioButton r1,r2;
    RadioGroup rg;
    Button b1,b2,b3,b;
    EditText et;
    Intent tolog;
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
        setContentView(R.layout.activity_verify);

        addListenerRadioButton();
     //   addListenerOnButton();
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
    }
    private void addListenerRadioButton()
    {
        rg = (RadioGroup) findViewById(R.id.rg1);


        b1 = (Button) findViewById(R.id.send);
        b2 = (Button) findViewById(R.id.resend);
        b3 = (Button) findViewById(R.id.validate);



    mydb = new DBHelperregister(this);
    et=(EditText)findViewById(R.id.entercode);
    b1.setOnClickListener(new View.OnClickListener() {
        public void onClick(View view) {

         //   r1 = (RadioButton) findViewById(R.id.email);
         //   r2 = (RadioButton) findViewById(R.id.phone);

            int selected =rg.getCheckedRadioButtonId();
            r2 = (RadioButton) findViewById(selected);
            Toast.makeText(Verify.this,r2.getText(), Toast.LENGTH_SHORT).show();
            String s="",s1="",s2="";
            s1="PHONE NO";
            s2="EMAIL ID";
            s=r2.getText().toString();
           if(s.equals(s1))
           {
                String x="",y="",z="";
                x=sharedpreferences.getString(code,"");
                y=sharedpreferences.getString(mobno,"");
                System.out.println("CODE ="+x);
                System.out.println("MOB NO ="+y);
               z="YOUR CODE TO VERIFY YOUR DETAILS IS :"+x+" FROM PASSWORD GENIE";
                sendSMSMessage(z,y);
            }
            if(s.equals(s2))
            {
                String x="",y="";
                x=sharedpreferences.getString(emailid,"");
                System.out.println("EMAIL ID ="+x);
                y=sharedpreferences.getString(code,"");
                sendEmail(x,y);

            }
        }
    });
    b2.setOnClickListener(new View.OnClickListener() {
        public void onClick(View view) {
            int selected =rg.getCheckedRadioButtonId();
            r2 = (RadioButton) findViewById(selected);
            Toast.makeText(Verify.this,r2.getText(), Toast.LENGTH_SHORT).show();
            String s="",s1="",s2="";
            s1="PHONE NO";
            s2="EMAIL ID";
            s=r2.getText().toString();

            if(s.equals(s1))
            {
                String x="",y="",z="";
                x=sharedpreferences.getString(code,"");
                y=sharedpreferences.getString(mobno,"");
                z="YOUR CODE TO VERIFY YOUR DETAILS IS :"+x+" FROM PASSWORD GENIE";
                sendSMSMessage(z,y);
            }
            if(s.equals(s2))
            {
                String x="",y="";
                x=sharedpreferences.getString(emailid,"");
                y=sharedpreferences.getString(code,"");
                sendEmail(x,y);

            }
        }
    });
    b3.setOnClickListener(new View.OnClickListener() {
        public void onClick(View view) {

            String s1 = "", s2 = "", s3 = "";
            s2 = sharedpreferences.getString(code, "");
            s3 = sharedpreferences.getString(Name, "");
            String a = "TRUE";
            s1 = et.getText().toString();
            if (s1.equals(s2)) {
                Toast.makeText(getApplicationContext(), "VERIFIED SUCCESSFULLY NOW LOGIN YOUR ACCOUNT", Toast.LENGTH_LONG).show();
                mydb.update(s3, a);
                System.out.println("VERIFY UPDATED TO "+a);
                tolog = new Intent(Verify.this, Login.class);
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


       //     startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            startActivity(emailIntent);

            finish();
            Log.i("Finished", "");
        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(Verify.this,"There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_verify, menu);
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
