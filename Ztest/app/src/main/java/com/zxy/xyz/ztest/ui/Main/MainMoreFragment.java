package com.zxy.xyz.ztest.ui.Main;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zxy.xyz.ztest.R;
import com.zxy.xyz.ztest.adapter.MainMoreAdapter;
import com.zxy.xyz.ztest.adapter.MainMoreTAdapter;
import com.zxy.xyz.ztest.biz.MainPhotoItem;
import com.zxy.xyz.ztest.listener.OnMainMoreChangeListener;

import org.litepal.crud.DataSupport;

import java.util.List;


/**
 * Created by ZXY on 2017/7/22.
 */

public class MainMoreFragment extends Fragment {
    private RecyclerView recyclerView,recyclerView1;
    private List<MainPhotoItem> al;
    private List<MainPhotoItem> alT;
    OnMainMoreChangeListener onMainMoreChangeListener;
    public  Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int code=msg.what;
            switch (code) {
                case 0:
                alT = DataSupport.where("status = ?", "0").find(MainPhotoItem.class);
                al = DataSupport.where("status = ?", "1").find(MainPhotoItem.class);
                MainMoreAdapter mainMoreAdapter = new MainMoreAdapter(al, getActivity(), mHandler);
                MainMoreTAdapter mainMoreAdapterT = new MainMoreTAdapter(alT, getActivity(), mHandler);
                recyclerView.setAdapter(mainMoreAdapter);
                recyclerView1.setAdapter(mainMoreAdapterT);

                    break;
                case 1:
                    onMainMoreChangeListener.changed();
                    break;
            }
        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.mainnewsfragment,null);
        recyclerView = (RecyclerView) view.findViewById(R.id.main_lv);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerView1 = (RecyclerView) view.findViewById(R.id.main_lv_1);
        recyclerView1.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        mHandler.sendEmptyMessage(0);
        return view;
    }

    public void setOnMainMoreChangeListener(OnMainMoreChangeListener onMainMoreChangeListener){
        this.onMainMoreChangeListener=onMainMoreChangeListener;
    }

}
