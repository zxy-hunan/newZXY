package com.zxy.xyz.ztest.ui;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.lljjcoder.citypickerview.widget.CityPicker;
import com.zxy.xyz.ztest.R;
import com.zxy.xyz.ztest.adapter.CityAdapter;
import com.zxy.xyz.ztest.biz.City;
import com.zxy.xyz.ztest.database.SqliteDBUtil;

import java.util.ArrayList;

/**
 * Created by 51c on 2017/4/21.
 */

public class CityPickerFragment extends Fragment implements View.OnClickListener{

    private View view;
    private GridView grid_view;
    private ImageView image_setting,image_addcity,image_return;
    private SqliteDBUtil sqliteDBUtil;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.citypickerfragment,null);
        initView();
        sqliteDBUtil=new SqliteDBUtil(getActivity());
        return view;
    }

    private void initView() {

        grid_view=(GridView)view.findViewById(R.id.grid_view);
        image_setting = (ImageView) view.findViewById(R.id.image_setting);
        image_addcity = (ImageView) view.findViewById(R.id.image_addcity);
        image_return = (ImageView) view.findViewById(R.id.image_return);
        image_setting.setOnClickListener(this);
        image_addcity.setOnClickListener(this);
        image_return.setOnClickListener(this);
    }


    @Override
    public void onResume() {
        super.onResume();
        ArrayList<City> arrayList = sqliteDBUtil.getCityList();
        if(arrayList.size()!=0) {
            CityAdapter cityAdapter = new CityAdapter(getActivity(), arrayList);
            grid_view.setAdapter(cityAdapter);
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.grid_view:

                break;
            case  R.id.image_setting:

                break;
            case  R.id.image_addcity:
                CityPicker cityPicker = new CityPicker.Builder(getActivity())
                        .textSize(20)
                        .title("地址选择")
                        .backgroundPop(0xa0000000)
                        .titleBackgroundColor("#234Dfa")
                        .titleTextColor("#000000")
                        .backgroundPop(0xa0000000)
                        .confirTextColor("#000000")
                        .cancelTextColor("#000000")
                        .province("广东省")
                        .city("深圳市")
                        .district("宝安区")
                        .textColor(Color.parseColor("#000000"))
                        .provinceCyclic(true)
                        .cityCyclic(false)
                        .districtCyclic(false)
                        .visibleItemsCount(7)
                        .itemPadding(10)
                        .onlyShowProvinceAndCity(false)
                        .build();
                cityPicker.show();

                //监听方法，获取选择结果
                cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
                    @Override
                    public void onSelected(String... citySelected) {
                        //省份
                        String province = citySelected[0];
                        //城市
                        String city = citySelected[1];
                        //区县（如果设定了两级联动，那么该项返回空）
                        String district = citySelected[2];
                        //邮编
                        String code = citySelected[3];
                        int result=sqliteDBUtil.insertoDB(province,city,district,code);
                        Toast.makeText(getActivity(),""+province+city+district+code,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel() {
                    }
                });
                break;
            case  R.id.image_return:
                try {
                    getActivity().getFragmentManager().beginTransaction().replace(R.id.content_main,new WeatherFragment()).commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

        }
    }
}
