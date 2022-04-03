package com.example.homeworkandroid.homework004.models;

import java.io.Serializable;

public enum TVShopManufacturer implements Serializable {
    Sony("Sony.png", "Sony")
    , LG("LG.png", "LG")
    , Samsung("Samsung.png", "Samsung")
    , Panasonic("Panasonic.png", "Panasonic")
    , Philips("Philips.png", "Philips")
    , TLC("TLC.png", "TLC")
    , Hyundai("Hyundai.png", "Hyundai")
    , BBKElectronics("BBKElectronics.png", "BBKElectronics")
    , Supra("Supra.png", "Supra")
    , Thomson("Thomson.png", "Thomson")
    ;

    private String base;
    private String name;

    TVShopManufacturer() {
    }

    TVShopManufacturer(String _filename, String _name) {
        this.base = "TVShop/Manufacturer/" + _filename;
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

