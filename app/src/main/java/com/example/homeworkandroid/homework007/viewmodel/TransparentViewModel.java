package com.example.homeworkandroid.homework007.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.homeworkandroid.homework007.models.TranslateAnimationModel;

public class TransparentViewModel extends ViewModel {
    // creating object of Model class
    private TranslateAnimationModel model;

    public TranslateAnimationModel getModel() {
        return model;
    }

    public void setModel(TranslateAnimationModel model) {
        this.model = model;
        model.notifyChange();

    }
}
