package com.example.homeworkandroid.homework007.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.homeworkandroid.R;
import com.example.homeworkandroid.databinding.ComboAnimationBinding;
import com.example.homeworkandroid.databinding.RotateAnimationBinding;
import com.example.homeworkandroid.databinding.ScaleAnimationBinding;
import com.example.homeworkandroid.homework007.models.ComboAnimationModel;
import com.example.homeworkandroid.homework007.models.RotateAnimationModel;
import com.example.homeworkandroid.homework007.models.ScaleAnimationModel;
import com.example.homeworkandroid.homework007.viewmodel.ComboViewModel;

public class ComboFragment extends Fragment {

    public ComboViewModel getComboViewModel() {
        return comboViewModel;
    }

    public void setComboViewModel(ComboViewModel comboViewModel) {
        this.comboViewModel = comboViewModel;
    }

    public ComboViewModel comboViewModel;

    public ComboFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        comboViewModel = new ViewModelProvider(getActivity()).get(ComboViewModel.class);

    }

    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

//        return inflater.inflate(R.layout.homework007_fragment_combo, container, false);

        context = this.getContext();

        ComboAnimationBinding binding = DataBindingUtil.inflate(inflater, R.layout.homework007_fragment_combo, container, false);
        ComboAnimationModel comboAnimationModel = new ComboAnimationModel(this.getContext());

        comboViewModel.setModel(comboAnimationModel);

        binding.setCombo(comboAnimationModel);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // получаем ссылку на фрагмент-приемник
                RotateFragment fragmentRotate = (RotateFragment) ((AppCompatActivity)context).getSupportFragmentManager()
                        .findFragmentById(R.id.frCombo).getChildFragmentManager().findFragmentById(R.id.frComboRotate);

                RotateAnimationBinding bindingRotate = DataBindingUtil.bind(fragmentRotate.getView());
                RotateAnimationModel rotateAnimationModel = new RotateAnimationModel(context);
                assert bindingRotate != null;
                bindingRotate.setRotate(rotateAnimationModel);
                fragmentRotate.rotateViewModel.setModel(rotateAnimationModel);
                comboAnimationModel.setRotateAnimation(fragmentRotate.rotateViewModel.getModel());

                // получаем ссылку на фрагмент-приемник
                ScaleFragment fragmentScale = (ScaleFragment) ((AppCompatActivity)context).getSupportFragmentManager()
                        .findFragmentById(R.id.frCombo).getChildFragmentManager().findFragmentById(R.id.frComboScale);

                ScaleAnimationBinding bindingScale = DataBindingUtil.bind(fragmentScale.getView());
                ScaleAnimationModel scaleAnimationModel = new ScaleAnimationModel(context);
                assert bindingScale != null;
                bindingScale.setScale(scaleAnimationModel);
                fragmentScale.scaleViewModel.setModel(scaleAnimationModel);
                comboAnimationModel.setScaleAnimation(fragmentScale.scaleViewModel.getModel());

                comboViewModel.setModel(comboAnimationModel);
            }
        }, 1000);


        return binding.getRoot();
    }

}