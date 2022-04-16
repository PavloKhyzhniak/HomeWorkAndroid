package com.example.homeworkandroid.homework007.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CollectionAviasales {
    private List<Aviasales> itemsList;

    //region Конструкторы
    public CollectionAviasales() {
        this(new ArrayList<Aviasales>());
        initializer();
    } //

    public CollectionAviasales(List<Aviasales> itemsList) {
        this.itemsList = itemsList;
    } //
    //endregion


    public List<Aviasales> getItemsList() {
        return itemsList;
    }
    public void setItemsList(List<Aviasales> itemsList) {
        this.itemsList = itemsList;
    }

    // заполнение коллекции начальными данными
    private void initializer() {
        itemsList.clear();
        itemsList.addAll(Arrays.asList(
                new Aviasales("Москва",345234,"Кулич","Антон",new Date()),
                new Aviasales("Москва",234455,"Сатинов","Марина",new Date()),
                new Aviasales("Москва",332244,"Мартынов","Владимир",new Date()),
                new Aviasales("Москва",554433,"Агафий","Александр",new Date()),
                new Aviasales("Рига",554432,"Цымблаков","Лилия",new Date()),
                new Aviasales("Рига",543212,"Шукин","Вано",new Date()),
                new Aviasales("Рига",654345,"Огарев","Джо",new Date()),
                new Aviasales("Рига",654445,"Непутов","Гектор",new Date()),
                new Aviasales("Казань",665544,"Лапенко","Хелена",new Date()),
                new Aviasales("Казань",656453,"Горев","Миша",new Date()),
                new Aviasales("Казань",654234,"Шотов","Галина",new Date()),
                new Aviasales("Казань",654564,"Лосев","Анна",new Date()),
                new Aviasales("Казань",666666,"Ников","Евгений",new Date()),
                new Aviasales("Минск",555555,"Радов","Анатолий",new Date()),
                new Aviasales("Минск",444444,"Титов","Денис",new Date()),
                new Aviasales("Минск",333444,"Долин","Илья",new Date()),
                new Aviasales("Минск",555333,"Роков","Александров",new Date()),
                new Aviasales("Минск",555444,"Толоков","Василий",new Date()),
                new Aviasales("Дели",333444,"Локомотин","Сергей",new Date()),
                new Aviasales("Дели",333555,"Ратов","Павел",new Date()),
                new Aviasales("Дели",776655,"Самов","Карина",new Date()),
                new Aviasales("Дели",777666,"Укошко","Василина",new Date()),
                new Aviasales("Дели",666777,"Маленький","Дарья",new Date()),
                new Aviasales("Киев",555666,"Зарубов","Марина",new Date()),
                new Aviasales("Киев",555777,"Жорин","Георгий",new Date()),
                new Aviasales("Киев",576576,"Хорошун","Иван",new Date()),
                new Aviasales("Киев",887665,"Жаров","Елена",new Date())
        ));
    } // initializer
}
