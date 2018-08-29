package com.example.sms_demo;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.annotation.Target;
import java.util.Random;
import java.util.jar.Manifest;

public class MainActivity extends AppCompatActivity {
    EditText name,email,password,contact;
    Button signup;

    private int PERMISSION_REQUEST_CODE=8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        contact = findViewById(R.id.contact);
        signup = findViewById(R.id.button);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
                        if(checkSelfPermission(android.Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(MainActivity.this, "Please Select Allow", Toast.LENGTH_SHORT).show();
                        }else {
                            requestPermissions(new String[]{android.Manifest.permission.SEND_SMS},PERMISSION_REQUEST_CODE);
                        }
                }else{
                    Send_sms();
                }
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == PERMISSION_REQUEST_CODE) {
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED) {
                    Send_sms();
                }

        }
    }
    private void Send_sms() {
        Random random = new Random();
         final  int r = random.nextInt(50000);
        String contact_f = contact.getText().toString();

        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("sms:"
                + contact_f)));


      //  SmsManager smsManager = SmsManager.getDefault();
       // smsManager.sendTextMessage(contact_f,null,String.valueOf(r),null,null);

    }
}
