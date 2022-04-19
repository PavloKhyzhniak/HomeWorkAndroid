package com.example.homeworkandroid.homework007.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.ViewModel;

import com.example.homeworkandroid.homework007.models.ComboAnimationModel;
import com.example.homeworkandroid.homework007.models.ViewAnimationModel;

public class ViewAnimationViewModel extends ViewModel {
    // creating object of Model class
    private ViewAnimationModel model;

    public ViewAnimationModel getModel() {
        return model;
    }

    public void setModel(ViewAnimationModel model) {
        this.model = model;
        model.notifyChange();
    }
}
