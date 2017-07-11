package com.zxy.xyz.ztest.ui.NBA;

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
import com.zxy.xyz.ztest.ui.NBA.bean.New;

import java.util.List;
import com.zxy.xyz.ztest.R;



/**
 * Created by 51c on 2017/7/4.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<New> news;
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
    public NewsAdapter(List<New> news, Context context){
        this.news=news;
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

                Intent  intent=new Intent(context,NbaInfoActivity.class);
                intent.putExtra("url",news.get(position).getHref());
                intent.putExtra("img",news.get(position).getImg());
                context.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
      New ne=news.get(position);
        holder.text_title.setText(ne.getTitle());
        holder.text_date.setText(ne.getDate());
        Picasso.with(parent.getContext()).load(ne.getImg()).into(holder.text_img);
    }

    @Override
    public int getItemCount() {
        Log.i("TAG","数据大小:"+news.size());
        return news.size();

    }


}
