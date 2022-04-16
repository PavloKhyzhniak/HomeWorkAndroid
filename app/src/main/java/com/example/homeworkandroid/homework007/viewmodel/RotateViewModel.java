package com.example.homeworkandroid.homework007.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.homeworkandroid.homework007.models.RotateAnimationModel;

public class RotateViewModel extends ViewModel {
    // creating object of Model class
    private RotateAnimationModel model;

    public RotateAnimationModel getModel() {
        return model;
    }

    public void setModel(RotateAnimationModel model) {
        this.model = model;
        model.notifyChange();

    }
}
