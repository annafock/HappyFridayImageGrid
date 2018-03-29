package com.example.happyfridayimagegrid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ImageRecyclerViewAdapter extends RecyclerView.Adapter<ImageRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Image> mImages;
    private OnItemClickListener mListener;


    public interface OnItemClickListener {
        void onItemClicked(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){ mListener = listener; }

    public ImageRecyclerViewAdapter(Context mContext, ArrayList<Image> mImages) {
        this.mContext = mContext;
        this.mImages = mImages;
    }

    // inflates the cell layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_item, null);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.myImageView.setImageDrawable(null);

        String imagePath = mImages.get(position).getFilePath();

        Glide.with(mContext)
                .load(imagePath)
                .placeholder(R.mipmap.ic_launcher)
                .thumbnail(0.5f)
                .into(holder.myImageView);
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView myImageView;

        ViewHolder(View itemView) {
            super(itemView);
            myImageView = (ImageView) itemView.findViewById(R.id.image_view);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mListener!=null){
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            mListener.onItemClicked(position);
                            Image clickedItem = mImages.get(position);

                            Toast.makeText(mContext, "Image clicked", Toast.LENGTH_SHORT).show();

                        }

                    }
                }
            });


        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return mImages.get(id).getImageName();
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }
}
