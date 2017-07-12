package com.zxy.xyz.ztest.app;

import org.litepal.LitePal;
import org.litepal.LitePalApplication;
import org.xutils.x;


/**
 * Created by 51c on 2017/4/17.
 */

public class AppData extends LitePalApplication {

    public AppData(){

    }

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        LitePal.initialize(this);
    }
}
