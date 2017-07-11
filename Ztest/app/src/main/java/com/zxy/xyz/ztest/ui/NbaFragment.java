package com.zxy.xyz.ztest.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.zxy.xyz.ztest.R;
import com.zxy.xyz.ztest.ui.NBA.HomeFragment;

/**
 * Created by 51c on 2017/7/4.
 */

public class NbaFragment extends Fragment implements BottomNavigationBar.OnTabSelectedListener{
    private FragmentManager fragmentManager;
    private FragmentTransaction ft;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.nbafragment,null);
        fragmentManager=getFragmentManager();
        BadgeItem numberBadgeItem = new BadgeItem()
                .setBorderWidth(4)
                .setBackgroundColorResource(R.color.liji_material_red_500)
                .setText("5");
        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar)view.findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
                );
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.home, "首页").setActiveColorResource(R.color.liji_material_blue_700).setBadgeItem(numberBadgeItem))
                .addItem(new BottomNavigationItem(R.mipmap.match, "比赛").setActiveColorResource(R.color.liji_material_blue_700))
                .addItem(new BottomNavigationItem(R.mipmap.photo, "图片").setActiveColorResource(R.color.liji_material_blue_700))
                .addItem(new BottomNavigationItem(R.mipmap.video, "视频").setActiveColorResource(R.color.liji_material_blue_700))
                .addItem(new BottomNavigationItem(R.mipmap.more, "更多").setActiveColorResource(R.color.liji_material_blue_700))
                .setFirstSelectedPosition(0)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);
        defaultFragment();
        return view;
    }

    public void defaultFragment(){
        ft=fragmentManager.beginTransaction();
        ft.replace(R.id.layFrame,new HomeFragment());
        ft.commit();
    }
    @Override
    public void onTabSelected(int position) {
        ft=fragmentManager.beginTransaction();
        switch (position){
            case 0:
                ft.replace(R.id.layFrame,new HomeFragment());
                break;
        }
        ft.commit();
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
