package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.time.Duration;

public class Details extends AppCompatActivity {
    DB db;
    TextView Title;
    TextView Description;
    TextView ReleaseDate;
    ImageView img;
    TextView Rating;
    TextView Duration;
    TextView Country;
    MaterialButton btnlogin;
    Movies currentMovie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        db=new DB(this);
        currentMovie=getIntent().getParcelableExtra("movie"); //catch passed movies
        Title=(TextView) findViewById(R.id.title);
        Description=(TextView) findViewById(R.id.description);
        Duration =(TextView) findViewById(R.id.duration);
        Country =(TextView) findViewById(R.id.country);
        ReleaseDate=(TextView) findViewById(R.id.realiseDate);
        img=(ImageView) findViewById(R.id.img);
        Rating=(TextView) findViewById(R.id.rate);
        Title.setText(currentMovie.getTitle());
        Description.setText(currentMovie.getDescription());
        Duration.setText(currentMovie.getDuration());
        Country.setText(currentMovie.getCountry());
        ReleaseDate.setText(currentMovie.getReleaseDate());
        Rating.setText(String.valueOf((int) currentMovie.getRating()));

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_meni,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        switch(item.getItemId()){
            case R.id.nav_home:
                Intent home=new Intent(Details.this,Listing.class);
                startActivity(home);
                break;
            case R.id.nav_add:
                Intent add=new Intent(Details.this,Add.class);
                startActivity(add);
                break;


        }
        return true;

    }
}