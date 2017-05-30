package com.zxy.xyz.ztest.biz;

import java.util.ArrayList;

/**
 * Created by 51c on 2017/4/18.
 */

public class Weather {
    private String airCondition;
    private String city;
    private String coldIndex;
    private String updateTime;
    private String date;
    private String distrct;
    private String dressingIndex;
    private String exerciseIndex;
    private String humidity;
    private String province;
    private String sunset;
    private String sunrise;
    private String temperature;
    private String time;
    private String washIndex;
    private String weather;
    private String week;
    private String wind;
    private String pollutionIndex;
    private ArrayList<WeatherInfo> wiList;

    public ArrayList<WeatherInfo> getWiList() {
        return wiList;
    }

    public void setWiList(ArrayList<WeatherInfo> wiList) {
        this.wiList = wiList;
    }




    public String getPollutionIndex() {
        return pollutionIndex;
    }

    public void setPollutionIndex(String pollutionIndex) {
        this.pollutionIndex = pollutionIndex;
    }

    public String getAirCondition() {
        return airCondition;
    }

    public void setAirCondition(String airCondition) {
        this.airCondition = airCondition;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getColdIndex() {
        return coldIndex;
    }

    public void setColdIndex(String coldIndex) {
        this.coldIndex = coldIndex;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDistrct() {
        return distrct;
    }

    public void setDistrct(String distrct) {
        this.distrct = distrct;
    }

    public String getDressingIndex() {
        return dressingIndex;
    }

    public void setDressingIndex(String dressingIndex) {
        this.dressingIndex = dressingIndex;
    }

    public String getExerciseIndex() {
        return exerciseIndex;
    }

    public void setExerciseIndex(String exerciseIndex) {
        this.exerciseIndex = exerciseIndex;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWashIndex() {
        return washIndex;
    }

    public void setWashIndex(String washIndex) {
        this.washIndex = washIndex;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }
}


//        "future": [
//        {
//        "date": "2015-09-09",
//        "dayTime": "阵雨",
//        "night": "阴",
//        "temperature": "24°C/18°C",
//        "week": "星期三",
//        "wind": "无持续风向小于3级"
//        },