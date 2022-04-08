package com.example.homeworkandroid.homework006.modelview;


import java.text.SimpleDateFormat;
import java.util.Date;

// Модель данных - термин для обрабатываемого типа данных
// DAO - Data Access Object - класс, отображающий структуру таблицы
// ORM - Object Relation Mapping --> Hibernate --> Spring Data
public class SpecializationAvgRateView {

    private String Specialization;   // специализация
    private double AVG_Rate;      // ставка

    public SpecializationAvgRateView(String _Specialization, double _AVG_Rate){
        this.Specialization = _Specialization;
        this.AVG_Rate = _AVG_Rate;
    } // User



    @Override
    public String toString() {
        return String.format("%s : %.2f",
                Specialization, AVG_Rate);
    } // toString

    public String getSpecialization() {
        return Specialization;
    }

    public void setSpecialization(String specialization) {
        if(specialization.length()>0)
            Specialization = specialization;
    }

    public double getRate() {
        return AVG_Rate;
    }

    public void setRate(double rate) {
        if(rate>0)
            AVG_Rate = rate;
    }
} // class User