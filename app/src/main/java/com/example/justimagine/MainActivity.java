package com.example.justimagine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.justimagine.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment =
                fragmentManager.findFragmentById(R.id.main_container);

        if(fragment == null) {
            fragment = new MainMenuFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.main_container, fragment)
                    .commit();
        }

        CardPackageManager.get().setContext(getApplicationContext());
        CardPackageManager.get().loadDB();

    }
}