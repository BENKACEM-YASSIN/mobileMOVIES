package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.io.ByteArrayOutputStream;

public class Update extends AppCompatActivity {
    DB db;
    TextView Title;
    TextView Description;
    TextView ReleaseDate;
    ImageView img;
    TextView Rating;
    TextView Duration;
    TextView Country;
    MaterialButton btnUpdate;
    Movies currentMovie;
    final int Request_CodeGal=999;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        db=new DB(this);
        currentMovie=getIntent().getParcelableExtra("movie"); //catch passed movies
        Title=(TextView) findViewById(R.id.Title);
        Description=(TextView) findViewById(R.id.Description);
        Duration =(TextView) findViewById(R.id.Duration);
        Country =(TextView) findViewById(R.id.Country);
        ReleaseDate=(TextView) findViewById(R.id.ReleaseDate);
        img=(ImageView) findViewById(R.id.img);
        Rating=(TextView) findViewById(R.id.Rating);
        Title.setText(currentMovie.getTitle());
        Description.setText(currentMovie.getDescription());
        Duration.setText(currentMovie.getDuration());
        ReleaseDate.setText(currentMovie.getReleaseDate());
        Country.setText(currentMovie.getCountry());
        Rating.setText(String.valueOf((int) currentMovie.getRating()));
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(Update.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},Request_CodeGal);
            }
        });
        btnUpdate=(MaterialButton) findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db=new DB(Update.this);
                db.updateMovies(currentMovie.getID(),Title.getText().toString(),Description.getText().toString(),Duration.getText().toString(),ReleaseDate.getText().toString(),Rating.getText().toString(),Country.getText().toString());
                Intent listing = new Intent(Update.this, Listing.class);
                startActivity(listing);
            }
        });

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