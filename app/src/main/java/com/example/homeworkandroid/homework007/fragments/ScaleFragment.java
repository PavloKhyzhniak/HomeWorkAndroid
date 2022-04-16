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
import com.example.homeworkandroid.databinding.ScaleAnimation;
import com.example.homeworkandroid.homework007.models.ScaleAnimationModel;
import com.example.homeworkandroid.homework007.viewmodel.ScaleViewModel;


public class ScaleFragment extends Fragment {

    public ScaleViewModel scaleViewModel;

    public ScaleFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scaleViewModel = new ViewModelProvider(getActivity()).get(ScaleViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.homework007_fragment_scale, container, false);
        ScaleAnimation binding = DataBindingUtil.inflate(inflater, R.layout.homework007_fragment_scale, container, false);
        ScaleAnimationModel scaleAnimationModel = new ScaleAnimationModel(this.getContext());
        binding.setScale(scaleAnimationModel);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                scaleViewModel.setModel(scaleAnimationModel);
            }
        }, 1000);

        return binding.getRoot();
    }
}