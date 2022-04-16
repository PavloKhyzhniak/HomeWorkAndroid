package com.example.homeworkandroid.homework007.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.homeworkandroid.homework007.models.ScaleAnimationModel;

public class ScaleViewModel extends ViewModel {
    // creating object of Model class
    private ScaleAnimationModel model;

    public ScaleAnimationModel getModel() {
        return model;
    }

    public void setModel(ScaleAnimationModel model) {
        this.model = model;
        model.notifyChange();

    }
}
