package com.example.miodragmilosevic.nativetest.filterimage;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.miodragmilosevic.nativetest.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.miodragmilosevic.nativetest.filters.ImageProcessor.GRAYSCALE;
import static com.example.miodragmilosevic.nativetest.filters.ImageProcessor.NEGATIVE;
import static com.example.miodragmilosevic.nativetest.filters.ImageProcessor.NOISE;
import static com.example.miodragmilosevic.nativetest.filters.ImageProcessor.OLD_TV;
import static com.example.miodragmilosevic.nativetest.filters.ImageProcessor.ONE_COLOR;
import static com.example.miodragmilosevic.nativetest.filters.ImageProcessor.ORIGINAL;
import static com.example.miodragmilosevic.nativetest.filters.ImageProcessor.PIXELATE;
import static com.example.miodragmilosevic.nativetest.filters.ImageProcessor.RELIEF;
import static com.example.miodragmilosevic.nativetest.filters.ImageProcessor.SNOW;

/**
 * Created by miodrag.milosevic on 12/11/2017.
 */

public class FilterAdapter extends
        RecyclerView.Adapter
                <FilterAdapter.ListItemViewHolder> {
    private OnRecyclerItemClickListener mOnItemClickListener;
    private List<FilterItem> items;
    private Random mRandom;

    public FilterAdapter() {
        mRandom = new Random();
        items = new ArrayList<>();
        items.add(new FilterItem("Original", ORIGINAL));
        items.add(new FilterItem("Grayscale", GRAYSCALE));
        items.add(new FilterItem("Negative", NEGATIVE));
        items.add(new FilterItem("Noise", NOISE));
        items.add(new FilterItem("Relief", RELIEF));
        items.add(new FilterItem("Old TV", OLD_TV));
        items.add(new FilterItem("Pixelate", PIXELATE));
        items.add(new FilterItem("One Color", ONE_COLOR));
        items.add(new FilterItem("Snow", SNOW));
    }

    @Override
    public ListItemViewHolder onCreateViewHolder(
            ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.item_filter,
                        viewGroup,
                        false);
        return new ListItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(
            ListItemViewHolder viewHolder, int position) {
        FilterItem filterItem = items.get(position);
        viewHolder.filterName.setText(filterItem.getName());
        int color = Color.argb(255, mRandom.nextInt(256), mRandom.nextInt(256), mRandom.nextInt(256));
        viewHolder.filterImage.setBackgroundColor(color);
        viewHolder.filterLayout.setOnClickListener(view -> {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(items.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public final static class ListItemViewHolder
            extends RecyclerView.ViewHolder {
        public TextView filterName;
        public ImageView filterImage;
        public LinearLayout filterLayout;

        public ListItemViewHolder(View itemView) {
            super(itemView);
            filterLayout = itemView.findViewById(R.id.filter_layout);
            filterName = itemView.findViewById(R.id.filter_name);
            filterImage = itemView.findViewById(R.id.filter_image);
        }
    }

    public void setFilterItems(List<FilterItem> filterItems) {
        items.clear();
        if (filterItems != null) {
            items.addAll(filterItems);
        }
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnRecyclerItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public interface OnRecyclerItemClickListener {
        void onItemClick(FilterItem filterItem);
    }
}

