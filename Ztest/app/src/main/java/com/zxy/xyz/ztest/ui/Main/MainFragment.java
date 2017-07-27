package com.zxy.xyz.ztest.ui.Main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zxy.xyz.ztest.MainActivity;
import com.zxy.xyz.ztest.R;
import com.zxy.xyz.ztest.biz.MainPhotoItem;
import com.zxy.xyz.ztest.listener.OnMainMoreChangeListener;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZXY on 2017/7/22.
 */
public class MainFragment extends Fragment implements View.OnClickListener, OnMainMoreChangeListener {
    private ViewPager mViewPager;
    TabLayout mTabLayout;
    ImageView img_return;
    private List<String> mTitle = new ArrayList<String>();
    private List<Fragment> mDatas = new ArrayList<Fragment>();
    private MainMoreFragment mainMoreFragment;
    private MyAPager pager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mainfragment, null);
        LinearLayout ll_group = (LinearLayout) view.findViewById(R.id.ll_group);
        mTabLayout = (TabLayout) view.findViewById(R.id.tab_FindFragment_title);
        mViewPager = (ViewPager) view.findViewById(R.id.vp_FindFragment_pager);
        img_return = (ImageView) view.findViewById(R.id.img_return);

        initDatas();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loding();
    }

    public void loding(){
        pager = new MyAPager(getFragmentManager());
        mViewPager.setAdapter(pager);
        MainActivity.mTabLayout.setVisibility(View.VISIBLE);
        MainActivity.mTabLayout.setupWithViewPager(mViewPager);
        MainActivity.mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }
    @Override
    public void onClick(View view) {

    }

    public void initDatas() {
        mTitle.clear();
        mDatas.clear();
        List<MainPhotoItem> al = DataSupport.where("status = ?", "1").find(MainPhotoItem.class);
        for (int i = 0; i < al.size(); i++) {
            mTitle.add(al.get(i).getTitle());
            mDatas.add(new MainPhotoFragment(al.get(i).getPath()));
        }
        mainMoreFragment = new MainMoreFragment();
        mainMoreFragment.setOnMainMoreChangeListener(this);
        mTitle.add("更多");
        mDatas.add(mainMoreFragment);
//        mTitle.add("美女");
//        mTitle.add("人物");
//        mTitle.add("影视");
//        mTitle.add("体育");
//        mTitle.add("动物");
//        mTitle.add("卡通");
//        mTitle.add("美食");
//        mTitle.add("风景");
//
//
//        mDatas.add(new MainPhotoFragment("http://www.jj20.com/bz/nxxz/"));
//        mDatas.add(new MainPhotoFragment("http://www.jj20.com/bz/rwtx/"));
//        mDatas.add(new MainPhotoFragment("http://www.jj20.com/bz/ysbz/"));
//        mDatas.add(new MainPhotoFragment("http://www.jj20.com/bz/tyyd/"));
//        mDatas.add(new MainPhotoFragment("http://www.jj20.com/bz/dwxz/"));
//        mDatas.add(new MainPhotoFragment("http://www.jj20.com/bz/ktmh/"));
//        mDatas.add(new MainPhotoFragment("http://www.jj20.com/bz/mwjy/"));
//        mDatas.add(new MainPhotoFragment("http://www.jj20.com/bz/zrfg/"));
    }

    @Override
    public void changed() {
        initDatas();
        Log.i("debug","mTitle:"+mTitle.size()+"mData："+mDatas.size());
//        pager=new MyAPager(getFragmentManager());
//        mViewPager.setAdapter(pager);
//        MainActivity.mTabLayout.setVisibility(View.VISIBLE);
//
//        MainActivity.mTabLayout.setupWithViewPager(mViewPager);
//        MainActivity.mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        pager.notifyDataSetChanged();
        mViewPager.setAdapter(pager);
    }

    class MyAPager extends FragmentStatePagerAdapter {

        public MyAPager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mDatas.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitle.get(position);
        }

        @Override
        public int getCount() {
            return mTitle.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }
    }
}
