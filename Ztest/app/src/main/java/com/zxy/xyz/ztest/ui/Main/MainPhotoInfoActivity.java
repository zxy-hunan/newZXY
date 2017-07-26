package com.zxy.xyz.ztest.ui.Main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zxy.xyz.ztest.MainActivity;
import com.zxy.xyz.ztest.R;
import com.zxy.xyz.ztest.ui.PhotoInfoActivity;
import com.zxy.xyz.ztest.util.DialogUtil;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

import static com.baidu.location.g.j.al;
import static com.zxy.xyz.ztest.R.id.imageView;


/**
 * Created by ZXY on 2017/7/22.
 */
public class MainPhotoInfoActivity extends Activity implements View.OnClickListener,View.OnLongClickListener{
    private ViewPager mViewPager;
    private ImageView img_return;
    public int position;
    private ArrayList<View> viewArrayList = new ArrayList<>();
    private ArrayList<String> viewUrl = new ArrayList<>();
    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            position=msg.what;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.mainphotoinfo);
        String title = intent.getStringExtra("title");
        final int num=Integer.parseInt(title);
        String url=intent.getStringExtra("url");
        final String imgUrl= url.substring(0,url.indexOf("-")+1);
        Log.i("TAG","url"+url+"   imgUrl"+imgUrl);
        img_return = (ImageView) findViewById(R.id.img_return);
        mViewPager = (ViewPager) findViewById(R.id.vp_FindFragment_pager);
        img_return.setOnClickListener(this);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (int i=1;i<=num;i++){
                    //-lp.jpg
                    String url=imgUrl+i+".jpg";
                    ImageView imageView=new ImageView(MainPhotoInfoActivity.this);
                    Picasso.with(MainPhotoInfoActivity.this).load(url).into(imageView);
                    viewArrayList.add(imageView);
                    viewUrl.add(url);
                }
                mViewPager.setAdapter(new MyPagerAdapter(MainPhotoInfoActivity.this,mHandler));
            }
        });
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
        DialogUtil.dialog(this,"保存图片到手机？","是","否",viewArrayList.get(position),viewUrl.get(position));
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
