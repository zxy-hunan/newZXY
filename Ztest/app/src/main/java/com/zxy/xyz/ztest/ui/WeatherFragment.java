package com.zxy.xyz.ztest.ui;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.zxy.xyz.ztest.R;
import com.zxy.xyz.ztest.adapter.TempAdapter;
import com.zxy.xyz.ztest.biz.Weather;
import com.zxy.xyz.ztest.biz.WeatherInfo;
import com.zxy.xyz.ztest.util.Constant;
import com.zxy.xyz.ztest.util.WeatherJsonUtil;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static com.baidu.location.g.j.t;


/**
 * Created by 51c on 2017/4/18.
 */

public class WeatherFragment extends Fragment implements BDLocationListener, View.OnClickListener {
    private LocationClient locationClient;
    private static String APPKEY = "1d17e4da746dc";
    private String city = "深圳";
    private String province = "广东";
    private String CITY = URLEncoder.encode(city, "UTF-8");
    private String PROVINCE = URLEncoder.encode(province, "UTF-8");
    private String WEATHERURI = "http://apicloud.mob.com/v1/weather/query?key=" + APPKEY + "&city=" + CITY + "&province=" + PROVINCE;
    private String location;
    private LinearLayout ll_we;
    private TextView text_city;
    private TextView text_ll_date;
    private TextView text_date;
    private TextView text_airnum;
    private TextView text_air;
    private TextView text_updatetime;
    private TextView text_temp;
    private TextView text_yun;
    private View view;
    private TextView text_sunrise;
    private TextView text_sunset;
    private TextView text_exercis;
    private TextView text_dress;
    private TextView text_cold;
    private TextView text_wind;
    private TextView text_hum;
    private ListView listView;
    private ImageView image_add;
    private ImageView image_location;
    private ImageView image_share;
    private ImageView image_set;
    private ImageView image_air;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private int count=0;
    private int[] imageDayLen={R.mipmap.bg_cloudy_day,R.mipmap.bg_fine_day,R.mipmap.bg_snow,R.mipmap.bg_na,R.mipmap.bg_overcast,R.mipmap.bg_sand_storm};
    private int[] imageNightLen={R.mipmap.bg_cloudy_night,R.mipmap.bg_fine_night,R.mipmap.bg_fog,R.mipmap.bg_haze,R.mipmap.bg_thunder_storm};
    private ImageView image_firebal;
    private int hour;

