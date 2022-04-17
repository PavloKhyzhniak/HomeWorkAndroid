package com.example.homeworkandroid.homework007.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.homeworkandroid.R;
import com.example.homeworkandroid.databinding.TranslateAnimation;
import com.example.homeworkandroid.homework007.models.TranslateAnimationModel;
import com.example.homeworkandroid.homework007.viewmodel.TransparentViewModel;

public class TranslateFragment extends Fragment {

    public TransparentViewModel translateViewModel;

    public TranslateFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        translateViewModel = new ViewModelProvider(getActivity()).get(TransparentViewModel.class);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.homework007_fragment_rotate, container, false);

        TranslateAnimation binding = DataBindingUtil.inflate(inflater, R.layout.homework007_fragment_translate, container, false);

        TranslateAnimationModel translateAnimationModel = new TranslateAnimationModel(this.getContext());
        binding.setTranslate(translateAnimationModel);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                translateViewModel.setModel(translateAnimationModel);
            }
        }, 1000);

        return binding.getRoot();
    }

}