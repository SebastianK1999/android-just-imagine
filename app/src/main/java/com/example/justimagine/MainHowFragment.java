package com.example.justimagine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.justimagine.databinding.FragmentMainHowBinding;

public class MainHowFragment extends Fragment {
    FragmentMainHowBinding mBinding;

    public MainHowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentMainHowBinding.inflate(getLayoutInflater());
        View v = mBinding.getRoot();

        return v;
    }
}