    public WeatherFragment() throws UnsupportedEncodingException {
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constant.RESPONSE_SUCCESS:
                    ArrayList<Weather> alist = (ArrayList) msg.obj;
                    Weather we = alist.get(0);
                    text_city.setText(we.getCity());
                    text_ll_date.setText(we.getTime());
                    text_date.setText(we.getDate());
                    text_airnum.setText(we.getPollutionIndex());
                    text_air.setText("空气:" + we.getAirCondition());
                    text_updatetime.setText(we.getUpdateTime());
                    text_temp.setText(we.getTemperature());
                    text_yun.setText(we.getWeather());
                    text_sunrise.setText("日出时间:" + we.getSunrise());
                    text_sunset.setText("日落时间:" + we.getSunset());
                    text_exercis.setText("运动指数:" + we.getExerciseIndex());
                    text_dress.setText("穿衣指数:" + we.getDressingIndex());
                    text_cold.setText("感冒指数:" + we.getColdIndex());
                    text_wind.setText("风向:" + we.getWind());
                    text_hum.setText(we.getHumidity());
                    WeatherInfo weatherInfo = we.getWiList().get(0);

                    if(hour>8&&hour<18) {
                        String dayW=weatherInfo.getDayTime();
                        if (dayW.substring(dayW.length() - 1).equals("雨")) {
                            ll_we.setBackgroundResource(imageDayLen[3]);
                        } else {
                            ll_we.setBackgroundResource(imageDayLen[0]);
                        }
                    }else{
                        String night=weatherInfo.getNight();
                        if (night.substring(night.length() - 1).equals("雨")) {
                            ll_we.setBackgroundResource(imageDayLen[3]);
                        } else {
                            ll_we.setBackgroundResource(imageDayLen[4]);
                        }
                    }

                    listView.setAdapter(new TempAdapter(alist, getActivity()));
                    setListViewHeightBasedOnChildren(listView);
                    break;
                case Constant.CHANGE_PHOTO:

//                    ll_we.setBackgroundResource(imageDayLen[count]);
                    if(count<imageDayLen.length-1) {
                        count+=1;
                    }else{
                        count=0;
                    }
//                    mHandler.sendEmptyMessageDelayed(Constant.CHANGE_PHOTO,1000*6);
                    break;
            }
        }
    };

    /**
     * 动态设置ListView的高度
     *
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        if (listView == null) return;
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        x.view().inject(getActivity());

        view = inflater.inflate(R.layout.activity_weather, null);
        initView();
        initLocation();
        preferences = getActivity().getSharedPreferences("location", Context.MODE_PRIVATE);
        editor = preferences.edit();
        String cityName = preferences.getString("city", "");
        if (!cityName.equals("")) {
            String cityM = cityName.substring(0, cityName.indexOf("."));
            String province1 = cityName.substring(cityName.indexOf("."), cityName.length());
            province = province1;
            city = cityM;
        }
        Time t=new Time();
        t.setToNow();
        hour=t.hour;
        return view;
    }

    /**
     * initialize widget
     */
    private void initView() {
        ll_we=(LinearLayout)view.findViewById(R.id.ll_we);
        text_city = (TextView) view.findViewById(R.id.text_city);
        text_ll_date = (TextView) view.findViewById(R.id.text_ll_date);
        text_date = (TextView) view.findViewById(R.id.text_date);
        text_airnum = (TextView) view.findViewById(R.id.text_airnum);
        text_air = (TextView) view.findViewById(R.id.text_air);
        text_updatetime = (TextView) view.findViewById(R.id.text_updatetime);
        text_temp = (TextView) view.findViewById(R.id.text_temp);
        text_yun = (TextView) view.findViewById(R.id.text_yun);

        text_sunrise = (TextView) view.findViewById(R.id.text_sunrise);
        text_sunset = (TextView) view.findViewById(R.id.text_sunset);
        text_exercis = (TextView) view.findViewById(R.id.text_exercis);
        text_dress = (TextView) view.findViewById(R.id.text_dress);
        text_cold = (TextView) view.findViewById(R.id.text_cold);
        text_wind = (TextView) view.findViewById(R.id.text_wind);
        text_hum = (TextView) view.findViewById(R.id.text_hum);

        image_add = (ImageView) view.findViewById(R.id.image_add);
        image_location = (ImageView) view.findViewById(R.id.image_location);
        image_share = (ImageView) view.findViewById(R.id.image_share);
        image_set = (ImageView) view.findViewById(R.id.image_set);
        image_firebal= (ImageView) view.findViewById(R.id.image_firebal);
        image_air= (ImageView) view.findViewById(R.id.image_air);
        listView = (ListView) view.findViewById(R.id.list_view);
        image_add.setOnClickListener(this);
        image_location.setOnClickListener(this);
        image_share.setOnClickListener(this);
        image_set.setOnClickListener(this);
        RotateAnimation animation = new RotateAnimation(0, 20);
        animation.setDuration(10000);//设定转一圈的时间
        animation.setRepeatCount(Animation.INFINITE);//设定无限循环
        animation.setRepeatMode(Animation.REVERSE);
//        Animation translateAnimation=new TranslateAnimation(10,0,0,0);
//        translateAnimation.setDuration(10000);               //设置动画持续时间
//        translateAnimation.setRepeatCount(Animation.INFINITE); //设置重复次数
//        translateAnimation.setRepeatMode(Animation.REVERSE);    //反方向执行
//        image_air.setAnimation(translateAnimation);             //设置动画效果
//        translateAnimation.startNow();                      //启动动画
        image_firebal.startAnimation(animation);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.image_add:
             getActivity().getFragmentManager().beginTransaction().replace(R.id.content_main,new CityPickerFragment()).commit();
                break;
            case R.id.image_location:
                  initLocation();
                break;
            case R.id.image_share:

                break;
            case R.id.image_set:

                break;

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        RequestParams params = new RequestParams(WEATHERURI);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                ArrayList<Weather> alist = WeatherJsonUtil.getWeaList(s);
                Message message = new Message();
                message.obj = alist;
                message.what = Constant.RESPONSE_SUCCESS;
                mHandler.sendMessage(message);
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
        Timer timer=new Timer();
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
               mHandler.sendEmptyMessage(Constant.CHANGE_PHOTO);
            }
        };
        timer.schedule(timerTask,1000*6);
    }


    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        location = bdLocation.getLatitude() + "," + bdLocation.getLongitude();
        editor.putString("city", bdLocation.getCity() + "." + bdLocation.getProvince());
        editor.putString("location", location);
        editor.commit();
        Toast.makeText(getActivity(), bdLocation.getCity() + bdLocation.getProvince(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectHotSpotMessage(String s, int i) {

    }


    private void initLocation() {
        locationClient = new LocationClient(getActivity().getApplicationContext());
        locationClient.registerLocationListener(this);
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备

        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系

        int span = 1000;
        option.setScanSpan(span);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的

        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要

        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps

        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果

        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”

        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到

        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死

        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集

        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要

        locationClient.setLocOption(option);
        locationClient.start();
        locationClient.requestLocation();
    }


}
