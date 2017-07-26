package com.zxy.xyz.ztest.ui.NBA;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zxy.xyz.ztest.MainActivity;
import com.zxy.xyz.ztest.R;

/**
 * Created by ZXY on 2017/7/18.
 */
public class MoreFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.morefragment,null);
        MainActivity.toolbar.setTitle("更多");
        MainActivity.toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        return  view;
    }
}
