package com.example.homeworkandroid.homework007.models;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import com.example.homeworkandroid.BR;

public class ComboAnimation extends BaseObservable {
    RotateAnimation rotateAnimation;
    ScaleAnimation scaleAnimation;

    @Bindable
    public RotateAnimation getRotateAnimation() {
        return rotateAnimation;
    }

    public void setRotateAnimation(RotateAnimation rotateAnimation) {
        this.rotateAnimation = rotateAnimation;
        notifyPropertyChanged(BR.rotateAnimation);
    }

    @Bindable
    public ScaleAnimation getScaleAnimation() {
        return scaleAnimation;
    }

    public void setScaleAnimation(ScaleAnimation scaleAnimation) {
        this.scaleAnimation = scaleAnimation;
        notifyPropertyChanged(BR.scaleAnimation);
    }

    Context context;

    public ComboAnimation(Context context) {
        this.context = context;
    }
}

