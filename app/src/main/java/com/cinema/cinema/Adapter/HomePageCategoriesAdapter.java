package com.cinema.cinema.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.cinema.cinema.Fragment.CatalogiesFragment;
import com.cinema.cinema.Model.HomePage_Featured;
import com.cinema.cinema.Model.HomePage_categories;
import com.cinema.cinema.R;

import java.util.ArrayList;


public class HomePageCategoriesAdapter extends RecyclerView.Adapter<HomePageCategoriesAdapter.ViewHolder> {
    private ArrayList<HomePage_categories> listdata;
    private HomePageCategoriesAdapter.OnItemClickListener mOnItemClickListener;

    // RecyclerView recyclerView;
    public HomePageCategoriesAdapter(ArrayList<HomePage_categories> listdata, OnItemClickListener mOnItemClickListener) {
        this.listdata = listdata;
        this.mOnItemClickListener =  mOnItemClickListener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.home_page_categories_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem, mOnItemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final String cata = listdata.get(position).getCagTitle();
        holder.title.setText(listdata.get(position).getCagTitle());
        holder.iv_image.setBackgroundResource(listdata.get(position).getImg());

    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title;
        public ImageView iv_image;
        HomePageCategoriesAdapter.OnItemClickListener onItemClickListener;

        public ViewHolder(View itemView, HomePageCategoriesAdapter.OnItemClickListener onItemClickListener) {
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.tv_cataogle);
            this.iv_image = (ImageView) itemView.findViewById(R.id.imageView4);

            //listener
            this.onItemClickListener = onItemClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(getAdapterPosition());

        }
    }


    public interface OnItemClickListener {
        void onItemClick(int position);

    }

}
