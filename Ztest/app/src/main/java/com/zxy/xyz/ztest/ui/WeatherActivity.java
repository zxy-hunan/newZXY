package com.zxy.xyz.ztest.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.zxy.xyz.ztest.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


/**
 * Created by 51c on 2017/4/18.
 */
public class WeatherActivity extends Activity implements View.OnClickListener{
    private static String APPKEY="1d17e4da746dc";
    private String city="通州";
    private String province="北京";
    private String CITY=URLEncoder.encode(city,"UTF-8");
    private String PROVINCE=URLEncoder.encode(province,"UTF-8");
    private String WEATHERURI="http://apicloud.mob.com/v1/weather/query?key="+APPKEY+"&city="+CITY+"&province="+PROVINCE;
    public WeatherActivity() throws UnsupportedEncodingException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        setContentView(R.layout.activity_weather);
        initView();
    }

    /**
     * initialize widget
     */
    private void initView() {

    }


    @Override
    protected void onResume() {
        super.onResume();
        RequestParams params=new RequestParams(WEATHERURI);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Toast.makeText(WeatherActivity.this,s,Toast.LENGTH_SHORT).show();
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
    }

    @Override
    public void onClick(View v) {

    }
}
