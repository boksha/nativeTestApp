package com.example.miodragmilosevic.nativetest.imagegallery;

/**
 * Created by miodrag.milosevic on 12/6/2017.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import android.support.v7.widget.RecyclerView;

import com.example.miodragmilosevic.nativetest.R;
import com.example.miodragmilosevic.nativetest.util.ImageLoader;

import java.util.List;

/**
 * Created by miodrag.milosevic on 11/30/2017.
 */

public class ImageGridAdapter extends RecyclerView.Adapter<ImageGridAdapter.ViewHolder> {

    private final Context mContext;
    private ItemClickListener mClickListener;
    private List<ImageModel> mImages = new ArrayList<>();

    public void setData(List<ImageModel> images) {
        mImages.clear();
        mImages.addAll(images);
        notifyDataSetChanged();
    }
    public void clearList() {
        mImages.clear();
    }

    public void addData(List<ImageModel> images) {
        mImages.addAll(images);
        notifyDataSetChanged();
    }

    // data is passed into the constructor
    public ImageGridAdapter(Context context) {
        mContext = context.getApplicationContext();
    }

    // inflates the cell layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the views
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImageModel image = mImages.get(position);
        ImageLoader.loadImageFromPathIntoView(mContext, image.getUrl(), holder.iconView);

        holder.imageName.setText(image.getName());
//        if( position %20 == 0){
//            holder.imageName.setTextColor(mContext.getResources().getColor(R.color.colorYellow));
//        } else {
//            holder.imageName.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
//
//        }
        holder.linearLayout.setOnClickListener(view -> {
            if (mClickListener != null) {
                mClickListener.onItemClick(mImages.get(position));
            }
        });
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mImages.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconView;
        TextView imageName;
        LinearLayout linearLayout;

        ViewHolder(View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.grid_item_layout);
            iconView = itemView.findViewById(R.id.grid_item_image);
            imageName = itemView.findViewById(R.id.grid_item_label);
        }
    }

    // convenience method for getting data at click position
    ImageModel getItem(int id) {
        return mImages.get(id);
    }

    // allows clicks events to be caught
    public void setOnItemClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(ImageModel image);
    }
}

