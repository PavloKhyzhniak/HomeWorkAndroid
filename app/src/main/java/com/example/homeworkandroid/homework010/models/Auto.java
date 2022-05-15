package com.example.homeworkandroid.homework010.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Auto implements Parcelable {
    String brandName;//бренд
    String modelName;//модель
    int yearOfManufacture;//год выпуска
    double enginePower;//мощность двигателя
    int price;//цена
    String symbolicImage;//условное изображение

    protected Auto(Parcel in) {
        brandName = in.readString();
        modelName = in.readString();
        yearOfManufacture = in.readInt();
        enginePower = in.readDouble();
        price = in.readInt();
        symbolicImage = in.readString();
    }

    public static final Creator<Auto> CREATOR = new Creator<Auto>() {
        @Override
        public Auto createFromParcel(Parcel in) {
            return new Auto(in);
        }

        @Override
        public Auto[] newArray(int size) {
            return new Auto[size];
        }
    };

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        if (brandName.length() > 0)
            this.brandName = brandName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        if (modelName.length() > 0)
            this.modelName = modelName;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(int yearOfManufacture) {
        if (yearOfManufacture > 0)
            this.yearOfManufacture = yearOfManufacture;
    }

    public double getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(double enginePower) {
        if (enginePower > 0)
            this.enginePower = enginePower;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        if (price > 0)
            this.price = price;
    }

    public String getSymbolicImage() {
        return symbolicImage;
    }

    public void setSymbolicImage(String symbolicImage) {
        if (symbolicImage.length() > 0)
            this.symbolicImage = symbolicImage;
    }


    public Auto(
            String _brandName,
            String _modelName,
            int _yearOfManufacture,
            double _enginePower,
            int _price,
            String _symbolicImage
    ) {
        this.brandName = _brandName;
        this.modelName = _modelName;
        this.yearOfManufacture = _yearOfManufacture;
        this.enginePower = _enginePower;
        this.price = _price;
        this.symbolicImage = _symbolicImage;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(brandName);
        dest.writeString(modelName);
        dest.writeInt(yearOfManufacture);
        dest.writeDouble(enginePower);
        dest.writeInt(price);
        dest.writeString(symbolicImage);
    }
}
