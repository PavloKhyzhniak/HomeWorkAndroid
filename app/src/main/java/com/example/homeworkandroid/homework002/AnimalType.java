package com.example.homeworkandroid.homework002;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public enum AnimalType implements Serializable {
    bird("bird.png", "птица"), bull("bull.png", "бык"), cat("cat.png", "кошка"), cow("cow.png", "корова"),
    dog("dog.png", "собака"), duck("duck.png", "утка"), elephant("elephant.png", "слон"), fish("fish.png", "рыба"),
    horse("horse.png", "лошадь"), ladybug("ladybug.png", "божья коровка"), leopard("leopard.png", "леопард"),
    lion("lion.png", "лев"), lobster("lobster.png", "лобстер"), rabbit("rabbit.png", "кролик"),
    snail("snail.png", "улитка"), turtle("turtle.png", "черепаха");

    private String base;
    private String base_contour;
    private String name;

    AnimalType() {
    }

    AnimalType(String _filename, String _name) {
        this.base = "Animals-mini/png/64x64/" + _filename;
        this.base_contour = "Animals-mini/png/64x64_contour/" + _filename;
        this.name = _name;
    }

    AnimalType(String _base, String _base_contour, String _name) {
        this.base = _base;
        this.base_contour = _base_contour;
        this.name = _name;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getBase_contour() {
        return base_contour;
    }

    public void setBase_contour(String base_contour) {
        this.base_contour = base_contour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

