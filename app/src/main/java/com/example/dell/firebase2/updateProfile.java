package com.example.dell.firebase2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class updateProfile extends AppCompatActivity {
    EditText edname,edmail,edno;
    Button change;
    FirebaseDatabase database;
    DatabaseReference reference;
    String name, email, mobile, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("userInfo");

        edname = findViewById(R.id.e1);
        edmail = findViewById(R.id.e2);
        edno = findViewById(R.id.e3);
        change = findViewById(R.id.b1);

        SharedPreferences sharedPreferences = getSharedPreferences("vinod", MODE_PRIVATE);
        name = sharedPreferences.getString("name", null);
        email = sharedPreferences.getString("email", null);
        mobile = sharedPreferences.getString("mobile", null);
        id = sharedPreferences.getString("id", null);


        edname.setText(name);
        edno.setText(mobile);
        edmail.setText(email);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name1 = edname.getText().toString();
                final String email1 = edmail.getText().toString();
                final String mobile1 = edno.getText().toString();
                Log.d("123",name1);

             HashMap<String, Object> hashMap = new HashMap<>();
             hashMap.put("name",name1);
             hashMap.put("email",email1);
             hashMap.put("mobile",mobile1);
             reference.child(id).updateChildren(hashMap);
            }
        });
    }
    }

