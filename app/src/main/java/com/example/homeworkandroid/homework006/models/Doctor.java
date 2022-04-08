package com.example.homeworkandroid.homework006.models;


import com.example.homeworkandroid.R;

import java.text.SimpleDateFormat;
import java.util.Date;

// Модель данных - термин для обрабатываемого типа данных
// DAO - Data Access Object - класс, отображающий структуру таблицы
// ORM - Object Relation Mapping --> Hibernate --> Spring Data
public class Doctor {

    private long Id;       // ид
    private String Specialization;   // специализация
    private long UserId;      // ИД пользователя
    private double Rate;      // ставка

    public Doctor(long _Id, String _Specialization, long _UserId, double _Rate){
        this.Id = _Id;
        this.Specialization = _Specialization;
        this.UserId = _UserId;
        this.Rate = _Rate;
    } //


    @Override
    public String toString() {
        return String.format("%d : %s : %d : %.2f", Id, Specialization, UserId, Rate);
    } // toString

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        if(id>0)
            Id = id;
    }

    public String getSpecialization() {
        return Specialization;
    }

    public void setSpecialization(String specialization) {
        if(specialization.length()>0)
            Specialization = specialization;
    }

    public long getUserId() {
        return UserId;
    }

    public void setUserId(long userId) {
        if(userId>0)
            UserId = userId;
    }

    public double getRate() {
        return Rate;
    }

    public void setRate(double rate) {
        if(rate>0)
            Rate = rate;
    }
} // class