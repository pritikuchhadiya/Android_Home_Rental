package com.example.rent_home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mapbox.core.utils.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class confirmRent extends AppCompatActivity {

    private EditText cName, cAddress, cNo, caddhar, cnegotiation, ccurrentadd, cbuyrent,upi;
    private ImageButton confirm;
    String saveCurrentDate;
    String saveCurrentTime;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_rent);

        FirebaseUser cur_user = FirebaseAuth.getInstance().getCurrentUser();
        mAuth = FirebaseAuth.getInstance();
        upi = findViewById(R.id.con_upi);
        cName = findViewById(R.id.con_Name);
        cAddress = findViewById(R.id.con_address);
        cNo = findViewById(R.id.con_phnNo);
        caddhar = findViewById(R.id.con_addhar);
        ccurrentadd = findViewById(R.id.con_currentadd);
        cnegotiation = findViewById(R.id.con_Negotitation);
        cbuyrent = findViewById(R.id.con_buyrent);
        confirm = findViewById(R.id.confirm);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checking();
            }
        });

    }

    private void checking() {
        if (TextUtils.isEmpty(cName.getText().toString())) {
            Toast.makeText(this, "Please type your name", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(cAddress.getText().toString())) {
            Toast.makeText(this, "Please provide your address", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(cNo.getText().toString())) {
            Toast.makeText(this, "Please provide your contact no", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(caddhar.getText().toString())) {
            Toast.makeText(this, "Please provide your Addhar no", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(ccurrentadd.getText().toString())) {
            Toast.makeText(this, "Please provide your Current Address", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(cnegotiation.getText().toString())) {
            Toast.makeText(this, "Please provide your Negotiable Price", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(cbuyrent.getText().toString())) {
            Toast.makeText(this, "Do You Want To Buy or Rent This House", Toast.LENGTH_SHORT).show();
        } else {
            confirmation();
        }

    }

    private void confirmation() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH: mm: ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());
        DatabaseReference rentRef = FirebaseDatabase.getInstance().getReference().child("ConfirmRent");

        HashMap<String, Object> map = new HashMap<>();
        map.put("Name", cName.getText().toString());
        map.put("ContactNo", cNo.getText().toString());
        map.put("Address", cAddress.getText().toString());
        map.put("Addhar", caddhar.getText().toString());
        map.put("Current Address", ccurrentadd.getText().toString());
        map.put("Negotiation", cnegotiation.getText().toString());
        map.put("Buy Rent", cbuyrent.getText().toString());
        map.put("State", "Not Rented yet");

        rentRef.child(mAuth.getCurrentUser().getUid()).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {

            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(confirmRent.this, "You've successfully done the rent request", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(confirmRent.this, Receipt.class));

                }
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = cName.getText().toString();
                String str1 = caddhar.getText().toString();
                String str2 = cAddress.getText().toString();
                String str3 = ccurrentadd.getText().toString();
                String str4 = cbuyrent.getText().toString();
                String str5 = cNo.getText().toString();
                String str6 = upi.getText().toString();
                String str7 = cnegotiation.getText().toString();

                Intent intent = new Intent(getApplicationContext(), Receipt.class);
                intent.putExtra("name", str);
                intent.putExtra("addhar", str1);
                intent.putExtra("address", str2);
                intent.putExtra("currentadd", str3);
                intent.putExtra("buyrent", str4);
                intent.putExtra("number", str5);
                intent.putExtra("upi", str6);
                intent.putExtra("amount", str7);


                startActivity(intent);
            }

        });
    }
}