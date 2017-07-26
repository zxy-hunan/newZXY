package com.zxy.xyz.ztest.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.LoginFilter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.zxy.xyz.ztest.MainActivity;
import com.zxy.xyz.ztest.R;
import com.zxy.xyz.ztest.biz.Pictures;
import com.zxy.xyz.ztest.ui.NBA.util.Net;

import java.util.ArrayList;

import static com.baidu.location.g.j.al;
import static com.baidu.location.g.j.v;
import static com.zxy.xyz.ztest.R.id.imageView;
import static com.zxy.xyz.ztest.R.id.textView;


/**
 * Created by 51c on 2017/7/12.
 */
public class PhotoInfoActivity extends Activity implements View.OnClickListener, View.OnLongClickListener {
    private ViewPager mViewPager;
    private TextView text_title, text_con;
    private ImageView img_return;
    private ArrayList<Pictures> al = null;
    private ArrayList<View> viewArrayList = new ArrayList<>();
    public int position;
    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            position = msg.what;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_photo);
        String title = intent.getStringExtra("title");
        img_return = (ImageView) findViewById(R.id.img_return);
        final String url = intent.getStringExtra("url");
        mViewPager = (ViewPager) findViewById(R.id.vp_FindFragment_pager);
        text_title = (TextView) findViewById(R.id.text_title);
        text_con = (TextView) findViewById(R.id.text_con);
        text_title.setText(title);
        img_return.setOnClickListener(this);
        Log.i("TAG", "" + url);
        new Thread(new Runnable() {
            @Override
            public void run() {
                al = Net.getPhotoList(url);
                Log.i("TAG", "al.size()" + al.size());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < al.size(); i++) {
                            LinearLayout linearLayout = new LinearLayout(PhotoInfoActivity.this);
                            linearLayout.setOrientation(LinearLayout.VERTICAL);
                            ImageView imageView = new ImageView(PhotoInfoActivity.this);
                            Picasso.with(PhotoInfoActivity.this).load("http:" + al.get(i).getpImg()).into(imageView);
                            TextView textView = new TextView(PhotoInfoActivity.this);
                            textView.setText((i+1)+"/"+al.size()+"  "+al.get(i).getpTitle());
                            textView.setTextColor(Color.parseColor("#050505"));
                            linearLayout.addView(imageView);
                            linearLayout.addView(textView);
//                            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)textView.getLayoutParams();
//                            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
//                            textView.setLayoutParams(params);
                            viewArrayList.add(linearLayout);
                        }
                        mViewPager.setAdapter(new MyPagerAdapter(PhotoInfoActivity.this, mHandler));
                    }
                });
            }
        }).start();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.img_return) {
            MainActivity.toolbar.setVisibility(View.VISIBLE);
            this.finish();
        }
    }

    @Override
    public boolean onLongClick(View view) {
        Toast.makeText(this, "点击了" + position, Toast.LENGTH_LONG).show();
        return true;
    }

    class MyPagerAdapter extends PagerAdapter {
        View.OnLongClickListener listener;
        Handler mHandler;

        public MyPagerAdapter(View.OnLongClickListener listener, Handler mHandler) {
            this.listener = listener;
            this.mHandler = mHandler;
        }

        @Override
        public int getCount() {
            return viewArrayList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position,
                                Object object) {
            container.removeView(viewArrayList.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            viewArrayList.get(position).setOnLongClickListener(listener);
            container.addView(viewArrayList.get(position));
            mHandler.sendEmptyMessage(position);
            return viewArrayList.get(position);
        }
    }
}