package com.zxy.xyz.ztest.ui.Main;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zxy.xyz.ztest.R;
import com.zxy.xyz.ztest.adapter.MainPhotoAdapter;
import com.zxy.xyz.ztest.biz.Pictures;

import java.security.KeyStore;
import java.util.ArrayList;


/**
 * Created by ZXY on 2017/7/22.
 */

public class MainPhotoFragment extends Fragment {
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private String path;
    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            ArrayList<Pictures> arrayList = (ArrayList<Pictures>)msg.obj;
            MainPhotoAdapter adapter=new MainPhotoAdapter(arrayList,getActivity());
            recyclerView.setAdapter(adapter);
            SpacesItemDecoration decoration=new SpacesItemDecoration(6);
            recyclerView.addItemDecoration(decoration);
        }
    };
    public MainPhotoFragment(String path){
this.path=path;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.mainphotofragment,null);
        swipeRefreshLayout = (android.support.v4.widget.SwipeRefreshLayout) view.findViewById(R.id.main_srl);
        recyclerView = (RecyclerView) view.findViewById(R.id.main_lv);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        MainNet mainNet=new MainNet(mHandler,path);
        return view;
    }


    class SpacesItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpacesItemDecoration(int space) {
            this.space=space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left=space;
            outRect.right=space;
            outRect.bottom=space;
            if(parent.getChildAdapterPosition(view)==0){
                outRect.top=space;
            }
        }
    }
}
