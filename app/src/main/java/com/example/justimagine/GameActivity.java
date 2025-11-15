package com.example.justimagine;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.justimagine.databinding.ActivityGameBinding;

public class GameActivity extends AppCompatActivity {
    ActivityGameBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mBinding = ActivityGameBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment =
                fragmentManager.findFragmentById(R.id.game_container);

        if(fragment == null) {
            fragment = new GameRecycledFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.game_container, fragment)
                    .commit();
        }

        CardPackageManager.get().setContext(getApplicationContext());
        CardPackageManager.get().loadDB();

    }
}
