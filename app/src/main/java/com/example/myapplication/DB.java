package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DB extends SQLiteOpenHelper {
    static final String dbName="Movies.db";
    public DB(Context context) {
        super(context,"Movies.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists users(Id integer primary key autoincrement,Username text NOT NULL,Password text NOT NULL,isActive integer NOT NULL)");
//        sqLiteDatabase.execSQL("create table if not exists Movie(ID integer primary key autoincrement,Title text NOT NULL,Description text NOT NULL,Duration text NOT NULL,Image text NOT NULL,ReleaseDate text NOT NULL,Rating text NOT NULL,Country text NOT NULL)");
        sqLiteDatabase.execSQL("create table if not exists Movie(ID integer primary key autoincrement,Title text NOT NULL,Description text NOT NULL,Duration text NOT NULL,ReleaseDate text NOT NULL,Rating text NOT NULL,Country text NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists users");
        sqLiteDatabase.execSQL("drop table if exists Movie");
        onCreate(sqLiteDatabase);
    }
//    public boolean setMovies(String Title, String Description,String Duration,String Image,String ReleaseDate,double Rating,String Country){
    public boolean setMovies(String Title, String Description, String Duration, String ReleaseDate, String Rating, String Country){
        SQLiteDatabase sql= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("Title",Title);
        values.put("Description",Description);
        values.put("Duration",Duration);
//        values.put("Image",Image);
        values.put("ReleaseDate",ReleaseDate);
        values.put("Rating",Rating);
        values.put("Country",Country);
        long query=sql.insert("Movie",null,values);
        return query != -1;
    }
    public boolean updateMovies(int ID, String Title, String Description,String Duration,String ReleaseDate,String Rating,String Country){
        SQLiteDatabase sql= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("Title",Title);
        values.put("Description",Description);
        values.put("Duration",Duration);
//        values.put("Image",Image);
        values.put("ReleaseDate",ReleaseDate);
        values.put("Rating",Rating);
        values.put("Country",Country);
        @SuppressLint("Recycle") Cursor cursor=sql.rawQuery("select * from Movie where ID=?",new String[]{String.valueOf(ID)});
        if (cursor.getCount()>0){
            long query=sql.update("Movie",values,"ID=?",new String[]{String.valueOf(ID)});
            return query != -1;
        }
        return  true;
    }
    public boolean deleteMovie(String ID){
        SQLiteDatabase sql= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("ID",ID);
        long query=sql.delete("Movie","ID=?",new String[]{ID});
        return query != -1;
    }
    public ArrayList<Movies> getMovies(){
        ArrayList<Movies> mls=new ArrayList<>();
        Movies m;
        SQLiteDatabase sql= this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor=sql.rawQuery("select * from Movie",null);
        while(cursor.moveToNext()){
            int ID=cursor.getInt(0);
            String Title=cursor.getString(1);
            String Description=cursor.getString(2);
            String Duration=cursor.getString(3);
//            String Image=cursor.getString(4);
            String ReleaseDate=cursor.getString(4);
            double Rating=cursor.getDouble(5);
            String Country=cursor.getString(6);
            m=new Movies(ID,Title,Description,Duration,ReleaseDate,Rating,Country);
//            m=new Movies(ID,Title,Description,Duration,Image,ReleaseDate,Rating,Country);
            mls.add(m);
        }
        return mls;

    }
    public boolean newUser(String username, String password,int isActive){
        SQLiteDatabase sql= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("Username",username);
        values.put("Password",password);
        values.put("isActive",isActive);
        long query=sql.insert("users",null,values);
        return query != -1;
    }
    public boolean isExist(String username, String password){

        SQLiteDatabase sql= this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor=sql.rawQuery("select * from users where username=? and password=?",new String[] {username,password});
        return cursor.getCount()>0;
    }
}
