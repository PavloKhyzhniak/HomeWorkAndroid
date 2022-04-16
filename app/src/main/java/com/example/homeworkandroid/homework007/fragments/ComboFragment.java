package com.example.homeworkandroid.homework007.fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.homeworkandroid.R;
import com.example.homeworkandroid.databinding.ComboAnimation;
import com.example.homeworkandroid.databinding.RotateAnimation;
import com.example.homeworkandroid.databinding.ScaleAnimation;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

//        return inflater.inflate(R.layout.homework007_fragment_combo, container, false);

        ComboAnimation binding = DataBindingUtil.inflate(inflater, R.layout.homework007_fragment_combo, container, false);
        final com.example.homeworkandroid.homework007.models.ComboAnimation comboAnimation = new com.example.homeworkandroid.homework007.models.ComboAnimation(this.getContext());

        final com.example.homeworkandroid.homework007.models.RotateAnimation rotateComboAnimation = new com.example.homeworkandroid.homework007.models.RotateAnimation(this.getContext());
        final com.example.homeworkandroid.homework007.models.ScaleAnimation scaleComboAnimation = new com.example.homeworkandroid.homework007.models.ScaleAnimation(this.getContext());

        comboAnimation.setRotateAnimation(rotateComboAnimation);
        comboAnimation.setScaleAnimation(scaleComboAnimation);

        comboViewModel.setModel(comboAnimation);

        binding.setCombo(comboAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                comboViewModel.setModel(comboAnimation);
            }
        }, 1000);


        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();

        ScaleAnimation bindingScale = DataBindingUtil.bind(getActivity().findViewById(R.id.frComboScale).findViewById(R.id.fragment_scale));
        assert bindingScale != null;
        bindingScale.setScale(comboViewModel.getModel().getScaleAnimation());

        RotateAnimation bindingRotate = DataBindingUtil.bind(getActivity().findViewById(R.id.frComboRotate).findViewById(R.id.fragment_rotate));
        assert bindingRotate != null;
        bindingRotate.setRotate(comboViewModel.getModel().getRotateAnimation());
    }
}