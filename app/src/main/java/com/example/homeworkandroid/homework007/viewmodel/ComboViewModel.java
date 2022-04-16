package com.example.homeworkandroid.homework007.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.homeworkandroid.homework007.models.ComboAnimationModel;

public class ComboViewModel extends ViewModel {
    // creating object of Model class
    private ComboAnimationModel model;

    public ComboAnimationModel getModel() {
        return model;
    }

    public void setModel(ComboAnimationModel model) {
        this.model = model;
        model.notifyChange();
    }
}
