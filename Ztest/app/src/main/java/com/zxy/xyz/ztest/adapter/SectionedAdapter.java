package com.zxy.xyz.ztest.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.zxy.xyz.ztest.R;
import com.zxy.xyz.ztest.biz.MatchInfo;

import java.util.ArrayList;
import java.util.List;

import de.halfbit.pinnedsection.PinnedSectionListView;

/**
 * Created by 51c on 2017/7/12.
 */

public class SectionedAdapter extends BaseAdapter implements PinnedSectionListView.PinnedSectionListAdapter {
    Context con;
    ArrayList<MatchInfo> matchInfoList;

    public SectionedAdapter(Context con, ArrayList<MatchInfo> matchInfoList) {
        this.con = con;
        this.matchInfoList = matchInfoList;
    }

    @Override
    public boolean isItemViewTypePinned(int viewType) {
        return false;
    }

    @Override
    public int getCount() {
        return matchInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return matchInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int itemViewType = matchInfoList.get(position).getType();
        if (itemViewType == MatchInfo.ITEM_VIEW_TYPE_HEADER) {
            if (convertView == null) {
                convertView = LayoutInflater.from(con).inflate(R.layout.item_head, null);
            }
        } else {
            if (convertView == null) {
                convertView = LayoutInflater.from(con).inflate(R.layout.item_body, null);
            }
        }
        return convertView;
    }
}
