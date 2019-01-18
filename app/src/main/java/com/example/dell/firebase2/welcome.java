package com.example.dell.firebase2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class welcome extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item1:
                Intent intent = new Intent(getApplicationContext(),updateProfile.class);
                startActivity(intent);

                return true;
            case R.id.item2:
                Intent intent1 = new Intent(getApplicationContext(),passwordchange.class);
                startActivity(intent1);
                return true;
            case R.id.item3:
                Toast.makeText(getApplicationContext(), "log out successfully", Toast.LENGTH_LONG).show();
                SharedPreferences sharedPreferences = getSharedPreferences("vinod", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
               // editor.putBoolean("loginStatus", false);
                editor.clear();
                editor.commit();
                Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent2);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}