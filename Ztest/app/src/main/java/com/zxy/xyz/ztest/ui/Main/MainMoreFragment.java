package com.zxy.xyz.ztest.ui.Main;

import android.os.Bundle;
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

import org.litepal.crud.DataSupport;

import java.util.List;


/**
 * Created by ZXY on 2017/7/22.
 */

public class MainMoreFragment extends Fragment {
    private RecyclerView recyclerView,recyclerView1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.mainnewsfragment,null);
        recyclerView = (RecyclerView) view.findViewById(R.id.main_lv);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerView1 = (RecyclerView) view.findViewById(R.id.main_lv_1);
        recyclerView1.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        List<MainPhotoItem> alT= DataSupport.where("status = ?","0").find(MainPhotoItem.class);
        List<MainPhotoItem> al= DataSupport.where("status = ?","1").find(MainPhotoItem.class);

        MainMoreAdapter mainMoreAdapter=new MainMoreAdapter(al,getActivity());
        MainMoreTAdapter mainMoreAdapterT=new MainMoreTAdapter(alT,getActivity());

        recyclerView.setAdapter(mainMoreAdapter);
        recyclerView1.setAdapter(mainMoreAdapterT);
        return view;
    }
}
