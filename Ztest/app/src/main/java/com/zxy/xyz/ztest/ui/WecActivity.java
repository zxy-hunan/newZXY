package com.zxy.xyz.ztest.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wec);
        initLocation();
        imageWec=(ImageView) findViewById(R.id.image_wec);
        Timer timer=new Timer(true);
        TimerTask tt=new TimerTask() {
            @Override
            public void run() {
//            startActivity(new Intent(WecActivity.this, MainActivity.class));
                finish();
            }
        };
        timer.schedule(tt,100);
    }



    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
      location=bdLocation.getLatitude()+","+bdLocation.getLongitude();
        Toast.makeText(this,location+bdLocation.getCity(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectHotSpotMessage(String s, int i) {

    }


    private void initLocation(){
        mLocClient=new LocationClient(this);
        mLocClient.registerLocationListener(this);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开GPS
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);// 设置定位模式
        option.setCoorType("bd09ll"); // 返回的定位结果是百度经纬度,默认值gcj02
        option.setScanSpan(5000); // 设置发起定位请求的间隔时间为5000ms
        option.setIsNeedAddress(true); // 返回的定位结果包含地址信息
        option.setNeedDeviceDirect(true); // 返回的定位结果包含手机机头的方向

        mLocClient.setLocOption(option);
        mLocClient.start();
        if(mLocClient!=null||mLocClient.isStarted()){
            mLocClient.requestLocation();
        }
    }
}
