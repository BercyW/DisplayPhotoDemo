package com.example.c052735.simplephotoview;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.c052735.simplephotoview.remote.model.Photo;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {
    List<Photo> photosList;

    public PhotoAdapter(List<Photo> photosList) {
        this.photosList = photosList;
    }
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_photos,viewGroup,false);
        final ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAdapterPosition();
                Photo photo = photosList.get(position);
                Intent intent = new Intent(view.getContext(),PhotoDetails.class);
                intent.putExtra("photoUrl",photo.getUrl());
                view.getContext().startActivity(intent);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PhotoAdapter.ViewHolder viewHolder, int i) {
        Photo photo = photosList.get(i);
        Glide.with(viewHolder.imageView.getContext()).load(photo.getThumbnailUrl()).into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return photosList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View photoView;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.photoView = itemView;
            imageView = itemView.findViewById(R.id.iv_image);

        }
    }

    public void clear() {
        photosList.clear();
        notifyDataSetChanged();
    }


    public void addAll(List<Photo> photos) {
        photosList.addAll(photos);
        notifyDataSetChanged();
    }
}
