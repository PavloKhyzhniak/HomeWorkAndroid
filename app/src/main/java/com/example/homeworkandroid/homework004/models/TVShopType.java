package com.example.homeworkandroid.homework004.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class TVShopType implements Serializable , Parcelable {

    private String base;
    private String name;
    private TVShopManufacturer manufacturer;
    private double size;
    private int vertical;
    private int horizontal;

    TVShopType(TVShopManufacturer _manufacturer, String _filename, String _name,double _size, int _vertical, int _horizontal) {
        this.manufacturer = _manufacturer;
        this.base = "TVShop/Type/" + _filename;
        this.name = _name;
        this.size = _size;
        this.vertical = _vertical;
        this.horizontal = _horizontal;
    }

    protected TVShopType(Parcel in) {
        base = in.readString();
        name = in.readString();
        size = in.readDouble();
        vertical = in.readInt();
        horizontal = in.readInt();
        this.manufacturer = TVShopManufacturer.valueOf(in.readString());
    }

    public static final Creator<TVShopType> CREATOR = new Creator<TVShopType>() {
        @Override
        public TVShopType createFromParcel(Parcel in) {
            return new TVShopType(in);
        }

        @Override
        public TVShopType[] newArray(int size) {
            return new TVShopType[size];
        }
    };

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        if(size>0)
        this.size = size;
    }

    public int getVertical() {
        return vertical;
    }

    public void setVertical(int vertical) {
        if(vertical>0)
        this.vertical = vertical;
    }

    public int getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(int horizontal) {
        if(horizontal>0)
        this.horizontal = horizontal;
    }

    public TVShopManufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(TVShopManufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(base);
        parcel.writeString(name);
        parcel.writeDouble(size);
        parcel.writeInt(vertical);
        parcel.writeInt(horizontal);
        parcel.writeString(this.manufacturer.name());
    }
}


