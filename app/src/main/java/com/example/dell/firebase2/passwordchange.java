package com.example.dell.firebase2;

import android.content.Intent;
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
import java.util.Map;

public class passwordchange extends AppCompatActivity {
    EditText eold,enew,ecnf;
    Button buttonChangePassword;
    DatabaseReference reference;
    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwordchange);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("userInfo");
        eold = findViewById(R.id.e1);
        ecnf = findViewById(R.id.e3);
        enew = findViewById(R.id.e2);
        buttonChangePassword = findViewById(R.id.buttonChangePassword);




        buttonChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String oldPassword = eold.getText().toString();
                final String newPassword = enew.getText().toString();
                String confirmPassword = ecnf.getText().toString();

                if (newPassword.equals(confirmPassword))
                {
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){

                                Mypojo mypojo = dataSnapshot1.getValue(Mypojo.class);
                                String password = mypojo.getPassword();
                                String id = mypojo.getId();
                                Log.d("1234",id);
                                if (oldPassword.equals(password)){
                                    HashMap<String,Object> taskMap = new HashMap<String,Object>();
                                    taskMap.put("password", newPassword);
                                    reference.child(id).updateChildren(taskMap);
                                    Intent i=new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(i);
                                    Toast.makeText(getApplicationContext(),"password changed successfully",Toast.LENGTH_LONG).show();

                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }else{
                    Toast.makeText(passwordchange.this, "Password does not match!", Toast.LENGTH_SHORT).show();
                }




            }
        });

    }
}
