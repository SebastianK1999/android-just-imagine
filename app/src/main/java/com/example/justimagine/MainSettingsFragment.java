package com.example.justimagine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.justimagine.databinding.FragmentMainSettingsBinding;

public class MainSettingsFragment extends Fragment {
    FragmentMainSettingsBinding mBinding;

    public MainSettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentMainSettingsBinding.inflate(getLayoutInflater());
        View v = mBinding.getRoot();


        return v;
    }
}
