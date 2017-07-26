package com.zxy.xyz.ztest.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zxy.xyz.ztest.R;
import com.zxy.xyz.ztest.biz.MainPhotoItem;
import com.zxy.xyz.ztest.biz.Pictures;
import com.zxy.xyz.ztest.ui.Main.MainPhotoInfoActivity;

import java.util.List;


/**
 * Created by 51c on 2017/7/4.
 */

public class MainMoreAdapter extends RecyclerView.Adapter<MainMoreAdapter.ViewHolder> {
    private List<MainPhotoItem> pictures;
    ViewGroup parent;
    Context context;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View newsView;
        TextView text_img;


        public ViewHolder(View itemView) {
            super(itemView);
            newsView=itemView;
            text_img = (TextView) itemView.findViewById(R.id.news_img);
        }
    }
    public MainMoreAdapter(List<MainPhotoItem> pictures, Context context){
        this.pictures=pictures;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        this.parent=parent;
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_morephoto,parent,false);
        final ViewHolder viewHolder=new ViewHolder(view);
        viewHolder.newsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=viewHolder.getAdapterPosition();


            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MainPhotoItem ne=pictures.get(position);
        holder.text_img.setText(ne.getTitle());
    }

    @Override
    public int getItemCount() {
        return pictures.size();

    }


}
