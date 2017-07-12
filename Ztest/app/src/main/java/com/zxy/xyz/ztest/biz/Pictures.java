package com.zxy.xyz.ztest.biz;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by ZXY on 2017/2/9.
 */

public class Pictures extends DataSupport implements Serializable{
    private String pTitle;
    private String pDate;
    private String pImg;
    private String pHref;

    public String getpHref() {
        return pHref;
    }

    public void setpHref(String pHref) {
        this.pHref = pHref;
    }

    public String getpTitle() {
        return pTitle;
    }

    public void setpTitle(String pTitle) {
        this.pTitle = pTitle;
    }

    public String getpDate() {
        return pDate;
    }

    public void setpDate(String pDate) {
        this.pDate = pDate;
    }

    public String getpImg() {
        return pImg;
    }

    public void setpImg(String pImg) {
        this.pImg = pImg;
    }
}
