package com.example.homeworkandroid.homework006.modelview;


import java.text.SimpleDateFormat;
import java.util.Date;

// Модель данных - термин для обрабатываемого типа данных
// DAO - Data Access Object - класс, отображающий структуру таблицы
// ORM - Object Relation Mapping --> Hibernate --> Spring Data
public class VisitMaxPriceView {

    private Date Date;   // дата
    private int Price;      // стоимость

    public VisitMaxPriceView(Date _Date, int _Price){
        this.Date = _Date;
        this.Price = _Price;
    } // User

    @Override
    public String toString() {
        return String.format("%s : %d", new SimpleDateFormat("yyyy-MM-dd").format(Date), Price);
    } // toString

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
} // class User