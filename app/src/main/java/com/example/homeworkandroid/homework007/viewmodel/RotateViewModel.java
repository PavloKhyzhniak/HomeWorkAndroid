package com.example.homeworkandroid.homework007.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.ViewModel;

import com.example.homeworkandroid.BR;
import com.example.homeworkandroid.homework007.models.RotateAnimation;

public class RotateViewModel extends ViewModel {
        // creating object of Model class
        private RotateAnimation model;

        public RotateAnimation getModel() {
            return model;
        }

        public void setModel(RotateAnimation model) {
            this.model = model;
            model.notifyChange();

        }
}
