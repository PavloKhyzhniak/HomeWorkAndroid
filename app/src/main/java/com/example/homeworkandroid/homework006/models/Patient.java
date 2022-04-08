package com.example.homeworkandroid.homework006.models;


// Модель данных - термин для обрабатываемого типа данных
// DAO - Data Access Object - класс, отображающий структуру таблицы
// ORM - Object Relation Mapping --> Hibernate --> Spring Data
public class Patient {

    private long Id;       // ид
    private long UserId;      // ИД пользователя

    public Patient(long _Id, long _UserId){
        this.Id = _Id;
        this.UserId = _UserId;
    } //


    @Override
    public String toString() {
        return String.format("%d : %d", Id, UserId);
    } // toString

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        if(id>0)
            Id = id;
    }

    public long getUserId() {
        return UserId;
    }

    public void setUserId(long userId) {
        if(userId>0)
            UserId = userId;
    }

} // class