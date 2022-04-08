package com.example.homeworkandroid.homework006.modelview;


import java.text.SimpleDateFormat;
import java.util.Date;

// Модель данных - термин для обрабатываемого типа данных
// DAO - Data Access Object - класс, отображающий структуру таблицы
// ORM - Object Relation Mapping --> Hibernate --> Spring Data
public class DoctorPriceView extends DoctorView{

    private double Summary;      // зарплата за вычетом 13 процентов

    public DoctorPriceView(long _Id, String _FirstName, String _SecondName, String _LastName, Date _BirthDate,
                           String _Specialization, double _Rate,
                           double _Summary){
        super(_Id, _FirstName, _SecondName, _LastName, _BirthDate, _Specialization, _Rate);

        this.Summary = _Summary;
    } // User

    @Override
    public String toString() {
        return String.format("%d : %s %s %s %s : %s : %.2f : %.2f",
                getId(), getFirstName(), getSecondName(), getLastName(), new SimpleDateFormat("yyyy-MM-dd").format(getBirthDate()),
                getSpecialization(), getRate(),
                Summary);
    } // toString

    public double getSummary() {
        return Summary;
    }

    public void setSummary(double summary) {
        if(summary>0)
            Summary = summary;
    }

} // class User