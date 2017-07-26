package com.zxy.xyz.ztest.ui;

import android.support.v4.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
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
import com.zxy.xyz.ztest.ui.NBA.MatchFragment;
import com.zxy.xyz.ztest.ui.NBA.MoreFragment;
import com.zxy.xyz.ztest.ui.NBA.PhotoFragment;

/**
 * Created by 51c on 2017/7/4.
 */

public class NbaFragment extends Fragment implements BottomNavigationBar.OnTabSelectedListener{
    private android.support.v4.app.FragmentManager fragmentManager;
    private android.support.v4.app.FragmentTransaction ft;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.nbafragment,null);
        fragmentManager=getActivity().getSupportFragmentManager();
        BadgeItem numberBadgeItem = new BadgeItem()
                .setBorderWidth(4)
                .setBackgroundColorResource(R.color.liji_material_red_500)
                .setText("5");
        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar)view.findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT
                );
        bottomNavigationBar.setBarBackgroundColor(R.color.white);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.home, "首页").setActiveColorResource(R.color.colorPrimary).setBadgeItem(numberBadgeItem))
                .addItem(new BottomNavigationItem(R.mipmap.match, "比赛").setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.mipmap.photo, "图片").setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.mipmap.video, "视频").setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.mipmap.more, "更多").setActiveColorResource(R.color.colorPrimary))
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
            case 1:
                ft.replace(R.id.layFrame,new MatchFragment());
                break;
            case 2:
                ft.replace(R.id.layFrame,new PhotoFragment());
                break;
            case 3:
                break;
            case 4:
                ft.replace(R.id.layFrame,new MoreFragment());
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
