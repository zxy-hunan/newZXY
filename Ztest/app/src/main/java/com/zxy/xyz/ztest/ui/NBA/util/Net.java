package com.zxy.xyz.ztest.ui.NBA.util;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.zxy.xyz.ztest.ui.NBA.bean.New;
import com.zxy.xyz.ztest.ui.NBA.bean.NumFlag;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
        }
    }
}
