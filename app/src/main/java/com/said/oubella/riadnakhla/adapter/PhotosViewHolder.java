package com.said.oubella.riadnakhla.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.said.oubella.riadnakhla.R;

final class PhotosViewHolder extends RecyclerView.ViewHolder {

    private final AppCompatImageView imageView;

    PhotosViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView);
    }

    void bind(int resource) {
        imageView.setImageResource(resource);
    }
}
