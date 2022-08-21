package com.example.rent_home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Receipt extends AppCompatActivity {

    TextView  addhar,sname,scontact,address,buyrent,currentadd,note,amount,upi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        addhar = (TextView)findViewById(R.id.addhar);
        sname = (TextView)findViewById(R.id.sname);
        scontact = (TextView)findViewById(R.id.scontact);
        address = (TextView) findViewById(R.id.address);
        buyrent=(TextView) findViewById(R.id.buyrent);
        currentadd=(TextView) findViewById(R.id.currentadd);
        note=(TextView) findViewById(R.id.note);
        amount=(TextView) findViewById(R.id.amount);
        upi=(TextView) findViewById(R.id.upi);

        Intent intent = getIntent();
        String str = intent.getStringExtra("name");
        String str1 = intent.getStringExtra("addhar");
        String str2 = intent.getStringExtra("address");
        String str3 = intent.getStringExtra("currentadd");
        String str4 = intent.getStringExtra("buyrent");
        String str5 = intent.getStringExtra("number");
        String str6 = intent.getStringExtra("upi");
        String str7 = intent.getStringExtra("amount");


        addhar.setText(str);
        sname.setText(str1);
        address.setText(str2);
        currentadd.setText(str3);
        buyrent.setText(str4);
        scontact.setText(str5);
        amount.setText(str7);
        upi.setText(str6);
    }
}