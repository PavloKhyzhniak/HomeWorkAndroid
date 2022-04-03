package com.example.homeworkandroid.homework005.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.homeworkandroid.homework004.models.TVShopManufacturer;
import com.example.homeworkandroid.homework004.models.TVShopType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Book implements Parcelable {

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        if(author_id>0)
            this.author_id = author_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        if(category_id>0)
            this.category_id = category_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
       this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if(year>0)
            this.year = year;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        if(price>0)
            this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        if(amount>0)
            this.amount = amount;
    }

    private int id;
    private int author_id;
    private int category_id;
    private String title;
    private int year;
    private int price;
    private int amount;

    public Book(int id,
         int author_id,
            int category_id,
            String title,
            int year,
            int price,
            int amount)
    {
        this.id = id;
        this.author_id = author_id;
        this.category_id = category_id;
        this.title = title;
        this.year = year;
        this.price = price;
        this.amount = amount;
    }


    protected Book(Parcel in) {
        author_id = in.readInt();
        category_id = in.readInt();
        title = in.readString();
        year = in.readInt();
        price = in.readInt();
        amount = in.readInt();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(author_id);
        dest.writeInt(category_id);
        dest.writeString(title);
        dest.writeInt(year);
        dest.writeInt(price);
        dest.writeInt(amount);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
