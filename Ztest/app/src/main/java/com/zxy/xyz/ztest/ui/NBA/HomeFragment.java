package com.zxy.xyz.ztest.ui.NBA;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zxy.xyz.ztest.R;
import com.zxy.xyz.ztest.ui.NBA.bean.New;
import com.zxy.xyz.ztest.ui.NBA.util.Net;

import java.util.ArrayList;


/**
 * Created by 51c on 2017/7/4.
 */

public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
 Handler mHandler=new Handler(){
     @Override
     public void handleMessage(Message msg) {
         switch (msg.what){
             case 0:
                 ArrayList<New> newList=(ArrayList<New>) msg.obj;
                 NewsAdapter nAdapter=new NewsAdapter(newList,getActivity());
                 recyclerView.setAdapter(nAdapter);
                 break;
         }
     }
 };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.nbahome,null);
        swipeRefreshLayout = (android.support.v4.widget.SwipeRefreshLayout)view.findViewById(R.id.main_srl);
        recyclerView = (RecyclerView)view.findViewById(R.id.main_lv);

        initView();
        return view;
    }

    private void initView() {
//        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
       new Net(mHandler,"news-wrap");

/*        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.RED);
        swipeRefreshLayout.setBackgroundColor(Color.YELLOW);
        swipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);*/
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setDistanceToTriggerSync(100);
        swipeRefreshLayout.setProgressViewEndTarget(false, 200);
    }

    @Override
    public void onRefresh() {
        new Net(mHandler,"news-wrap hide");
    }
}

