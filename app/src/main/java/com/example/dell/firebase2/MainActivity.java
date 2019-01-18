package com.example.dell.firebase2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    Button bsignin, bsignup;
    EditText edmail, edpassword;
    FirebaseDatabase database;
    DatabaseReference reference;
    SharedPreferences sharedPreferences;
    CheckBox cbox;
    boolean flag=false;
    String uemail,upass;
    Mypojo mypojo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("userInfo");
        edmail =  findViewById(R.id.e1);
        edpassword =  findViewById(R.id.e2);
        cbox =  findViewById(R.id.c1);
        bsignin =  findViewById(R.id.bsgn);
        bsignup =  findViewById(R.id.bup);


         sharedPreferences = getSharedPreferences("vinod", MODE_PRIVATE);
         boolean loginStatus = sharedPreferences.getBoolean("loginStatus", false);
         if (loginStatus==true){
             Intent intent = new Intent(MainActivity.this, welcome.class);
             startActivity(intent);
             finish();
         }

        bsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), register.class);
                startActivity(i);
            }
        });
         bsignin.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View v) {

                 uemail = edmail.getText().toString();
                 upass = edpassword.getText().toString();

                 if (uemail.equals("") && upass.equals("")) {
                     Toast.makeText(MainActivity.this, "Fill all fields", Toast.LENGTH_SHORT).show();
                 } else {


                     reference.addValueEventListener(new ValueEventListener() {
                         @Override
                         public void onDataChange(DataSnapshot dataSnapshot) {
                             for (DataSnapshot data : dataSnapshot.getChildren()) {

                                 mypojo = data.getValue(Mypojo.class);

                                 String email = mypojo.getEmail();
                                 String password = mypojo.getPassword();

                                 if (uemail.equals(email) && upass.equals(password)) {
                                     flag = true;
                                     break;
                                 }

                             }

                             if (flag == true) {
                                 Toast.makeText(MainActivity.this, "login succesfully", Toast.LENGTH_SHORT).show();

                                  sharedPreferences = getSharedPreferences("vinod", MODE_PRIVATE);
                                 SharedPreferences.Editor editor = sharedPreferences.edit();
                                 editor.putString("name", mypojo.getName());
                                 editor.putString("email", mypojo.getEmail());
                                 editor.putString("mobile", mypojo.getMobile());
                                 editor.putString("id", mypojo.getId());
                                 editor.putBoolean("loginStatus",true);
                                 editor.putString("password", mypojo.getPassword());
                                 editor.commit();
                                 Intent i = new Intent(getApplicationContext(), welcome.class);
                                 startActivity(i);
                                 finish();
                             }
                             else{
                                 Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                             }

                         }

                         @Override
                         public void onCancelled(DatabaseError databaseError) {
                             Toast.makeText(MainActivity.this, "Database Error", Toast.LENGTH_SHORT).show();
                         }
                     });
                 }
             }

         });




    }
}