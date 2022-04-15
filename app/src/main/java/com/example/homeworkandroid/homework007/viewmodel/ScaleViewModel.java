package com.example.homeworkandroid.homework007.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.homeworkandroid.homework007.models.ScaleAnimation;

public class ScaleViewModel extends ViewModel {
        // creating object of Model class
        private ScaleAnimation model;

        public ScaleAnimation getModel() {
            return model;
        }

        public void setModel(ScaleAnimation model) {
            this.model = model;
            model.notifyChange();

        }
}
