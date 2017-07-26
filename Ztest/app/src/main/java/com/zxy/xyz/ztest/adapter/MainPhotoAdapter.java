package com.zxy.xyz.ztest.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zxy.xyz.ztest.R;
import com.zxy.xyz.ztest.biz.Pictures;
import com.zxy.xyz.ztest.ui.Main.MainPhotoInfoActivity;
import com.zxy.xyz.ztest.ui.PhotoInfoActivity;

import java.util.List;


/**
 * Created by 51c on 2017/7/4.
 */

public class MainPhotoAdapter extends RecyclerView.Adapter<MainPhotoAdapter.ViewHolder> {
    private List<Pictures> pictures;
    ViewGroup parent;
    Context context;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View newsView;
       ImageView text_img;


        public ViewHolder(View itemView) {
            super(itemView);
            newsView=itemView;
            text_img = (ImageView) itemView.findViewById(R.id.news_img);
        }
    }
    public MainPhotoAdapter(List<Pictures> pictures, Context context){
        this.pictures=pictures;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        this.parent=parent;
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mainphoto,parent,false);
        final ViewHolder viewHolder=new ViewHolder(view);
        viewHolder.newsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=viewHolder.getAdapterPosition();

                Intent  intent=new Intent(context,MainPhotoInfoActivity.class);
                intent.putExtra("url",pictures.get(position).getpImg());
                intent.putExtra("title",pictures.get(position).getpDate());
                context.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pictures ne=pictures.get(position);
        Picasso.with(parent.getContext()).load(ne.getpImg()).into(holder.text_img);
    }

    @Override
    public int getItemCount() {
        return pictures.size();

    }


}
