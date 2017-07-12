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
import com.zxy.xyz.ztest.ui.NBA.NbaInfoActivity;
import com.zxy.xyz.ztest.ui.NBA.bean.New;
import com.zxy.xyz.ztest.ui.PhotoInfoActivity;

import java.util.List;


/**
 * Created by 51c on 2017/7/4.
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {
    private List<Pictures> pictures;
    ViewGroup parent;
    Context context;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View newsView;
       TextView text_title,text_content,text_date;
       ImageView text_img;


        public ViewHolder(View itemView) {
            super(itemView);
            newsView=itemView;
            text_title = (TextView) itemView.findViewById(R.id.news_title);
           text_content = (TextView) itemView.findViewById(R.id.news_content);
            text_date = (TextView) itemView.findViewById(R.id.news_date);
            text_img = (ImageView) itemView.findViewById(R.id.news_img);
        }
    }
    public PhotoAdapter(List<Pictures> pictures, Context context){
        this.pictures=pictures;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        this.parent=parent;
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new,parent,false);
        final ViewHolder viewHolder=new ViewHolder(view);
        viewHolder.newsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=viewHolder.getAdapterPosition();

                Intent  intent=new Intent(context,PhotoInfoActivity.class);
                intent.putExtra("url",pictures.get(position).getpHref());
                intent.putExtra("title",pictures.get(position).getpTitle());
                context.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pictures ne=pictures.get(position);
        holder.text_title.setText(ne.getpTitle());
        Picasso.with(parent.getContext()).load(ne.getpImg()).into(holder.text_img);
    }

    @Override
    public int getItemCount() {
        Log.i("TAG","数据大小:"+pictures.size());
        return pictures.size();

    }


}
