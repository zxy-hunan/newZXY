package com.zxy.xyz.ztest.ui.NBA.bean;

import android.graphics.Bitmap;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * 新闻
 * Created by ZXY on 2017/1/20.
 */

public class New extends DataSupport implements Serializable{
    private String title;
    private String content;
    private String img;
    private String date;
    private String href;
    private Bitmap bmp;

    public Bitmap getBmp() {
        return bmp;
    }

    public void setBmp(Bitmap bmp) {
        this.bmp = bmp;
    }



    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        if(content==""){
            this.content="你好";
        }
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
