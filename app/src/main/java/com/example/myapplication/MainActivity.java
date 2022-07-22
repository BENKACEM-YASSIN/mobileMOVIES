package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    DB db;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new DB(this);
        db.newUser("yassin","yassin",1);
        EditText username=(EditText) findViewById(R.id.txtUsername);
        EditText password=(EditText) findViewById(R.id.txtPassword);
        MaterialButton btnlogin=(MaterialButton) findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getEmail= username.getText().toString().trim();
                String getPassword= password.getText().toString().trim();
                boolean isExist=db.isExist(getEmail,getPassword);
                if (isExist){
                    Intent listing=new Intent(MainActivity.this,Listing.class);
                    startActivity(listing);

                }else {
                    Toast.makeText(MainActivity.this,"Email or Password not valid",Toast.LENGTH_LONG).show();
                }

            }

        });

//        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            ImageView img=(ImageView) findViewById(R.id.img);
//            @Override
//            public void onFocusChange(View view, boolean hasFocus) {
//                if (hasFocus){
//                    img.setVisibility(View.GONE);
//                }else{
//                    img.setVisibility(View.VISIBLE);
//
//                }
//            }
//        });
//        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            ImageView img=(ImageView) findViewById(R.id.img);
//            @Override
//            public void onFocusChange(View view, boolean hasFocus) {
//                if (hasFocus){
//                    img.setVisibility(View.GONE);
//                }else{
//                    img.setVisibility(View.VISIBLE);
//
//                }
//            }
//        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    //@SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        switch(item.getItemId()){
            case R.id.nav_home:
                Intent lisiting=new Intent(this,Listing.class);
                break;
            case R.id.nav_add:
                Intent ad=new Intent(this,Add.class);
                break;
//            case R.id.nav_delete:
//                Intent intent=new Intent(Listing.this,Details.class);
//                break;
        }
        return true;

    }
}