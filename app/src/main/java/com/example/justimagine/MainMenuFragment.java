package com.example.justimagine;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.justimagine.databinding.FragmentMainMenuBinding;

public class MainMenuFragment extends Fragment {
    FragmentMainMenuBinding mBinding;

    public MainMenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentMainMenuBinding.inflate(getLayoutInflater());
        View v = mBinding.getRoot();


        mBinding.menuBtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getBaseContext(), GameActivity.class);
                //intent.putExtra("EXTRA_SESSION_ID", sessionId);
                startActivity(intent);
            }
        });
        mBinding.menuBtnHow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) getContext();
                MainHowFragment fragment = new MainHowFragment();
                Bundle bundle = new Bundle();
                fragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).addToBackStack(null).commit();
            }
        });
        mBinding.menuBtnPackages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) getContext();
                MainPackagesFragment fragment = new MainPackagesFragment();
                Bundle bundle = new Bundle();
                fragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).addToBackStack(null).commit();
            }
        });
//        mBinding.menuBtnSettings.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AppCompatActivity activity = (AppCompatActivity) getContext();
//                MainSettingsFragment fragment = new MainSettingsFragment();
//                Bundle bundle = new Bundle();
//                fragment.setArguments(bundle);
//                activity.getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).addToBackStack(null).commit();
//            }
//        });

        return v;
    }
}
