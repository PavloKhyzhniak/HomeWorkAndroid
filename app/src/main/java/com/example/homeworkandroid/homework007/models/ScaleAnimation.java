package com.example.homeworkandroid.homework007.models;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.InverseMethod;

import com.example.homeworkandroid.BR;
import com.example.homeworkandroid.R;

public class ScaleAnimation extends BaseObservable {
    public static class Converter {

        public static int convertStringToInt(String text) {
            return Integer.parseInt(text);
        }

        @InverseMethod(value = "convertStringToInt")
        public static String convertIntToString(int value) {
            return Integer.toString(value);
        }


        @SuppressLint("ResourceType")
        public static int convertScaleToColor(double scale, Context context) {
            int answer = R.color.green;
            if (scale <= 60)
                answer = R.color.red;
            else if (scale <= 300)
                answer = R.color.green;
            else if (scale <= 360)
                answer = R.color.gold;
            return ResourcesCompat.getColor(context.getResources(), answer, context.getTheme());
        }

        @SuppressLint("ResourceType")
        public static int convertDurationToColor(int duration, Context context) {
            int answer = R.color.green;
            if (duration <= 1000)
                answer = R.color.red;
            else if (duration <= 8000)
                answer = R.color.green;
            else if (duration <= 10000)
                answer = R.color.gold;
            return ResourcesCompat.getColor(context.getResources(), answer, context.getTheme());
        }

    }

    @Bindable
    public int getFromXScaleColor() {
        return fromXScaleColor;
    }

    public void setFromXScaleColor(double fromXScale) {
        this.fromXScaleColor = Converter.convertScaleToColor(fromXScale, this.context);
        notifyPropertyChanged(BR.fromXScaleColor);
    }

    @Bindable
    public int getToXScaleColor() {
        return toXScaleColor;
    }

    public void setToXScaleColor(double toXScale) {
        this.toXScaleColor = Converter.convertScaleToColor(toXScale, this.context);
        notifyPropertyChanged(BR.toXScaleColor);
    }

    @Bindable
    public int getFromYScaleColor() {
        return fromYScaleColor;
    }

    public void setFromYScaleColor(int fromYScale) {
        this.fromYScaleColor = Converter.convertScaleToColor(fromYScale, this.context);
        notifyPropertyChanged(BR.fromYScaleColor);
    }

    @Bindable
    public int getToYScaleColor() {
        return toYScaleColor;
    }

    public void setToYScaleColor(double toYScale) {
        this.toYScaleColor = Converter.convertScaleToColor(toYScale, this.context);
        notifyPropertyChanged(BR.toYScaleColor);
    }

    @Bindable
    public int getDurationColor() {
        return durationColor;
    }

    public void setDurationColor(int duration) {
        this.durationColor = Converter.convertDurationToColor(duration, this.context);
        notifyPropertyChanged(BR.durationColor);
    }

    int fromXScaleColor;
    int toXScaleColor;
    int fromYScaleColor;
    int toYScaleColor;
    int durationColor;

    int fromXScale;
    int fromXScaleMin;
    int fromXScaleMax;
    int toXScale;
    int toXScaleMin;
    int toXScaleMax;
    int fromYScale;
    int fromYScaleMin;
    int fromYScaleMax;
    int toYScale;
    int toYScaleMin;
    int toYScaleMax;
    int duration;
    int durationMin;
    int durationMax;

