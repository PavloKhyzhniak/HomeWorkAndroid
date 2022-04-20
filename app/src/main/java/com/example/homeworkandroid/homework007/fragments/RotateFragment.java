package com.example.homeworkandroid.homework007.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.homeworkandroid.R;
import com.example.homeworkandroid.databinding.RotateAnimationBinding;
import com.example.homeworkandroid.homework007.models.RotateAnimationModel;
import com.example.homeworkandroid.homework007.viewmodel.RotateViewModel;

public class RotateFragment extends Fragment {

    public RotateViewModel rotateViewModel;

    public RotateFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rotateViewModel = new ViewModelProvider(getActivity()).get(RotateViewModel.class);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.homework007_fragment_rotate, container, false);

        RotateAnimationBinding binding = DataBindingUtil.inflate(inflater, R.layout.homework007_fragment_rotate, container, false);

        RotateAnimationModel rotateAnimationModel = new RotateAnimationModel(this.getContext());
        binding.setRotate(rotateAnimationModel);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rotateViewModel.setModel(rotateAnimationModel);
            }
        }, 1000);

        return binding.getRoot();
    }

}