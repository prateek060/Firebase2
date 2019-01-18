package com.example.dell.firebase2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class register extends AppCompatActivity {
    Button Register;
    EditText NameED,ContactED,emailED,newPasswordED,confPaswordED;
    RadioButton male,female;
    SharedPreferences sharedPreferences;
    FirebaseDatabase database;
    DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("userInfo");

        Register = findViewById(R.id.bup);
        NameED = findViewById(R.id.e1);
        ContactED = findViewById(R.id.e3);
        emailED = findViewById(R.id.e2);
        newPasswordED = findViewById(R.id.e4);
        confPaswordED = findViewById(R.id.e5);



        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = NameED.getText().toString();
                String mobile = ContactED.getText().toString();
                String email = emailED.getText().toString();
                String newPassword = newPasswordED.getText().toString();
                String confirmPassword = confPaswordED.getText().toString();

                if (newPassword.equals(confirmPassword)){

                    String id = reference.push().getKey();
                    Mypojo mypojo = new Mypojo();
                    mypojo.setName(name);
                    mypojo.setEmail(email);
                    mypojo.setMobile(mobile);
                    mypojo.setId(id);
                    mypojo.setPassword(confirmPassword);
                    reference.child(id).setValue(mypojo);

                    Intent i=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);

                }
                else{
                    Toast.makeText(register.this, "password doesnot match!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
