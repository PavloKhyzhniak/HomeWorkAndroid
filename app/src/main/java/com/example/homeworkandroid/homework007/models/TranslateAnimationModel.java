package com.example.homeworkandroid.homework007.models;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.InverseMethod;

import com.example.homeworkandroid.BR;
import com.example.homeworkandroid.R;

public class TranslateAnimationModel extends BaseObservable {
    public static class Converter {

        public static int convertStringToInt(String text) {
            return Integer.parseInt(text);
        }

        @InverseMethod(value = "convertStringToInt")
        public static String convertIntToString(int value) {
            return Integer.toString(value);
        }


        @SuppressLint("ResourceType")
        public static int convertDeltaToColor(int degrees, Context context) {
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
    public int getFromXDeltaColor() {
        return fromXDeltaColor;
    }

    public void setFromXDeltaColor(int fromXDelta) {
        this.fromXDeltaColor = Converter.convertDeltaToColor(fromXDelta, this.context);
        notifyPropertyChanged(BR.fromDegreesColor);
    }

    @Bindable
    public int getToXDeltaColor() {
        return toXDeltaColor;
    }

    public void setToXDeltaColor(int toXDelta) {
        this.toXDeltaColor = Converter.convertDeltaToColor(toXDelta, this.context);
        notifyPropertyChanged(BR.toDegreesColor);
    }

    @Bindable
    public int getFromYDeltaColor() {
        return fromYDeltaColor;
    }

    public void setFromYDeltaColor(int fromYDelta) {
        this.toYDeltaColor = Converter.convertDeltaToColor(fromYDelta, this.context);
        notifyPropertyChanged(BR.toYDeltaColor);
    }

    @Bindable
    public int getToYDeltaColor() {
        return toYDeltaColor;
    }

    public void setToYDeltaColor(int toYDelta) {
        this.toYDeltaColor = Converter.convertDeltaToColor(toYDelta, this.context);
        notifyPropertyChanged(BR.toYDeltaColor);
    }

    @Bindable
    public int getDurationColor() {
        return durationColor;
    }

    public void setDurationColor(int duration) {
        this.durationColor = Converter.convertDurationToColor(duration, this.context);
        notifyPropertyChanged(BR.durationColor);
    }

    int fromYDeltaColor;
    int toYDeltaColor;
    int fromXDeltaColor;
    int toXDeltaColor;
    int durationColor;

    int fromXDelta;
    int fromXDeltaMin;
    int fromXDeltaMax;
    int toXDelta;
    int toXDeltaMin;
    int toXDeltaMax;
    int fromYDelta;
    int fromYDeltaMin;
    int fromYDeltaMax;
    int toYDelta;
    int toYDeltaMin;
    int toYDeltaMax;
    int duration;
    int durationMin;
    int durationMax;

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


    public TranslateAnimationModel(
            int fromXDelta,
            int fromXDeltaMin,
            int fromXDeltaMax,
            int toXDelta,
            int toXDeltaMin,
            int toXDeltaMax,
            int fromYDelta,
            int fromYDeltaMin,
            int fromYDeltaMax,
            int toYDelta,
            int toYDeltaMin,
            int toYDeltaMax,
            int duration,
            int durationMin,
            int durationMax
    ) {
        this.fromXDelta = fromXDelta;
        this.fromXDeltaMin = fromXDeltaMin;
        this.fromXDeltaMax = fromXDeltaMax;
        this.toXDelta = toXDelta;
        this.toXDeltaMin = toXDeltaMin;
        this.toXDeltaMax = toXDeltaMax;
        this.fromYDelta = fromYDelta;
        this.fromYDeltaMin = fromYDeltaMin;
        this.fromYDeltaMax = fromYDeltaMax;
        this.toYDelta = toYDelta;
        this.toYDeltaMin = toYDeltaMin;
        this.toYDeltaMax = toYDeltaMax;
        this.duration = duration;
        this.durationMin = durationMin;
        this.durationMax = durationMax;
    }

    Context context;

    public TranslateAnimationModel(Context context) {
        this.context = context;
        fromXDelta = 60;
        fromXDeltaMin = 0;
        fromXDeltaMax = 360;
        toXDelta = 120;
        toXDeltaMin = 0;
        toXDeltaMax = 360;
        fromYDelta = 60;
        fromYDeltaMin = 0;
        fromYDeltaMax = 360;
        toYDelta = 120;
        toYDeltaMin = 0;
        toYDeltaMax = 360;
        duration = 3000;
        durationMin = 100;
        durationMax = 10000;
    }

    @Bindable
    public int getFromXDelta() {
        return fromXDelta;
    }

    public void setFromXDelta(int fromXDelta) {
        if (fromXDelta >= 0) {
            this.fromXDelta = fromXDelta;
            this.setFromXDeltaColor(this.fromXDelta);
        }
        notifyPropertyChanged(BR.fromXDelta);
    }

    @Bindable
    public int getFromXDeltaMin() {
        return fromXDeltaMin;
    }

    public void setFromXDeltaMin(int fromXDeltaMin) {
        if (fromXDeltaMin >= 0)
            this.fromXDeltaMin = fromXDeltaMin;
        notifyPropertyChanged(BR.fromXDeltaMin);
    }

    @Bindable
    public int getFromXDeltaMax() {
        return fromXDeltaMax;
    }

    public void setFromXDeltaMax(int fromXDeltaMax) {
        if (fromXDeltaMax >= 0)
            this.fromXDeltaMax = fromXDeltaMax;
        notifyPropertyChanged(BR.fromXDeltaMax);
    }

    @Bindable
    public int getToXDelta() {
        return toXDelta;
    }

    public void setToXDelta(int toXDelta) {
        if (toXDelta >= 0) {
            this.toXDelta = toXDelta;
            this.setToXDeltaColor(this.toXDelta);
        }
        notifyPropertyChanged(BR.toXDelta);
    }

    @Bindable
    public int getToXDeltaMin() {
        return toXDeltaMin;
    }

    public void setToXDeltaMin(int toXDeltaMin) {
        if (toXDeltaMin >= 0)
            this.toXDeltaMin = toXDeltaMin;
        notifyPropertyChanged(BR.toXDeltaMin);
    }

    @Bindable
    public int getToXDeltaMax() {
        return toXDeltaMax;
    }

    public void setToXDeltaMax(int toXDeltaMax) {
        if (toXDeltaMax >= 0)
            this.toXDeltaMax = toXDeltaMax;
        notifyPropertyChanged(BR.toXDeltaMax);
    }

    @Bindable
    public int getFromYDelta() {
        return fromYDelta;
    }

    public void setFromYDelta(int fromYDelta) {
        if (fromYDelta >= 0) {
            this.fromYDelta = fromYDelta;
            this.setFromXDeltaColor(this.fromYDelta);
        }
        notifyPropertyChanged(BR.fromYDelta);
    }

    @Bindable
    public int getFromYDeltaMin() {
        return fromYDeltaMin;
    }

    public void setFromYDeltaMin(int fromYDeltaMin) {
        if (fromYDeltaMin >= 0)
            this.fromYDeltaMin = fromYDeltaMin;
        notifyPropertyChanged(BR.fromYDeltaMin);
    }

    @Bindable
    public int getFromYDeltaMax() {
        return fromYDeltaMax;
    }

    public void setFromYDeltaMax(int fromYDeltaMax) {
        if (fromYDeltaMax >= 0)
            this.fromYDeltaMax = fromYDeltaMax;
        notifyPropertyChanged(BR.fromYDeltaMax);
    }

    @Bindable
    public int getToYDelta() {
        return toYDelta;
    }

    public void setToYDelta(int toYDelta) {
        if (toYDelta >= 0) {
            this.toYDelta = toYDelta;
            this.setToXDeltaColor(this.toYDelta);
        }
        notifyPropertyChanged(BR.toYDelta);
    }

    @Bindable
    public int getToYDeltaMin() {
        return toYDeltaMin;
    }

    public void setToYDeltaMin(int toYDeltaMin) {
        if (toYDeltaMin >= 0)
            this.toYDeltaMin = toYDeltaMin;
        notifyPropertyChanged(BR.toYDeltaMin);
    }

    @Bindable
    public int getToYDeltaMax() {
        return toYDeltaMax;
    }

    public void setToYDeltaMax(int toYDeltaMax) {
        if (toYDeltaMax >= 0)
            this.toYDeltaMax = toYDeltaMax;
        notifyPropertyChanged(BR.toYDeltaMax);
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
