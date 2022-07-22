package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.PopupMenu;

import java.util.ArrayList;

public class MoviesAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<Movies> moviesArrayList;

    public MoviesAdapter(Context context, ArrayList<Movies> MLS) {
        this.context = context;
        moviesArrayList=MLS;
    }
    //////       ATTENTION RETURNS ARE IMPORTANT
    @Override
    public int getCount() {
        return moviesArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return moviesArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    //@SuppressLint("InflateParams")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view==null)
            view= LayoutInflater.from(context).inflate(R.layout.list_item,null);
        Movies movies=moviesArrayList.get(i);
        ImageView img=(ImageView) view.findViewById(R.id.img);
        ImageView pop=(ImageView) view.findViewById(R.id.pop) ;
        pop.setOnClickListener(view1 -> {
            PopupMenu popupMenu= new PopupMenu(context, view1);
            popupMenu.getMenuInflater().inflate(R.menu.main_menu, popupMenu.getMenu());
            popupMenu.show();
            popupMenu.setOnMenuItemClickListener(item -> {
                switch(item.getItemId()){
                    case R.id.nav_update:
                        Intent intent=new Intent(context,Update.class);
                        intent.putExtra("movie", (Movies) getItem(i));
                        context.startActivity(intent);
                        break;
                    case R.id.nav_delete:
                        Movies currentMovie= (Movies) getItem(i);
                        if (currentMovie!=null){
                            DB db=new DB(context);
                            db.deleteMovie(String.valueOf(currentMovie.getID()));
                        }
                        break;
                }
                return true;
            });
        });
        TextView title=(TextView) view.findViewById(R.id.title) ;
        TextView country=(TextView) view.findViewById(R.id.country) ;
        TextView rate=(TextView) view.findViewById(R.id.rate) ;

//        img.setBackgroundResource(getImageId( movies.getImage()));
        title.setText(movies.getTitle());
        country.setText(movies.getCountry());
        rate.setText(String.valueOf(movies.getRating()));


        return  view;
    }
    public int getImageId(String getName){
        String pkNm=context.getPackageName();
        int resid=context.getResources().getIdentifier(getName.replace(".png",""),"drawable",pkNm);
        return  resid;
    }

}
