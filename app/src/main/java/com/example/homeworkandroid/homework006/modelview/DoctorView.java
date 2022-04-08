package com.example.homeworkandroid.homework006.modelview;


import java.text.SimpleDateFormat;
import java.util.Date;

// Модель данных - термин для обрабатываемого типа данных
// DAO - Data Access Object - класс, отображающий структуру таблицы
// ORM - Object Relation Mapping --> Hibernate --> Spring Data
public class DoctorView {

    private long Id;       // ид
    private String FirstName;   // имя
    private String SecondName;   // имя
    private String LastName;   // фамилия
    private Date BirthDate;   // ДР

    private String Specialization;   // специализация
    private double Rate;      // ставка

    public DoctorView(long _Id, String _FirstName, String _SecondName, String _LastName, Date _BirthDate,
                      String _Specialization, double _Rate){
        this.Id = _Id;
        this.FirstName = _FirstName;
        this.SecondName = _SecondName;
        this.LastName = _LastName;
        this.BirthDate = _BirthDate;

        this.Specialization = _Specialization;
        this.Rate = _Rate;
    } // User



    @Override
    public String toString() {
        return String.format("%d : %s %s %s %s : %s : %.2f",
                Id, FirstName, SecondName, LastName, new SimpleDateFormat("yyyy-MM-dd").format(BirthDate),
                Specialization, Rate);
    } // toString

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        if(id>0)
            Id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        if(firstName.length()>0)
            FirstName = firstName;
    }

    public String getSecondName() {
        return SecondName;
    }

    public void setSecondName(String secondName) {
        if(secondName.length()>0)
            SecondName = secondName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        if(lastName.length()>0)
            LastName = lastName;
    }

    public Date getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(Date birthDate) {
        BirthDate = birthDate;
    }

    public String getSpecialization() {
        return Specialization;
    }

    public void setSpecialization(String specialization) {
        if(specialization.length()>0)
            Specialization = specialization;
    }

    public double getRate() {
        return Rate;
    }

    public void setRate(double rate) {
        if(rate>0)
            Rate = rate;
    }
} // class User