package com.example.homeworkandroid.homework006.modelview;


import java.text.SimpleDateFormat;
import java.util.Date;

// Модель данных - термин для обрабатываемого типа данных
// DAO - Data Access Object - класс, отображающий структуру таблицы
// ORM - Object Relation Mapping --> Hibernate --> Spring Data
public class PatientView {

    private long Id;       // ид
    private String FirstName;   // имя
    private String SecondName;   // имя
    private String LastName;   // фамилия
    private Date BirthDate;   // ДР

    private String Street;   // улица
    private int House;   // дом
    private int Appartment;   // квартира


    public PatientView(long _Id, String _FirstName, String _SecondName, String _LastName, Date _BirthDate,
                       String _Street, int _House, int _Appartment){
        this.Id = _Id;
        this.FirstName = _FirstName;
        this.SecondName = _SecondName;
        this.LastName = _LastName;
        this.BirthDate = _BirthDate;

        this.Street = _Street;
        this.House = _House;
        this.Appartment = _Appartment;
    } // User



    @Override
    public String toString() {
        return String.format("%d : %s %s %s %s :  %s %d %d",
                Id, FirstName, SecondName, LastName, new SimpleDateFormat("yyyy-MM-dd").format(BirthDate),
                Street, House, Appartment);
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