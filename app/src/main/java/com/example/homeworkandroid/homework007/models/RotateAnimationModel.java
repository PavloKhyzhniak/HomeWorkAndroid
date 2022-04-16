package com.example.homeworkandroid.homework007.models;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.InverseMethod;

import com.example.homeworkandroid.BR;
import com.example.homeworkandroid.R;

public class RotateAnimationModel extends BaseObservable {
    public static class Converter {

        public static int convertStringToInt(String text) {
            return Integer.parseInt(text);
        }

        @InverseMethod(value = "convertStringToInt")
        public static String convertIntToString(int value) {
            return Integer.toString(value);
        }


        @SuppressLint("ResourceType")
        public static int convertDegreesToColor(int degrees, Context context) {
            int answer = R.color.green;
            if (degrees <= 60)
                answer = R.color.red;
            else if (degrees <= 300)
                answer = R.color.green;
            else if (degrees <= 360)
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
    public int getFromDegreesColor() {
        return fromDegreesColor;
    }

    public void setFromDegreesColor(int fromDegrees) {
        this.fromDegreesColor = Converter.convertDegreesToColor(fromDegrees, this.context);
        notifyPropertyChanged(BR.fromDegreesColor);
    }

    @Bindable
    public int getToDegreesColor() {
        return toDegreesColor;
    }

    public void setToDegreesColor(int toDegrees) {
        this.toDegreesColor = Converter.convertDegreesToColor(toDegrees, this.context);
        notifyPropertyChanged(BR.toDegreesColor);
    }

    @Bindable
    public int getDurationColor() {
        return durationColor;
    }

    public void setDurationColor(int duration) {
        this.durationColor = Converter.convertDurationToColor(duration, this.context);
        notifyPropertyChanged(BR.durationColor);
    }

    int fromDegreesColor;
    int toDegreesColor;
    int durationColor;

    int fromDegrees;
    int fromDegreesMin;
    int fromDegreesMax;
    int toDegrees;
    int toDegreesMin;
    int toDegreesMax;
    int duration;
    int durationMin;
    int durationMax;

    int pivotX;
    int pivotY;
    public static int PivotXscale = 25;
    public static int PivotYscale = 25;

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


    public RotateAnimationModel(
            int fromDegrees,
            int fromDegreesMin,
            int fromDegreesMax,
            int toDegrees,
            int toDegreesMin,
            int toDegreesMax,
            int duration,
            int durationMin,
            int durationMax
    ) {
        this.fromDegrees = fromDegrees;
        this.fromDegreesMin = fromDegreesMin;
        this.fromDegreesMax = fromDegreesMax;
        this.toDegrees = toDegrees;
        this.toDegreesMin = toDegreesMin;
        this.toDegreesMax = toDegreesMax;
        this.duration = duration;
        this.durationMin = durationMin;
        this.durationMax = durationMax;
    }

    Context context;

    public RotateAnimationModel(Context context) {
        this.context = context;
        fromDegrees = 60;
        fromDegreesMin = 0;
        fromDegreesMax = 360;
        toDegrees = 120;
        toDegreesMin = 0;
        toDegreesMax = 360;
        duration = 3000;
        durationMin = 100;
        durationMax = 10000;
    }

    @Bindable
    public int getFromDegrees() {
        return fromDegrees;
    }

    public void setFromDegrees(int fromDegrees) {
        if (fromDegrees >= 0) {
            this.fromDegrees = fromDegrees;
            this.setFromDegreesColor(this.fromDegrees);
        }
        notifyPropertyChanged(BR.fromDegrees);
    }

    @Bindable
    public int getFromDegreesMin() {
        return fromDegreesMin;
    }

    public void setFromDegreesMin(int fromDegreesMin) {
        if (fromDegreesMin >= 0)
            this.fromDegreesMin = fromDegreesMin;
        notifyPropertyChanged(BR.fromDegreesMin);
    }

    @Bindable
    public int getFromDegreesMax() {
        return fromDegreesMax;
    }

    public void setFromDegreesMax(int fromDegreesMax) {
        if (fromDegreesMax >= 0)
            this.fromDegreesMax = fromDegreesMax;
        notifyPropertyChanged(BR.fromDegreesMax);
    }

    @Bindable
    public int getToDegrees() {
        return toDegrees;
    }

    public void setToDegrees(int toDegrees) {
        if (toDegrees >= 0) {
            this.toDegrees = toDegrees;
            this.setToDegreesColor(this.toDegrees);
        }
        notifyPropertyChanged(BR.toDegrees);
    }

    @Bindable
    public int getToDegreesMin() {
        return toDegreesMin;
    }

    public void setToDegreesMin(int toDegreesMin) {
        if (toDegreesMin >= 0)
            this.toDegreesMin = toDegreesMin;
        notifyPropertyChanged(BR.toDegreesMin);
    }

    @Bindable
    public int getToDegreesMax() {
        return toDegreesMax;
    }

    public void setToDegreesMax(int toDegreesMax) {
        if (toDegreesMax >= 0)
            this.toDegreesMax = toDegreesMax;
        notifyPropertyChanged(BR.toDegreesMax);
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
