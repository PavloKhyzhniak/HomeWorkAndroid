package com.example.homeworkandroid.homework007.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.homeworkandroid.homework007.models.ComboAnimation;

public class ComboViewModel extends ViewModel {
    // creating object of Model class
    private ComboAnimation model;

    public ComboAnimation getModel() {
        return model;
    }

    public void setModel(ComboAnimation model) {
        this.model = model;
        model.notifyChange();
    }
}
