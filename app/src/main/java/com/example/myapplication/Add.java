package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.io.ByteArrayOutputStream;

public class Add extends AppCompatActivity {
    DB db;
    EditText Title;
    EditText Description;
    EditText ReleaseDate;
    ImageView img;
    EditText Rating;
    EditText Duration;
    EditText Country;
    MaterialButton btnadd;
    final int Request_CodeGal=999;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Title=(EditText) findViewById(R.id.Title);
        Description=(EditText) findViewById(R.id.Description);
        ReleaseDate=(EditText) findViewById(R.id.ReleaseDate);
        img=(ImageView) findViewById(R.id.img);
        Rating=(EditText) findViewById(R.id.Rating);
        Country=(EditText) findViewById(R.id.Country);
        btnadd=(MaterialButton) findViewById(R.id.btnAdd);
        img.setOnClickListener(view -> ActivityCompat.requestPermissions(Add.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},Request_CodeGal));
        btnadd.setOnClickListener(view -> {
            if (Title.getText().toString()==""|| Description.getText().toString()==""||Duration.getText().toString()==""||ReleaseDate.getText().toString()==""||Rating.getText().toString()==""||Country.getText().toString()=="")
            {    db=new DB(Add.this);
                db.setMovies(Title.getText().toString(),Description.getText().toString(),Duration.getText().toString(),ReleaseDate.getText().toString(),Rating.getText().toString(),Country.getText().toString());
                Intent listing = new Intent(Add.this, Listing.class);
                startActivity(listing);
            }else{
                Toast.makeText(Add.this,"All Fields Are Required",Toast.LENGTH_LONG).show();
            }
        });
//        Title.setText(currentMovie.getTitle());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_meni,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                Intent listing = new Intent(this, Listing.class);
                startActivity(listing);
                break;
            case R.id.nav_add:
                Intent add = new Intent(this, Add.class);
                startActivity(add);
                break;
        }
        return true;

    }
    public static byte[] imgTobyte(ImageView image){
        Bitmap bitmap=((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] bytes=stream.toByteArray();
        return bytes;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Intent getImageIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getImageIntent .setType("image/*");
        startActivityForResult(getImageIntent , Request_CodeGal );
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==Request_CodeGal && resultCode==RESULT_OK){
            Uri fullPhotoUri = data.getData();
            img.setImageURI(fullPhotoUri);


        }

    }

}