package com.zxy.xyz.ztest.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.zxy.xyz.ztest.MainActivity;
import com.zxy.xyz.ztest.R;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by 51c on 2017/4/18.
 */

public class WecActivity extends Activity implements  BDLocationListener{

    private  LocationClient mLocClient;
    private String location;
    private ImageView imageWec;
    private LocationClient locationClient;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private TextView text_wec;
    private int num=6;
    public Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==0){
                text_wec.setText(""+num);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wec);
        initView();
        preferences = this.getSharedPreferences("location", Context.MODE_PRIVATE);
        editor = preferences.edit();
        initLocation();
        imageWec = (ImageView) findViewById(R.id.image_wec);
        Timer timer = new Timer(true);
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(WecActivity.this, MainActivity.class));
                finish();
            }
        };
        timer.schedule(tt, 5000);
        Timer timer1 = new Timer(true);
        TimerTask th = new TimerTask() {

            @Override
            public void run() {

                    num = num-1;
                    Message msg = new Message();
                    msg.what = 0;
                    mHandler.sendMessage(msg);

            }
        };
    timer1.schedule(th,0,1000);
    }

    private void initView() {
        text_wec=(TextView)findViewById(R.id.text_wec);
    }


    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        location = bdLocation.getLatitude() + "," + bdLocation.getLongitude();
//        if(cityName==null) {

        String city=bdLocation.getCity();
        String province=bdLocation.getProvince();
        if(city==null||province==null){
            editor.putString("city", "深圳市" + "." + "广东省");
        }else{
            editor.putString("city", bdLocation.getCity() + "." + bdLocation.getProvince());
        }
        editor.putString("location", location);
        editor.commit();
//        }
    }

    @Override
    public void onConnectHotSpotMessage(String s, int i) {

    }


    private void initLocation() {
        locationClient = new LocationClient(getApplicationContext());
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
