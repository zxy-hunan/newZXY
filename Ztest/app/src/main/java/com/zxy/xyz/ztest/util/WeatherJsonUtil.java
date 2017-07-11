package com.zxy.xyz.ztest.util;

import com.zxy.xyz.ztest.biz.Weather;
import com.zxy.xyz.ztest.biz.WeatherInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by 51c on 2017/4/18.
 */

public class WeatherJsonUtil {
public static ArrayList<Weather> getWeaList(String str){
    ArrayList<Weather> alist=new ArrayList<>();
    try {
        JSONObject jsonObject=new JSONObject(str);
        JSONArray jsonArray=jsonObject.getJSONArray("result");
        JSONObject data=jsonArray.getJSONObject(0);

        Weather we=new Weather();
        String airCondition=data.getString("airCondition");
        we.setAirCondition(airCondition);
        String city=data.getString("city");
        we.setCity(city);
        String coldIndex=data.getString("coldIndex");
        we.setColdIndex(coldIndex);
        String updateTime=data.getString("updateTime");
        we.setUpdateTime(updateTime);
        String date=data.getString("date");
        we.setDate(date);
        String distrct=data.getString("distrct");
        we.setDistrct(distrct);
        String dressingIndex=data.getString("dressingIndex");
        we.setDressingIndex(dressingIndex);
        String exerciseIndex=data.getString("exerciseIndex");
        we.setExerciseIndex(exerciseIndex);
        String humidity=data.getString("humidity");
        we.setHumidity(humidity);
        String province=data.getString("province");
        we.setProvince(province);
        String sunset=data.getString("sunset");
        we.setSunset(sunset);
        String sunrise=data.getString("sunrise");
        we.setSunrise(sunrise);
        String temperature=data.getString("temperature");
        we.setTemperature(temperature);
        String time=data.getString("time");
        we.setTime(time);
        String washIndex=data.getString("washIndex");
        we.setWashIndex(washIndex);
        String weather=data.getString("weather");
        we.setWeather(weather);
        String week=data.getString("week");
        we.setWeek(week);
        String wind=data.getString("wind");
        we.setWind(wind);
        String pollutionIndex=data.getString("pollutionIndex");
        we.setPollutionIndex(pollutionIndex);
        JSONArray future=data.getJSONArray("future");
        ArrayList<WeatherInfo> wiList=new ArrayList<>();
        for (int i=0;i<future.length();i++){
            WeatherInfo wi=new WeatherInfo();

                JSONObject ob = future.getJSONObject(i);

                String date1 = ob.getString("date");
                if(ob.has("dayTime")==true) {
                String dayTime = ob.getString("dayTime");
                    wi.setDayTime(dayTime);
               }else{
                    wi.setDayTime("已经过去。");
                }
                String night = ob.getString("night");
                String temperature1 = ob.getString("temperature");
                String week1 = ob.getString("week");
                String wind1 = ob.getString("wind");
                wi.setDate(date1);

                wi.setNight(night);
                wi.setTemperature(temperature1);
                wi.setWeek(week1);
                wi.setWind(wind1);

            wiList.add(wi);
        }
        we.setWiList(wiList);

        alist.add(we);


    } catch (JSONException e) {
        e.printStackTrace();
    }


    return alist;
}


    /**
     *
     * @param str
     * @return
     */
    public static WeatherInfo getWeaInfo(String str){
        WeatherInfo wi=new WeatherInfo();
        try {
            JSONObject jsonObject=new JSONObject(str);
            JSONArray jsonArray=jsonObject.getJSONArray("result");
            JSONObject data=jsonArray.getJSONObject(0);
            JSONArray future=data.getJSONArray("future");

                JSONObject ob = future.getJSONObject(0);
            String temperature1;
            String wind1;
            if (ob.has("temperature")) {
                 temperature1 = ob.getString("temperature");
            }else{
                 temperature1 = "失败";
            }
            if(ob.has("wind")) {
                 wind1 = ob.getString("wind");
            }else{
                wind1="失败";
            }
                wi.setTemperature(temperature1);
                wi.setWind(wind1);


        } catch (JSONException e) {
            e.printStackTrace();
        }


        return wi;
    }
}
