package com.example.justimagine;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import java.util.Collections;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.justimagine.databinding.FragmentGameRecycledBinding;
import com.google.android.flexbox.AlignContent;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameRecycledFragment extends Fragment {
    FragmentGameRecycledBinding mBinding;
    GameRecycledAdapter mAdapter;
    List<Integer> mCardImages;
    List<Integer> mCardColors;
    Random mRand;

    public GameRecycledFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentGameRecycledBinding.inflate(getLayoutInflater());
        View v = mBinding.getRoot();

        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(getActivity());
        flexboxLayoutManager.setJustifyContent(JustifyContent.CENTER);
        //flexboxLayoutManager.setAlignContent(AlignContent.CENTER);
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
        flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);

        mBinding.cardRecycler.setLayoutManager( flexboxLayoutManager );
        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP | ItemTouchHelper.DOWN |
                        ItemTouchHelper.START | ItemTouchHelper.END, 0) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {

                GameRecycledAdapter adapter = (GameRecycledAdapter) mBinding.cardRecycler.getAdapter();
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();

                adapter.notifyItemMoved(from, to);

                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) { }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
                super.onSelectedChanged(viewHolder, actionState);
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
            }
        };

        mBinding.gameDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawCards();
            }
        });

        final ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(mBinding.cardRecycler);

        mCardImages = new ArrayList<>();
        mCardColors = new ArrayList<>();
        mRand = new Random();


        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < CardPackageManager.get().getSize(); i++) {
            if(CardPackageManager.get().isActive(i)) {
                indexes.add(i);
            }
        }
        Collections.shuffle(indexes);
        indexes = indexes.subList(0, Math.min(6*6, indexes.size()));
        for(int i : indexes){
            mCardImages.addAll(CardPackageManager.get().getImages(i));
            Integer col = CardPackageManager.get().getColorId(i);
            for(int j = 0; j < CardPackageManager.get().getImages(i).size(); j++){
                mCardColors.add(col);
            }
        }

        if(mCardImages.size() < 2*6){
            mBinding.recycledWarning.setVisibility(View.VISIBLE);
            mBinding.recycledWarning.setText(getResources().getString(R.string.game_recycled_notEnoughCards));
        }
        else{
            ArrayList<Integer> selectedCardIndexes =  new ArrayList<>();
            ArrayList<Integer> selectedCardImages =  new ArrayList<>();
            ArrayList<Integer> selectedCardColors =  new ArrayList<>();
            for(int i = 0; i < 6; i++){
                int index = mRand.nextInt(mCardImages.size());
                while(selectedCardIndexes.contains(index)){
                    index = mRand.nextInt(mCardImages.size());
                }
                selectedCardIndexes.add(index);
                selectedCardImages.add(mCardImages.get(index));
                selectedCardColors.add(mCardColors.get(index));
            }

            mAdapter = new GameRecycledAdapter(new OnDragStartListener() {
                @Override
                public void onDragStart(GameRecycledAdapter.RecyclerViewHolder holder) {
                    helper.startDrag(holder);
                }
            },  selectedCardImages, selectedCardColors, this);

            mBinding.cardRecycler.setAdapter(mAdapter);
        }
        return v;
    }

    void drawCards(){
        if(mCardImages.size() < 2*6){
            mBinding.recycledWarning.setVisibility(View.VISIBLE);
            mBinding.recycledWarning.setText(getResources().getString(R.string.game_recycled_notEnoughCards));
        }
        else {
            mBinding.recycledWarning.setVisibility(View.INVISIBLE);
            ArrayList<Integer> selectedCardIndexes =  new ArrayList<>();
            ArrayList<Integer> selectedCardImages =  new ArrayList<>();
            ArrayList<Integer> selectedCardColors =  new ArrayList<>();
            for(int i = 0; i < 6; i++){
                int index = mRand.nextInt(mCardImages.size());
                while(selectedCardIndexes.contains(index)){
                    index = mRand.nextInt(mCardImages.size());
                }
                selectedCardIndexes.add(index);
                selectedCardImages.add(mCardImages.get(index));
                selectedCardColors.add(mCardColors.get(index));
            }
            mAdapter.mImages.clear();
            mAdapter.mColors.clear();
            mAdapter.mImages.addAll(selectedCardImages);
            mAdapter.mColors.addAll(selectedCardColors);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        CardPackageManager.get().saveDB();
    }

}