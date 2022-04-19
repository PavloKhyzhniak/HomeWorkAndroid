package com.example.homeworkandroid.homework007.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.homeworkandroid.BR;

public class ViewAnimationModel extends BaseObservable {

    @Bindable
    public boolean isRefreshAnimation() {
        return refreshAnimation;
    }

    public void setRefreshAnimation(boolean refreshAnimation) {
        this.refreshAnimation = refreshAnimation;
        notifyPropertyChanged(BR.refreshAnimation);
    }

    boolean refreshAnimation;

    public ViewAnimationModel(
            boolean refreshAnimation
    ) {
        this.refreshAnimation = refreshAnimation;
    }

    public ViewAnimationModel() {
        refreshAnimation = false;
    }
}
