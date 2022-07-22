package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class Listing extends AppCompatActivity {
    DB db;
    ListView ls;
    ImageView pop;
    ArrayList<Movies> moviesLS=new ArrayList<Movies>();
    Movies currentMovie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);
        ls=(ListView) findViewById(R.id.ls);
        pop=(ImageView) findViewById(R.id.pop);

        db=new DB(this);

        //db.setMovies( "N'Avatar'", "N'In the 22nd century, a paraplegic Marine is dispatched to the moon Pandora on a unique mission, but becomes torn between following orders and protecting an alien civilization.","162, ","N'jRXYjXNq0Cs2TcJjLkki24MLp7u.jpg","2009-12-18",7.5,"N'USA");
        //db.setMovies( "N'787878787877878787878787'", "N'In the 22nd century, a paraplegic Marine is dispatched to the moon Pandora on a unique mission, but becomes torn between following orders and protecting an alien civilization.","162, ","2009-12-18","7.5","N'USA");
        //db.setMovies( "N'Avaaaaaaaaaaaaaaaaaaapppppppp'", "N'In the 22nd century, a paraplegic Marine is dispatched to the moon Pandora on a unique mission, but becomes torn between following orders and protecting an alien civilization.","162, ","2009-12-18","7.5","N'USA");
//        Movies mv1=new Movies(21, "N'Avatar'", "N'In the 22nd century, a paraplegic Marine is dispatched to the moon Pandora on a unique mission, but becomes torn between following orders and protecting an alien civilization.","162, ","N'jRXYjXNq0Cs2TcJjLkki24MLp7u.jpg","2009-12-18",7.5,"N'USA");

//        moviesLS.add(mv1);
        moviesLS=db.getMovies();

        ls.setAdapter(new MoviesAdapter(this,moviesLS));
        ArrayList<Movies> finalMoviesLS = moviesLS;

        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(Listing.this,Details.class);
                intent.putExtra("movie", finalMoviesLS.get(i));
                startActivity(intent);
            }

        });

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

}