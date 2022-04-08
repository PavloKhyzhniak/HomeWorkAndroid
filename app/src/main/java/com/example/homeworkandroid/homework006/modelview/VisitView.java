package com.example.homeworkandroid.homework006.modelview;


import java.text.SimpleDateFormat;
import java.util.Date;

// Модель данных - термин для обрабатываемого типа данных
// DAO - Data Access Object - класс, отображающий структуру таблицы
// ORM - Object Relation Mapping --> Hibernate --> Spring Data
public class VisitView {

    private long Id;       // ид

    private String DoctorFirstName;   // имя
    private String DoctorSecondName;   // имя
    private String DoctorLastName;   // фамилия

    private Date Date;   // дата приема
    private int Price;      // цена

    private String PatientFirstName;   // имя
    private String PatientSecondName;   // имя
    private String PatientLastName;   // фамилия


    public VisitView(long _Id, String _DoctorFirstName, String _DoctorSecondName, String _DoctorLastName,
                     Date _Date, int _Price,
                     String _PatientFirstName, String _PatientSecondName, String _PatientLastName){
        this.Id = _Id;
        this.DoctorFirstName = _DoctorFirstName;
        this.DoctorSecondName = _DoctorSecondName;
        this.DoctorLastName = _DoctorLastName;
        this.Date = _Date;
        this.Price = _Price;
        this.PatientFirstName = _PatientFirstName;
        this.PatientSecondName = _PatientSecondName;
        this.PatientLastName = _PatientLastName;
    } // User



    @Override
    public String toString() {
        return String.format("%d : %s %s %s : %s : %d : %s %s %s", Id, DoctorFirstName, DoctorSecondName, DoctorLastName,
                new SimpleDateFormat("yyyy-MM-dd").format(Date), Price,
                PatientFirstName, PatientSecondName, PatientLastName);
    } // toString

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        if(id>0)
            Id = id;
    }

    public String getDoctorFirstName() {
        return DoctorFirstName;
    }

    public void setDoctorFirstName(String firstName) {
        if(firstName.length()>0)
            DoctorFirstName = firstName;
    }

    public String getDoctorSecondName() {
        return DoctorSecondName;
    }

    public void setDoctorSecondName(String secondName) {
        if(secondName.length()>0)
            DoctorSecondName = secondName;
    }

    public String getDoctorLastName() {
        return DoctorLastName;
    }

    public void setDoctorLastName(String lastName) {
        if(lastName.length()>0)
            DoctorLastName = lastName;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date date) {
        Date = date;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        if(price>0)
            Price = price;
    }


    public String getPatientFirstName() {
        return PatientFirstName;
    }

    public void setPatientFirstName(String firstName) {
        if(firstName.length()>0)
            PatientFirstName = firstName;
    }

    public String getPatientSecondName() {
        return PatientSecondName;
    }

    public void setPatientSecondName(String secondName) {
        if(secondName.length()>0)
            PatientSecondName = secondName;
    }

    public String getPatientLastName() {
        return PatientLastName;
    }

    public void setPatientLastName(String lastName) {
        if(lastName.length()>0)
            PatientLastName = lastName;
    }
} // class