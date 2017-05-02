package com.kanishq.feedbackactivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * Created by gupta on 18-04-2017.
 */

public class FeedbackeActivity extends AppCompatActivity {
    DatabaseReference feedbacker;

    EditText name, email, query;
    String device, manufacture, version, hardware, DeviceInfo,country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feeback_main);
        feedbacker = FirebaseDatabase.getInstance().getReference("feedbackes");
        TextView tx1 = (TextView) findViewById(R.id.tx1);
        Typeface typeface2 = Typeface.createFromAsset(getAssets(), "ranga.ttf");
        tx1.setTypeface(typeface2);
        name = (EditText) findViewById(R.id.editText);
        email = (EditText) findViewById(R.id.editText2);
        query = (EditText) findViewById(R.id.editText3);
        device = Build.MODEL;
        manufacture = Build.MANUFACTURER;
        version = Build.VERSION.RELEASE;
        hardware = Build.HARDWARE;
        DeviceInfo = manufacture + " " + device + " " + version + " " + hardware;
        Button b1 = (Button) findViewById(R.id.button99);
        country  = ((TelephonyManager) getApplicationContext().getSystemService(TELEPHONY_SERVICE)).getNetworkCountryIso();
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (DetectConnection.checkInternetConnection(FeedbackeActivity.this)) {
                        addfeedback();


                    }else {
                        Toast.makeText(FeedbackeActivity.this, "No Internet found! Pls check your Internet Connection and try again !" ,Toast.LENGTH_SHORT).show();
                        addfeedback();
                    }

                }
            });



        Intent i = getIntent();
        String text = i.getStringExtra("tx1");
        query.setText(text);

    }




    public void addfeedback() {
        String name1 = name.getText().toString().trim();
        String email1 = email.getText().toString().trim();
        String query1 = query.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (email1.matches(emailPattern) && !TextUtils.isEmpty(name1) && !TextUtils.isEmpty(query1)) {

            String id = feedbacker.push().getKey();
            feedbackUser feedbackUser = new feedbackUser(id, name1, email1, query1, DeviceInfo, country);
            feedbacker.child(name1 + id).setValue(feedbackUser);
            if (DetectConnection.checkInternetConnection(FeedbackeActivity.this)) {
                show1();
            }


        } else {
            Toast.makeText(this, "Please enter correct details", Toast.LENGTH_SHORT).show();
        }
    }



    private void show1() {
        new AlertDialog.Builder(this).setMessage("Thanks for submitting feedback. We will get back to you very soon :)")
                .setPositiveButton("Rate it have Star", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {

                        startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.store.com/store/apps/details?id=" + getPackageName())));

                    }
                })
                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                    }
                })
                .show();
    }


}



