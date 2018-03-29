package com.example.happyfridayimagegrid;

import android.content.Context;
import android.content.Intent;
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

    private final Context mContext;
    private final ArrayList<Image> mImages;
    private OnItemClickListener mListener;
    public static final String EXTRA_IMAGE = "com.example.happyfridayimagegrid.MESSAGE";


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

        if(0 != mImages.size()){
        String imagePath = mImages.get(position).getFilePath();

            Glide.with(mContext)
                    .load(imagePath)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.mipmap.ic_launcher_round)
                    .thumbnail(0.5f)
                    .centerCrop()
                    .into(holder.myImageView);

            //Set content description for each image
            holder.myImageView.setContentDescription(mImages.get(position).getImageName());
        }
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView myImageView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            myImageView = itemView.findViewById(R.id.image_view);


            itemView.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View view) {
                    if(mListener!=null){
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            mListener.onItemClicked(position);

                            Image clickedItem = mImages.get(position);

                            Intent intent = new Intent(mContext, ShowImageActivity.class);
                            intent.putExtra(EXTRA_IMAGE, clickedItem.getFilePath());
                            mContext.startActivity(intent);

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
