package com.zxy.xyz.ztest.app;

import android.app.Application;

import org.xutils.x;

/**
 * Created by 51c on 2017/4/17.
 */

public class AppData extends Application {

    public AppData(){

    }

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);

    }
}
