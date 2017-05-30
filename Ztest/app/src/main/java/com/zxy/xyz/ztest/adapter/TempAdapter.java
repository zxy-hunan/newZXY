package com.zxy.xyz.ztest.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zxy.xyz.ztest.R;
import com.zxy.xyz.ztest.biz.Weather;
import com.zxy.xyz.ztest.biz.WeatherInfo;

import java.util.ArrayList;


/**
 * Created by 51c on 2017/4/19.
 */

public class TempAdapter extends BaseAdapter {
    ArrayList<Weather> arrayList;
    ArrayList<WeatherInfo> wiList;
    Activity activity;
    public TempAdapter(ArrayList<Weather> arrayList, Activity activity){
        this.arrayList=arrayList;
        this.activity=activity;
        wiList=arrayList.get(0).getWiList();
    }
    @Override
    public int getCount() {
        return wiList.size();
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_list, null);
            viewHandler = new ViewHandler();
            viewHandler.text_week = (TextView) view.findViewById(R.id.text_week);
            viewHandler.text_daytime = (TextView) view.findViewById(R.id.text_daytime);
            viewHandler.text_night = (TextView) view.findViewById(R.id.text_night);
            viewHandler.text_temp = (TextView) view.findViewById(R.id.text_temp);
            viewHandler.text_wind = (TextView) view.findViewById(R.id.text_wind);
            viewHandler.text_wdate = (TextView) view.findViewById(R.id.text_wdate);
            view.setTag(viewHandler);
        }else{
            view=convertView;
            viewHandler=(ViewHandler) view.getTag();

        }
        viewHandler.text_week.setText(wiList.get(position).getWeek());
        viewHandler.text_daytime.setText("白天:"+wiList.get(position).getDayTime());
        viewHandler.text_night.setText("夜晚:"+wiList.get(position).getNight());
        viewHandler.text_temp.setText(wiList.get(position).getTemperature());
        viewHandler.text_wind.setText(wiList.get(position).getWind());
        viewHandler.text_wdate.setText(wiList.get(position).getDate());

        return view;
    }

     class ViewHandler{
        private TextView text_week;
        private TextView text_daytime;
        private TextView text_night;
        private TextView text_temp;
        private TextView text_wind;
         private TextView text_wdate;
    }
}
