package com.zxy.xyz.ztest.biz;

import org.litepal.crud.DataSupport;

/**
 * Created by ZXY on 2017/7/24.
 */

public class MainPhotoItem extends DataSupport {
    private String title;
    private String path;
    private String status;

    public MainPhotoItem(String title,String path,String status){
        setTitle(title);
        setPath(path);
        setStatus(status);
    }
    public MainPhotoItem(){

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
