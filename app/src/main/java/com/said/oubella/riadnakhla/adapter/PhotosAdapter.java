package com.said.oubella.riadnakhla.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.said.oubella.riadnakhla.R;
import com.said.oubella.riadnakhla.data.DataSource;

public final class PhotosAdapter extends RecyclerView.Adapter<PhotosViewHolder> {

    @NonNull
    @Override
    public PhotosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PhotosViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.picture_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull PhotosViewHolder holder, int position) {
        holder.bind(DataSource.IMAGES_IDS[position]);
    }

    @Override
    public int getItemCount() {
        return DataSource.IMAGES_IDS.length;
    }
}
