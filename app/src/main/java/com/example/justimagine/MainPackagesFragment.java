package com.example.justimagine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.justimagine.databinding.FragmentMainPackagesBinding;

public class MainPackagesFragment extends Fragment {
    FragmentMainPackagesBinding mBinding;
    MainPackagesAdapter mAdapter;

    public MainPackagesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentMainPackagesBinding.inflate(getLayoutInflater());
        View v = mBinding.getRoot();

        mBinding.packagesRecycler.setLayoutManager( new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false) );
        mAdapter = new MainPackagesAdapter(this);
        mBinding.packagesRecycler.setAdapter(mAdapter);

        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        CardPackageManager.get().saveDB();
    }
}
