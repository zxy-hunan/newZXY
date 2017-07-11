package com.zxy.xyz.ztest.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zxy.xyz.ztest.R;

/**
 * Created by 51c on 2017/5/28.
 */
public class SettingFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.settingfrag,null);
        return view;

    }
}
