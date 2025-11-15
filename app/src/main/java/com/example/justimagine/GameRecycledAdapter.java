package com.example.justimagine;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class GameRecycledAdapter extends RecyclerView.Adapter<GameRecycledAdapter.RecyclerViewHolder> {

    ArrayList<Integer> mImages;
    ArrayList<Integer> mColors;
    OnDragStartListener mListener;
    GameRecycledFragment mParent;

    public GameRecycledAdapter(OnDragStartListener listener, ArrayList<Integer> images, ArrayList<Integer> colors, GameRecycledFragment parent) {
        mImages = images;
        mColors = colors;
        mListener = listener;
        mParent = parent;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_card, parent, false);
        return new RecyclerViewHolder(view);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder holder, int position) {
        //mImages.get(position);
        holder.body.setStrokeColor(mParent.getResources().getColor(mColors.get(position), mParent.getActivity().getTheme()));
        holder.cardImage.setImageDrawable(ResourcesCompat.getDrawable( mParent.getResources(),  mImages.get(position), null ));
        holder.cardImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getActionMasked() == MotionEvent.ACTION_DOWN) {
                    mListener.onDragStart(holder);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {

        MaterialCardView body;
        ImageView cardImage;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            body = itemView.findViewById(R.id.card_body);
            cardImage = itemView.findViewById(R.id.card_image);
        }
    }
}
