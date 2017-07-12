package com.zxy.xyz.ztest.ui.NBA.util;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.zxy.xyz.ztest.biz.Pictures;
import com.zxy.xyz.ztest.ui.NBA.bean.New;
import com.zxy.xyz.ztest.ui.NBA.bean.NumFlag;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by 51c on 2017/7/4.
 */

public class Net {

    public Net(Handler handler,String path){
        NetThread netThread=new NetThread(handler,path);
        Thread thread=new Thread(netThread);
        thread.start();
    }
    /**
     * 获得新闻信息
     */
    public static ArrayList<New> getNewsInfo(Handler mHandler,String path) {
        ArrayList<New> al = new ArrayList<>();
        try {
            Document dc = Jsoup.connect(NumFlag.HTML).get();
            Elements els=dc.getElementsByClass("news-list");
            Elements elements = els.get(0).getElementsByClass(path);
            if(path.equals("news-wrap hide")){
                elements=elements.get(0).getElementsByClass(path);
            }

            Log.i("TAG","大小:"+elements.size());
            for (int i = 0; i < elements.size(); i++) {
                New news = new New();
                Element element = elements.get(i);
                //新闻的标题
                Elements elementss = element.getElementsByClass("news-title");
                //图片地址
                Elements eImg = element.select("img[data-original]");
                //新闻链接
                Elements eAhref = element.select("a[href]");
                //新闻日期
                Elements eDate = element.select("b");
                news.setDate(eDate.text());
                for (Element ea : eAhref) {
                    String href = ea.attr("href");
                    news.setHref(href);
                }

                for (int l = 0; l < eImg.size(); l++) {
                    String imgSrc = eImg.get(l).attr("data-original");
                    news.setImg(imgSrc);
                }

                for (int j = 0; j < elementss.size(); j++) {
                    news.setTitle(elementss.get(j).text());
                }

                al.add(news);
            }
            Message msg=new Message();
            msg.obj=al;
            msg.what=0;
            mHandler.sendMessage(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return al;
    }


    public void getPhoto(){
        Document dc = null;
        ArrayList<Pictures> al = new ArrayList<>();
        try {
            dc = Jsoup.connect(NumFlag.PHOTO).get();
            DataSupport.deleteAll(Pictures.class);
            Elements elements=dc.getElementsByClass("news-wrap");
            for (int i = 0; i < elements.size(); i++) {
                Pictures pictures = new Pictures();
                Element element = elements.get(i);
                Elements elementss = element.getElementsByClass("news-title");
                Elements eImg = element.select("img[data-original]");
                Elements eAhref = element.select("a[href]");

                for (Element ea : eAhref) {
                    String href = ea.attr("href");
                    String imgHref=getImgListString(href);
                    pictures.setpHref(imgHref);
                }

                for (int l = 0; l < eImg.size(); l++) {
                    String imgSrc = eImg.get(l).attr("data-original");
                    pictures.setpImg(imgSrc);
                }

                for (int j = 0; j < elementss.size(); j++) {
                    pictures.setpTitle(elementss.get(j).text());
                }
                al.add(pictures);
                DataSupport.saveAll(al);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getImgListString(String str) {
        String newStr = str.replace("nbachina", "shipei");
        String[] s = newStr.split(".htm");
        String s1 = "";
        for (int i = 0; i < s.length; i++) {
            s1 += s[i];
        }
        String s3 = s1.replace("a", "c");
        String s4 = s3.substring(0, 23) + "chinanba/" + s3.substring(23, s3.length());
        int num = s4.lastIndexOf("/");
        String left = s4.substring(0, num);
        String right = s4.substring(num + 1, s4.length());
        String s5 = left + right;
        return s5;
    }
    public static ArrayList<String> getPhotoList(String path) {
        ArrayList<String> list = new ArrayList<>();
        if (path != null) {
            Document dc = null;
            try {
                dc = Jsoup.connect(path).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Elements elements = dc.getElementsByClass("image split");
            for (Element el : elements) {
                Elements elImg = el.select("img[src]");
                String eImg = elImg.attr("src");
                list.add(eImg);
                System.out.println("图片的地址为" + eImg);
            }

        }
        return list;
    }

    class NetThread implements Runnable{
        Handler handler;
        String path;
        NetThread(Handler handler,String path){
            this.handler=handler;
            this.path=path;
        }
        @Override
        public void run() {
             getNewsInfo(handler,path);
             getPhoto();
        }
    }
}
