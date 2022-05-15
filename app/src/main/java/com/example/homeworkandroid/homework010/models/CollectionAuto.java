package com.example.homeworkandroid.homework010.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CollectionAuto {
    private List<Auto> itemsList;

    //region Конструкторы
    public CollectionAuto() {
        this(new ArrayList<Auto>());
        initializer();
    } //

    public CollectionAuto(List<Auto> itemsList) {
        this.itemsList = itemsList;
    } //
    //endregion


    public List<Auto> getItemsList() {
        return itemsList;
    }
    public void setItemsList(List<Auto> itemsList) {
        this.itemsList = itemsList;
    }

    // заполнение коллекции начальными данными
    private void initializer() {
        itemsList.clear();
        itemsList.addAll(Arrays.asList(
                new Auto("ВАЗ","2107",1985,3.0,120000,"car.png"),
                new Auto("ЗАЗ","205",1974,2.0,90000,"car.png"),
                new Auto("ВАЗ","2102",1980,3.0,150000,"car.png"),
                new Auto("ГАЗ","1234",1987,6.0,220000,"car.png"),
                new Auto("ЛАЗ","АЛ103",1995,5.5,240000,"car.png"),
                new Auto("Волга","217",1975,4.6,260000,"car.png"),
                new Auto("ВАЗ","2105",1988,3.4,170000,"car.png"),
                new Auto("ВАЗ","2107",1991,3.3,180000,"car.png")
        ));
    } // initializer
}
