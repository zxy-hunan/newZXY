package com.zxy.xyz.ztest.ui.NBA.util;

import android.icu.text.UnicodeSet;
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
import java.security.KeyStore;
import java.util.ArrayList;

import static com.baidu.location.g.a.i;
import static org.litepal.crud.DataSupport.select;


/**
 * Created by 51c on 2017/7/4.
 */

public class Net {

    private static Elements els;
    public static boolean isfresh=false;
    private static New news;
    private final Thread thread;

    public Net(Handler handler, String path){
        NetThread netThread=new NetThread(handler,path);
        thread=new Thread(netThread);
        thread.start();
    }

    public void netDestory(){
        thread.destroy();
    }
    /**
     * 获得新闻信息
     */
    public static ArrayList<New> getNewsInfo(Handler mHandler,String path) {
        ArrayList<New> al = new ArrayList<>();
        try {
            Document dc = Jsoup.connect(NumFlag.HTML).get();
            DataSupport.deleteAll(New.class);
//            Elements els=dc.select("div .news-page").select(".index-news");
                 els=dc.getElementsByClass(path);


//            Log.i("elements","els"+els.html());
            Elements elements =els.get(0).getElementsByClass("news-title");
            Elements elementT =els.get(0).select("img[data-original]");
            Elements elementH =els.get(0).select("a[href]");
            Elements elementB =els.get(0).select("i");
            Log.i("elements","elementB"+elementB.text());
            Log.i("TAG","elements:"+elements.size()+"elementT"+elementT.size()+"elementH"+elementH.size()+"elementB"+elementB.size());
//            for(int i=0;i<elements.size();i++){
//                Elements eImg = els.get(i).select("img[data-original]");
//                Elements eAhref = els.get(i).select("a[href]");
//            }
            for (int l = 0; l < elementT.size(); l++) {
                news = new New();
                String imgSrc = elementT.get(l).attr("data-original");
                news.setImg(imgSrc);
                Log.i("elements","elements"+imgSrc);
                news.setDate(elementB.get(l).text());
                String href = elementH.get(l).attr("href");
                news.setHref(href);
                Log.i("elements","href"+href);
                Element element = elements.get(l);
                Elements elementss = element.select("span");
                for (int j = 0; j < elementss.size(); j++) {
                    news.setTitle(elementss.get(j).text());
                }
                al.add(news);
            }
            DataSupport.saveAll(al);

            Message msg=new Message();
            msg.obj=al;
            if(isfresh){
                msg.what=1;
            }else{
                msg.what=0;
            }

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
            Elements elementss = elements.get(0).getElementsByClass("news-title");
            Elements eImg = elements.get(0).select("img[data-original]");
            Elements eAhref = elements.get(0).select("a[href]");
            Elements elementB =els.get(0).select("i");
            Log.i("TAG","elementss:"+elementss.size()+"eImg"+eImg.size()+"eAhref"+eAhref.size());
            for (int i = 0; i < elementss.size(); i++) {
                Pictures pictures = new Pictures();
                    String href = eAhref.get(i).attr("href");
                    String imgHref=getImgListString(href);
                    pictures.setpHref(imgHref);
                    String imgSrc = eImg.get(i).attr("data-original");
                    pictures.setpImg(imgSrc);
                    pictures.setpTitle(elementss.get(i).text());
                    pictures.setpDate(elementB.get(i).text());
                    al.add(pictures);
            }
            DataSupport.saveAll(al);
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
    public static ArrayList<Pictures> getPhotoList(String path) {
        ArrayList<Pictures> list = new ArrayList<>();
        boolean isNull=false;
        if (path != null) {
            Document dc = null;
            try {
                dc = Jsoup.connect(path).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Elements elements = dc.select("div .image").select(".split");
            Elements elT=dc.select("p.split");
            if(elT.size()==0||elT.size()!=elements.size()){
                isNull= true;
            }
            Log.i("TAG","elT:"+elT.html());
            for (int i=0;i<elements.size();i++) {
                Pictures pictures = new Pictures();
                Elements elImg = elements.get(i).select("img[src]");
                String eImg = elImg.attr("src");
                pictures.setpImg(eImg);
                if(!isNull) {
                    if (elT.get(i).text().equals(" ")) {
                        pictures.setpTitle(" ");
                    } else {
                        pictures.setpTitle(elT.get(i).text());
                    }
                }else{
                    pictures.setpTitle(" ");
                }
                list.add(pictures);
//                Log.i("TAG","elT:"+elT.get(i).text());
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
