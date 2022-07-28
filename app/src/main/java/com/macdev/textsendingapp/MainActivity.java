package com.macdev.textsendingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    public static final int PERMISSION_REQUEST_SEND_MESSAGE = 0;


    String mobile, msg;
    EditText phoneNo, messageText;
    Button sendMsgBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        phoneNo = findViewById(R.id.numberEditText);
        messageText = findViewById(R.id.msgEditText);
        sendMsgBtn = findViewById(R.id.sendMsgBtn);



        sendMsgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendMessage();


            }
        });



    }

    protected void sendMessage(){

        mobile = phoneNo.getText().toString();
        msg = messageText.getText().toString();



        if(ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, PERMISSION_REQUEST_SEND_MESSAGE);
        }else{

            //Getting intent and PendingIntent instance
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);

            //Get the SmsManager instance and call the sendTextMessage method to send message
            SmsManager sms=SmsManager.getDefault();
            sms.sendTextMessage(mobile, null, msg, pi,null);

            Toast.makeText(getApplicationContext(), "Message Sent successfully!",
                    Toast.LENGTH_LONG).show();
        }



    }


   /* @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){

            case PERMISSION_REQUEST_SEND_MESSAGE:{
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(mobile, null, msg, null, null);
                    Toast.makeText(this, "SMS SENT", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "SMS Failed....... Please Try again later.", Toast.LENGTH_SHORT).show();
                }
            }


        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


    }*/
}