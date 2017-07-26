package com.zxy.xyz.ztest.ui.Main;

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

import static android.R.attr.path;
import static com.zxy.xyz.ztest.ui.NBA.util.Net.getNewsInfo;


/**
 * Created by 51c on 2017/7/4.
 */

public class MainNet {

    private static Elements els;
    public static boolean isfresh=false;
    private static New news;
    private final Thread thread;

    public MainNet(Handler handler, String path){
        NetThread netThread=new NetThread(handler,path);
        thread=new Thread(netThread);
        thread.start();
    }

    public void netDestory(){
        thread.destroy();
    }


    public void getPhoto(Handler mHandler,String path){
        Document dc = null;
        ArrayList<Pictures> al = new ArrayList<>();
        try {
            dc = Jsoup.connect(path).get();
            Elements elements=dc.select("ul.pic2").select(".vvi").select(".fix");
//            Log.i("TAG","http://www.jj20.com/bz/nxxz/大小"+elements.size());
            for (int k=0;k<elements.size();k++) {
                Elements eImg;
                eImg= elements.get(k).select("img[src]");
                if (k==1){
                    eImg = elements.get(k).select("img[loadsrc]");
                }
                Elements eNum = elements.get(k).select("li");
//                Elements eAhref = elements.get(k).select("a[href]");
                ArrayList<String> arrayList = new ArrayList<>();
//                for (int j = 0; j < eAhref.size() - 1; j++) {
//                    if (eAhref.get(j).attr("href").equals(eAhref.get(j + 1).attr("href"))) {
//                        arrayList.add(eAhref.get(j).attr("href"));
//                    }
//                }
                Log.i("TAG", "eImg" + eImg.size() + "eNum" + eNum.get(0).text());
                for (int i = 0; i < eImg.size(); i++) {
                    Pictures pictures = new Pictures();
                    String imgSrc;
//                    pictures.setpHref(arrayList.get(i));

                    if (k==1){
                        imgSrc = eImg.get(i).attr("loadsrc");
                    }else{
                        imgSrc = eImg.get(i).attr("src");
                    }
                    String numText = eNum.get(i).text();
                    String num = numText.substring(numText.indexOf("(") + 1, numText.indexOf(")"));
                    String num2 = num.replace("张", "");
//                    Log.i("TAG", "num" + num2);
                    pictures.setpDate(num2);
                    pictures.setpImg(imgSrc);
                    al.add(pictures);
                }
            }
            Message message=new Message();
            message.what=0;
            message.obj=al;
            mHandler.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
             getPhoto(handler,path);
        }
    }
}
