package com.zxy.xyz.ztest.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
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
import com.zxy.xyz.ztest.listener.OnMainMoreChangeListener;
import com.zxy.xyz.ztest.ui.Main.MainPhotoInfoActivity;

import java.util.List;


/**
 * Created by 51c on 2017/7/4.
 */

public class MainMoreAdapter extends RecyclerView.Adapter<MainMoreAdapter.ViewHolder> {
    private List<MainPhotoItem> pictures;
    ViewGroup parent;
    Context context;
    Handler mHandler;
    boolean isOpen=false;


    static class ViewHolder extends RecyclerView.ViewHolder{
        View newsView;
        TextView text_img;
        ImageView image_remove;

        public ViewHolder(View itemView) {
            super(itemView);
            newsView=itemView;
            text_img = (TextView) itemView.findViewById(R.id.news_img);
            image_remove = (ImageView) itemView.findViewById(R.id.image_remove);
        }
    }
    public MainMoreAdapter(List<MainPhotoItem> pictures, Context context, Handler mHandler){
        this.pictures=pictures;
        this.context=context;
        this.mHandler=mHandler;
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
        viewHolder.newsView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                int position=viewHolder.getAdapterPosition();
                View mView=parent.getChildAt(position);
                ImageView remove = (ImageView) mView.findViewById(R.id.image_remove);
                if(!isOpen){
                remove.setVisibility(View.VISIBLE);
                    isOpen=true;
                }else{
                    remove.setVisibility(View.GONE);
                    isOpen=false;
                }
                return true;
            }
        });
        viewHolder.image_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=viewHolder.getAdapterPosition();
                MainPhotoItem item=pictures.get(position);
                item.setStatus("0");
                item.updateAll("title = ?",item.getTitle());
                mHandler.sendEmptyMessage(0);
                mHandler.sendEmptyMessage(1);
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
