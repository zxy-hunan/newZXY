package com.zxy.xyz.ztest.ui.NBA;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zxy.xyz.ztest.MainActivity;
import com.zxy.xyz.ztest.R;
import com.zxy.xyz.ztest.ui.NBA.bean.New;
import com.zxy.xyz.ztest.ui.NBA.util.Net;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;



/**
 * Created by 51c on 2017/7/4.
 */

public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    NewsAdapter nAdapter;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    ArrayList<New> newList = (ArrayList<New>) msg.obj;
                    nAdapter = new NewsAdapter(newList, getActivity());
                    recyclerView.setAdapter(nAdapter);
                    break;
                case  1:
                    Log.i("TAG","mHandler1");
                    newList = (ArrayList<New>) msg.obj;
                    nAdapter = new NewsAdapter(newList, getActivity());
                    recyclerView.setAdapter(nAdapter);
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nbahome, null);
        swipeRefreshLayout = (android.support.v4.widget.SwipeRefreshLayout) view.findViewById(R.id.main_srl);
        recyclerView = (RecyclerView) view.findViewById(R.id.main_lv);
        MainActivity.toolbar.setTitle("首页");
        MainActivity.toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        initView();
        return view;
    }

    private void initView() {
//        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        ArrayList<New> newList=(ArrayList)DataSupport.findAll(New.class);
        if (newList.size()==0){
            Net net1=new Net(mHandler, "news-wrap");
        }else{
            Message msg=new Message();
            msg.obj=newList;
            msg.what=0;
            mHandler.sendMessage(msg);
        }


/*        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.RED);
        swipeRefreshLayout.setBackgroundColor(Color.YELLOW);
        swipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);*/
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        Net.isfresh=true;
        new Net(mHandler, "news-wrap");
    }
}

