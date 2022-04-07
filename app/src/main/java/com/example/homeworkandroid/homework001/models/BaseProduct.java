package com.example.homeworkandroid.homework001.models;

public class BaseProduct {
    private String Name;
    private Integer Count;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getCount() {
        return Count;
    }

    public void setCount(Integer count) {
        if (count >= 0)
            Count = count;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        if (price >= 0)
            Price = price;
    }

    private Integer Price;

    public Integer Summary() {
        return this.getPrice() * this.getCount();
    }
}
