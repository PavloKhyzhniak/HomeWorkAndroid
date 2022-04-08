package com.example.homeworkandroid.homework006.models;


import java.text.SimpleDateFormat;
import java.util.Date;

// Модель данных - термин для обрабатываемого типа данных
// DAO - Data Access Object - класс, отображающий структуру таблицы
// ORM - Object Relation Mapping --> Hibernate --> Spring Data
public class Visit {

    private long Id;       // ид
    private long DoctorId;      // ИД доктора
    private long PatientId;      // ИД пациента
    private int Price;      // цена
    private Date Date;   // дата

    public Visit(long _Id, long _DoctorId, long _PatientId, int _Price, Date _Date){
        this.Id = _Id;
        this.DoctorId = _DoctorId;
        this.PatientId = _PatientId;
        this.Price = _Price;
        this.Date = _Date;
    } // User

    @Override
    public String toString() {
        return String.format("%d : %d : %d : %d : %s", Id, DoctorId, PatientId, Price, new SimpleDateFormat("yyyy-MM-dd").format(Date));
    } // toString

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        if(id>0)
            Id = id;
    }

    public long getDoctorId() {
        return DoctorId;
    }

    public void setDoctorId(long doctorId) {
        if(doctorId>0)
            DoctorId = doctorId;
    }

    public long getPatientId() {
        return PatientId;
    }

    public void setPatientId(long patientId) {
        if(patientId>0)
            PatientId = patientId;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        if(price>0)
            Price = price;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date date) {
        Date = date;
    }

} // class User