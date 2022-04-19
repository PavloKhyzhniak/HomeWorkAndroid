package com.example.homeworkandroid.homework007.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.homeworkandroid.BR;

public class BaseAnimationModel extends BaseObservable {

    int repeatCount;
    boolean repeatCountInfinite = false;
    boolean repeatMode = false;

    @Bindable
    public boolean isRepeatCountInfinite() {
        return repeatCountInfinite;
    }

    public void setRepeatCountInfinite(boolean repeatCountInfinite) {
        this.repeatCountInfinite = repeatCountInfinite;
        if (this.repeatCountInfinite) {
            setRepeatCount(-1);
        } else {
            setRepeatCount(0);
        }
        notifyPropertyChanged(BR.repeatCountInfinite);
    }

    @Bindable
    public int getRepeatCount() {
        return repeatCount;
    }

    public void setRepeatCount(int repeatCount) {
        if (repeatCount >= 0 || repeatCount == -1)
            this.repeatCount = repeatCount;
        notifyPropertyChanged(BR.repeatCount);
    }


    @Bindable
    public boolean isRepeatMode() {
        return repeatMode;
    }

    public void setRepeatMode(boolean repeatMode) {
        this.repeatMode = repeatMode;
        notifyPropertyChanged(BR.repeatMode);
    }

}
