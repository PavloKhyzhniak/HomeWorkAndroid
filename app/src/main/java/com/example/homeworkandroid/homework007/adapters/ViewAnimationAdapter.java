package com.example.homeworkandroid.homework007.adapters;

//import android.databinding.BindingAdapter;
//        import android.support.v4.view.animation.FastOutSlowInInterpolator;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

import com.example.homeworkandroid.homework007.models.BaseAnimationModel;
import com.example.homeworkandroid.homework007.models.RotateAnimationModel;
import com.example.homeworkandroid.homework007.models.ScaleAnimationModel;
import com.example.homeworkandroid.homework007.models.TranslateAnimationModel;

public class ViewAnimationAdapter {
    private static final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();

    public static BaseAnimationModel[] modelsBase;

    @BindingAdapter("isBusy")
    public static void setIsBusy(View view, boolean isBusy) {
        Animation animation = view.getAnimation();
        if (isBusy && animation == null) {
            view.clearAnimation();
            view.startAnimation(createAnimation());
        } else if (animation != null) {
            animation.cancel();
            view.setAnimation(null);
        }
    }

    private static Animation createAnimation() {
        if (modelsBase == null) {
            RotateAnimation anim = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            anim.setInterpolator(INTERPOLATOR);
            anim.setDuration(1400);
            anim.setRepeatCount(TranslateAnimation.INFINITE);
            anim.setRepeatMode(TranslateAnimation.RESTART);
            return anim;
        }

        AnimationSet animSet = new AnimationSet(true);

        for (BaseObservable modelBase : modelsBase) {

            if (modelBase instanceof ScaleAnimationModel) {
                ScaleAnimationModel model = (ScaleAnimationModel) modelBase;

                animSet.addAnimation(model.getAnimation());
            }

            if (modelBase instanceof RotateAnimationModel) {
                RotateAnimationModel model = (RotateAnimationModel) modelBase;

                animSet.addAnimation(model.getAnimation());
            }

            if (modelBase instanceof TranslateAnimationModel) {
                TranslateAnimationModel model = (TranslateAnimationModel) modelBase;

                animSet.addAnimation(model.getAnimation());
            }
        }

        return animSet;
    }
}