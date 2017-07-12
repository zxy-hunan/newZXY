package com.zxy.xyz.ztest.ui.NBA;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zxy.xyz.ztest.R;

/**
 * Created by 51c on 2017/7/12.
 */
public class MatchInfoFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.matchinfofrg,null);
        return view;
    }
}
