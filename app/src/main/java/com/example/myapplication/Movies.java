package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class Movies implements Parcelable {
    private int ID;
    private String Title;
    private String Description;
    private String Duration;
//    private String Image;
    private String ReleaseDate;
    private double Rating;
    private String Country;


//    public Movies(int id, String title, String description, String duration, String image, String releaseDate, double rating, String country) {
    public Movies(int id, String title, String description, String duration, String releaseDate, double rating, String country) {
        setID(id);
        setTitle(title);
        setDescription(description);
        setDuration(duration);
//        setImage(image);
        setReleaseDate(releaseDate);
        setRating(rating);
        setCountry(country);
    }

    protected Movies(Parcel in) {
        ID = in.readInt();
        Title = in.readString();
        Description = in.readString();
        Duration = in.readString();
        ReleaseDate = in.readString();
        Rating = in.readDouble();
        Country = in.readString();
    }

    public static final Creator<Movies> CREATOR = new Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel in) {
            return new Movies(in);
        }

        @Override
        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

//    public String getImage() {
//        return Image;
//    }
//
//    public void setImage(String image) {
//        Image = image;
//    }

    public String getReleaseDate() {
        return ReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        ReleaseDate = releaseDate;
    }

    public double getRating() {
        return Rating;
    }

    public void setRating(double rating) {
        Rating = rating;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ID);
        parcel.writeString(Title);
        parcel.writeString(Description);
        parcel.writeString(Duration);
        parcel.writeString(ReleaseDate);
        parcel.writeDouble(Rating);
        parcel.writeString(Country);
    }
}