package com.zxy.xyz.ztest.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.LoginFilter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zxy.xyz.ztest.R;
import com.zxy.xyz.ztest.ui.NBA.util.Net;

import java.util.ArrayList;


/**
 * Created by 51c on 2017/7/12.
 */
public class PhotoInfoActivity extends Activity{
    private ViewPager mViewPager;
    private TextView text_title;
    private ArrayList<String> al = null;
    private ArrayList<View> viewArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        setContentView(R.layout.activity_photo);
        String title=intent.getStringExtra("title");
        final String url=intent.getStringExtra("url");
        mViewPager = (ViewPager)findViewById(R.id.vp_FindFragment_pager);
        text_title = (TextView)findViewById(R.id.text_title);
        text_title.setText(title);
        Log.i("TAG",""+url);
        new Thread(new Runnable() {
            @Override
            public void run() {
                al=Net.getPhotoList(url);
                Log.i("TAG","al.size()"+al.size());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < al.size(); i++) {
                            ImageView imageView = new ImageView(PhotoInfoActivity.this);
                            Picasso.with(PhotoInfoActivity.this).load(al.get(i)).into(imageView);
                            viewArrayList.add(imageView);
                        }
                        mViewPager.setAdapter(new MyPagerAdapter());
                    }
                });
            }
        }).start();
    }

    class MyPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return viewArrayList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewArrayList.get(position));
            return viewArrayList.get(position);
        }
    }
}