    int pivotX;
    int pivotY;

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
            this.repeatCount = 0xffffffff;
            notifyPropertyChanged(BR.repeatCount);
        }
        notifyPropertyChanged(BR.repeatCountInfinite);
    }

    @Bindable
    public int getRepeatCount() {
        return repeatCount;
    }

    public void setRepeatCount(int repeatCount) {
        if (repeatCount >= 0)
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

    @Bindable
    public int getPivotX() {
        return pivotX;
    }

    public void setPivotX(int pivotX) {
        if (pivotX >= 0)
            this.pivotX = pivotX;
        notifyPropertyChanged(BR.pivotX);
    }

    @Bindable
    public int getPivotY() {
        return pivotY;
    }

    public void setPivotY(int pivotY) {
        if (pivotY >= 0)
            this.pivotY = pivotY;
        notifyPropertyChanged(BR.pivotY);
    }


    public ScaleAnimation(
            int fromXScale,
            int fromXScaleMin,
            int fromXScaleMax,
            int toXScale,
            int toXScaleMin,
            int toXScaleMax,
            int fromYScale,
            int fromYScaleMin,
            int fromYScaleMax,
            int toYScale,
            int toYScaleMin,
            int toYScaleMax,
            int duration,
            int durationMin,
            int durationMax
    ) {
        this.fromXScale = fromXScale;
        this.fromXScaleMin = fromXScaleMin;
        this.fromXScaleMax = fromXScaleMax;
        this.toXScale = toXScale;
        this.toXScaleMin = toXScaleMin;
        this.toXScaleMax = toXScaleMax;
        this.fromYScale = fromYScale;
        this.fromYScaleMin = fromYScaleMin;
        this.fromYScaleMax = fromYScaleMax;
        this.toYScale = toYScale;
        this.toYScaleMin = toYScaleMin;
        this.toYScaleMax = toYScaleMax;
        this.duration = duration;
        this.durationMin = durationMin;
        this.durationMax = durationMax;
    }

    Context context;

    public ScaleAnimation(Context context) {
        this.context = context;
        fromXScale = 50;
        fromXScaleMin = 0;
        fromXScaleMax = 4;
        toXScale = 100;
        toXScaleMin = 0;
        toXScaleMax = 4;
        fromYScale = 50;
        fromYScaleMin = 0;
        fromYScaleMax = 4;
        toYScale = 100;
        toYScaleMin = 0;
        toYScaleMax = 4;
        duration = 3000;
        durationMin = 100;
        durationMax = 10000;
    }

    @Bindable
    public int getFromXScale() {
        return fromXScale;
    }

    public void setFromXScale(int fromXScale) {
        if (fromXScale >= 0) {
            this.fromXScale = fromXScale;
            this.setFromXScaleColor(this.fromXScale);
        }
        notifyPropertyChanged(BR.fromXScale);
    }

    @Bindable
    public int getFromXScaleMin() {
        return fromXScaleMin;
    }

    public void setFromXScaleMin(int fromXScaleMin) {
        if (fromXScaleMin >= 0)
            this.fromXScaleMin = fromXScaleMin;
        notifyPropertyChanged(BR.fromXScaleMin);
    }

    @Bindable
    public int getFromXScaleMax() {
        return fromXScaleMax;
    }

    public void setFromXScaleMax(int fromXScaleMax) {
        if (fromXScaleMax >= 0)
            this.fromXScaleMax = fromXScaleMax;
        notifyPropertyChanged(BR.fromXScaleMax);
    }

    @Bindable
    public int getToXScale() {
        return toXScale;
    }

    public void setToXScale(int toXScale) {
        if (toXScale >= 0) {
            this.toXScale = toXScale;
            this.setToXScaleColor(this.toXScale);
        }
        notifyPropertyChanged(BR.toXScale);
    }

    @Bindable
    public int getToXScaleMin() {
        return toXScaleMin;
    }

    public void setToXScaleMin(int toXScaleMin) {
        if (toXScaleMin >= 0)
            this.toXScaleMin = toXScaleMin;
        notifyPropertyChanged(BR.toXScaleMin);
    }

    @Bindable
    public int getToXScaleMax() {
        return toXScaleMax;
    }

    public void setToXScaleMax(int toXScaleMax) {
        if (toXScaleMax >= 0)
            this.toXScaleMax = toXScaleMax;
        notifyPropertyChanged(BR.toXScaleMax);
    }


    @Bindable
    public int getFromYScale() {
        return fromYScale;
    }

    public void setFromYScale(int fromYScale) {
        if (fromYScale >= 0) {
            this.fromYScale = fromYScale;
            this.setFromYScaleColor(this.fromYScale);
        }
        notifyPropertyChanged(BR.fromYScale);
    }

    @Bindable
    public int getFromYScaleMin() {
        return fromYScaleMin;
    }

    public void setFromYScaleMin(int fromYScaleMin) {
        if (fromYScaleMin >= 0)
            this.fromYScaleMin = fromYScaleMin;
        notifyPropertyChanged(BR.fromYScaleMin);
    }

    @Bindable
    public int getFromYScaleMax() {
        return fromYScaleMax;
    }

    public void setFromYScaleMax(int fromYScaleMax) {
        if (fromYScaleMax >= 0)
            this.fromYScaleMax = fromYScaleMax;
        notifyPropertyChanged(BR.fromYScaleMax);
    }

    @Bindable
    public int getToYScale() {
        return toYScale;
    }

    public void setToYScale(int toYScale) {
        if (toYScale >= 0) {
            this.toYScale = toYScale;
            this.setToYScaleColor(this.toYScale);
        }
        notifyPropertyChanged(BR.toYScale);
    }

    @Bindable
    public int getToYScaleMin() {
        return toYScaleMin;
    }

    public void setToYScaleMin(int toYScaleMin) {
        if (toYScaleMin >= 0)
            this.toYScaleMin = toYScaleMin;
        notifyPropertyChanged(BR.toYScaleMin);
    }

    @Bindable
    public int getToYScaleMax() {
        return toYScaleMax;
    }

    public void setToYScaleMax(int toYScaleMax) {
        if (toYScaleMax >= 0)
            this.toYScaleMax = toYScaleMax;
        notifyPropertyChanged(BR.toYScaleMax);
    }


    @Bindable
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        if (duration >= 0) {
            this.duration = duration;
            this.setDurationColor(this.duration);
        }
        notifyPropertyChanged(BR.duration);
    }

    @Bindable
    public int getDurationMin() {
        return durationMin;
    }

    public void setDurationMin(int durationMin) {
        if (durationMin >= 0)
            this.durationMin = durationMin;
        notifyPropertyChanged(BR.durationMin);
    }

    @Bindable
    public int getDurationMax() {
        return durationMax;
    }

    public void setDurationMax(int durationMax) {
        if (durationMax >= 0)
            this.durationMax = durationMax;
        notifyPropertyChanged(BR.durationMax);
    }


}
