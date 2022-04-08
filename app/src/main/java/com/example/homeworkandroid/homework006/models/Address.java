package com.example.homeworkandroid.homework006.models;


import java.text.SimpleDateFormat;
import java.util.Date;

// Модель данных - термин для обрабатываемого типа данных
// DAO - Data Access Object - класс, отображающий структуру таблицы
// ORM - Object Relation Mapping --> Hibernate --> Spring Data
public class Address {

    private long Id;       // ид
    private String Street;   // улица
    private int House;   // дом
    private int Appartment;   // квартира

    public Address(long _Id, String _Street, int _House, int _Appartment){
        this.Id = _Id;
        this.Street = _Street;
        this.House = _House;
        this.Appartment = _Appartment;
    } // User



    @Override
    public String toString() {
        return String.format("%d : %s %d %d", Id, Street, House, Appartment);
    } // toString

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        if(id>0)
            Id = id;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        if(street.length()>0)
            Street = street;
    }

    public int getHouse() {
        return House;
    }

    public void setHouse(int house) {
        if(house>0)
            House = house;
    }

    public int getAppartment() {
        return Appartment;
    }

    public void setAppartment(int appartment) {
        if(appartment>0)
            Appartment = appartment;
    }
} // class User