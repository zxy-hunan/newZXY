package com.zxy.xyz.ztest.ui.NBA;

import android.support.v4.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zxy.xyz.ztest.MainActivity;
import com.zxy.xyz.ztest.R;
import com.zxy.xyz.ztest.adapter.SectionedAdapter;
import com.zxy.xyz.ztest.biz.MatchInfo;

import java.util.ArrayList;
import java.util.List;

import de.halfbit.pinnedsection.PinnedSectionListView;

/**
 * Created by 51c on 2017/7/12.
 */
public class MatchFragment extends Fragment {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<String> mTitle = new ArrayList<String>();
    private List<View> mDatas = new ArrayList<View>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.matchfragment,null);
        MainActivity.toolbar.setTitle("比赛");
        MainActivity.toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        mTabLayout = (TabLayout) view.findViewById(R.id.tab_FindFragment_title);
        mViewPager = (ViewPager) view.findViewById(R.id.vp_FindFragment_pager);
        initDatas();
        MyPagerAdapter mAdapter = new MyPagerAdapter();
        mTabLayout.setTabsFromPagerAdapter(mAdapter);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        return view;
    }

    class MyPagerAdapter extends PagerAdapter {
        private FragmentManager fragmentManager;
        private FragmentTransaction ft;

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitle.get(position);
        }
        @Override
        public int getCount() {
            return mDatas.size();
        }
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            container.addView(mDatas.get(position));

            return mDatas.get(position);
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((View) object);
        }
    }
    public void initDatas(){
        mTitle.add("赛程");
        mTitle.add("我的关注");
       View matchInfoFragment= LayoutInflater.from(getActivity()).inflate(R.layout.matchinfofrg,null);

        mDatas.add(matchInfoFragment);
        mDatas.add(matchInfoFragment);
        PinnedSectionListView listview_scrol = (PinnedSectionListView)matchInfoFragment.findViewById(R.id.listview_scrol);
//        ArrayList<MatchInfo> al= initList();
//        SectionedAdapter newadapter=new SectionedAdapter(getActivity(),al);
//        listview_scrol.setAdapter(newadapter);
    }

    private ArrayList<MatchInfo> initList() {
        ArrayList<MatchInfo> al=new ArrayList<>();
        for(int i=0;i<50;i++){
            MatchInfo mInfo=new MatchInfo();

                mInfo.setType(MatchInfo.ITEM_VIEW_TYPE_BODY);
            al.add(mInfo);
            for(int j=0;j<i;j++){
                MatchInfo mInfo1=new MatchInfo();
                mInfo1.setType(MatchInfo.ITEM_VIEW_TYPE_HEADER);
                al.add(mInfo1);
            }



        }
        return al;
    }
}
