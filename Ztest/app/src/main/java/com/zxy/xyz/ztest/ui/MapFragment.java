package com.zxy.xyz.ztest.ui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.mapapi.map.MapView;
import com.zxy.xyz.ztest.R;

/**
 * Created by 51c on 2017/4/18.
 */

public class MapFragment extends Fragment {
    private MapView mMapView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.map_fragment,null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mMapView=(MapView)view.findViewById(R.id.baidumap);
    }
}
