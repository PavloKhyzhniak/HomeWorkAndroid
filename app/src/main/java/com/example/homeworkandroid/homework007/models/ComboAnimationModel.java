package com.example.homeworkandroid.homework007.models;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.homeworkandroid.BR;

public class ComboAnimationModel extends BaseObservable {
    RotateAnimationModel rotateAnimationModel;
    ScaleAnimationModel scaleAnimationModel;

    @Bindable
    public RotateAnimationModel getRotateAnimation() {
        return rotateAnimationModel;
    }

    public void setRotateAnimation(RotateAnimationModel rotateAnimationModel) {
        this.rotateAnimationModel = rotateAnimationModel;
        notifyPropertyChanged(BR.rotateAnimation);
    }


    @Bindable
    public ScaleAnimationModel getScaleAnimation() {
        return scaleAnimationModel;
    }

    public void setScaleAnimation(ScaleAnimationModel scaleAnimationModel) {
        this.scaleAnimationModel = scaleAnimationModel;
        notifyPropertyChanged(BR.scaleAnimation);
    }

    Context context;

    public ComboAnimationModel(Context context) {
        this.context = context;
    }
}

