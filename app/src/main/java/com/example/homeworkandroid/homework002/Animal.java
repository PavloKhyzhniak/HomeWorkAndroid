package com.example.homeworkandroid.homework002;

import android.os.Parcel;
import android.os.Parcelable;



public class Animal implements Parcelable {

    public Animal() { this(AnimalType.cat, "Черная", "Пушистик", 2, 5, "Claus", "Ivanko",""); }

    public Animal(AnimalType _Type, String _Breed, String _Name, int _Age, int _Weight, String _LastNameOwner, String _FistNameOwner, String _img) {
        this.Type = _Type;
        this.Breed = _Breed;
        this.Name = _Name;
        this.Age = _Age;
        this.Weight = _Weight;
        this.LastNameOwner = _LastNameOwner;
        this.FistNameOwner = _FistNameOwner;
        this.img = _img;
    }

    protected Animal(Parcel in) {
        this.Breed = in.readString();
        this.Name = in.readString();
        this.Age = in.readInt();
        this.Weight = in.readInt();
        this.LastNameOwner = in.readString();
        this.FistNameOwner = in.readString();
        this.img = in.readString();
        this.Type = AnimalType.valueOf(in.readString());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Breed);
        dest.writeString(this.Name);
        dest.writeInt(this.Age);
        dest.writeInt(this.Weight);
        dest.writeString(this.LastNameOwner);
        dest.writeString(this.FistNameOwner);
        dest.writeString(this.img);
        dest.writeString(this.Type.name());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Animal> CREATOR = new Creator<Animal>() {
        @Override
        public Animal createFromParcel(Parcel in) {
            return new Animal(in);
        }

        @Override
        public Animal[] newArray(int size) {
            return new Animal[size];
        }
    };

    public String getBreed() {
        return Breed;
    }

    public void setBreed(String breed) {
        Breed = breed;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        if(age>0)
            Age = age;
    }

    public int getWeight() {
        return Weight;
    }

    public void setWeight(int weight) {
        if(weight>0)
            Weight = weight;
    }

    public String getLastNameOwner() {
        return LastNameOwner;
    }

    public void setLastNameOwner(String lastNameOwner) {
        LastNameOwner = lastNameOwner;
    }

    public String getFistNameOwner() {
        return FistNameOwner;
    }

    public void setFistNameOwner(String fistNameOwner) {
        FistNameOwner = fistNameOwner;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public AnimalType getType() {
        return Type;
    }

    public void setType(AnimalType type) {
        Type = type;
    }

    private AnimalType Type;
    private String Breed, Name;
    private int Age, Weight;
    private String LastNameOwner, FistNameOwner, img;

}
