package com.zxy.xyz.ztest.ui.NBA;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zxy.xyz.ztest.MainActivity;
import com.zxy.xyz.ztest.R;
import com.zxy.xyz.ztest.adapter.PhotoAdapter;
import com.zxy.xyz.ztest.biz.Pictures;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;

/**
 * Created by 51c on 2017/7/12.
 */
public class PhotoFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.nbahome,null);
        swipeRefreshLayout = (android.support.v4.widget.SwipeRefreshLayout) view.findViewById(R.id.main_srl);
        recyclerView = (RecyclerView) view.findViewById(R.id.main_lv);
        MainActivity.toolbar.setTitle("图片");
        MainActivity.toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        initView();
        ArrayList<Pictures> pAl=(ArrayList)DataSupport.findAll(Pictures.class);
        PhotoAdapter adapter=new PhotoAdapter(pAl,getActivity());
        recyclerView.setAdapter(adapter);
        return view;
    }
    private void initView() {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setDistanceToTriggerSync(100);
        swipeRefreshLayout.setProgressViewEndTarget(false, 200);
    }

    @Override
    public void onRefresh() {

    }
}
