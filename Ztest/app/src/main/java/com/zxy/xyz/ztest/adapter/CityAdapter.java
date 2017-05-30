package com.zxy.xyz.ztest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zxy.xyz.ztest.R;
import com.zxy.xyz.ztest.biz.City;

import java.util.ArrayList;

/**
 * Created by 51c on 2017/4/25.
 */

public class CityAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<City> al;
    public CityAdapter(Context context,ArrayList<City> al){
        this.context=context;
        this.al=al;
    }
    @Override
    public int getCount() {
        return al.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View view=null;
        ViewHandler viewHandler;
        if(view==null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_city, null);
            viewHandler = new ViewHandler();
            viewHandler.text_loc = (TextView) view.findViewById(R.id.text_loc);
            viewHandler.text_weather = (TextView) view.findViewById(R.id.text_daytime);
            viewHandler.text_default = (TextView) view.findViewById(R.id.text_night);
            viewHandler.image_weather=(ImageView)view.findViewById(R.id.image_weather);
            view.setTag(viewHandler);
        }else{
            view=convertView;
            viewHandler=(ViewHandler) view.getTag();

        }
        String pc=al.get(position).getCity();
        String cityLsat=pc.substring(pc.length()-1);
        if(cityLsat.equals("区")){
            viewHandler.text_loc.setText(al.get(position).getProvince()+al.get(position).getCity());
        }else{
            viewHandler.text_loc.setText(al.get(position).getProvince()+al.get(position).getCity());
        }
//
//        viewHandler.text_weather.setText("21/23:");
//        viewHandler.text_default.setText("夜晚:"+al.get(position).getNight());
//        viewHandler.image_weather.setBackground("夜晚:"+al.get(position).getNight());
        return view;
    }

    class ViewHandler{
        private TextView text_loc;
        private TextView text_weather;
        private TextView text_default;
        private ImageView image_weather;
    }
}
