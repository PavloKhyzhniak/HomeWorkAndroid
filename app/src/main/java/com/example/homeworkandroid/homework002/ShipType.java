package com.example.homeworkandroid.homework002;

import java.io.Serializable;

public enum ShipType implements Serializable {
    bulkCarrier("балкер.jpg", "балкер"),
    containerShip("контейнеровоз.jpg", "контейнеровоз"),
    ferry("паром.jpg", "паром"),
    rolker("ролкер.jpg", "ролкер"),
    dryCargoShip("сухогруз.jpg", "сухогруз"),
    tanker("танкер.jpg", "танкер");

    private String base;
    private String name;

    ShipType() {
    }

    ShipType(String _filename, String _name) {
        this.base = "Ship/" + _filename;
        this.name = _name;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
