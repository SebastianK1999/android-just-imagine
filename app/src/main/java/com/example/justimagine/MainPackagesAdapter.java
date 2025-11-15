package com.example.justimagine;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

public class MainPackagesAdapter extends RecyclerView.Adapter<MainPackagesAdapter.ViewHolder>  {
    MainPackagesFragment mParent;
    public MainPackagesAdapter(MainPackagesFragment parent) {
        mParent = parent;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mParent.getContext()).inflate(R.layout.layout_package, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    @SuppressLint("ResourceAsColor")
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.body.setStrokeColor(mParent.getResources().getColor(CardPackageManager.get().getColorId(position), mParent.getActivity().getTheme()));
        holder.name.setText(mParent.getResources().getString(CardPackageManager.get().getNameId(position)));
        holder.image.setImageDrawable(mParent.getResources().getDrawable(CardPackageManager.get().getCoverId(position), mParent.getActivity().getTheme()));
        holder.toggle.setChecked(CardPackageManager.get().isActive(position));
        holder.toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CardPackageManager.get().setActive(position, isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return CardPackageManager.get().getSize();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public MaterialCardView body;
        public ImageView image;
        public TextView name;
        public Switch toggle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            body = itemView.findViewById(R.id.package_body);
            image = itemView.findViewById(R.id.package_image);
            name = itemView.findViewById(R.id.package_name);
            toggle = itemView.findViewById(R.id.package_toggle);
        }
    }
}
