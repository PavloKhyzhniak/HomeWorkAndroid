package com.example.homeworkandroid.homework008.models;

import com.example.homeworkandroid.homework007.models.Aviasales;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CollectionMessage {
    private List<Message> itemsList;

    //region Конструкторы
    public CollectionMessage() {
        this(new ArrayList<Message>());
        initializer();
    } //

    public CollectionMessage(List<Message> itemsList) {
        this.itemsList = itemsList;
    } //
    //endregion


    public List<Message> getItemsList() {
        return itemsList;
    }
    public void setItemsList(List<Message> itemsList) {
        this.itemsList = itemsList;
    }

    // заполнение коллекции начальными данными
    private void initializer() {
        itemsList.clear();
        itemsList.addAll(Arrays.asList(
                new Message("Привет мир!",345,Boolean.FALSE),
                new Message("Hello World!!!",887,Boolean.TRUE)
        ));
    } // initializer
}
