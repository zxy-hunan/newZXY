package com.zxy.xyz.ztest.ui.NBA;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.zxy.xyz.ztest.R;
import com.zxy.xyz.ztest.ui.NBA.bean.New;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by 51c on 2017/7/10.
 */
public class NbaInfoActivity extends Activity{
    private String url;
    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {

        }
    };
    private ArrayList<New> arrayList;
    private LinearLayout llInfo;
    private ImageView imageView3;
    private String img;
    private String title;
    private TextView text_title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Intent intent=this.getIntent();
        super.onCreate(savedInstanceState);

        setContentView(R.layout.nbainfoactivity);
        llInfo=(LinearLayout)findViewById(R.id.ll_news);
        imageView3=(ImageView)findViewById(R.id.imageView3);
        text_title=(TextView)findViewById(R.id.text_title);
        url=intent.getStringExtra("url");
        img=intent.getStringExtra("img");
        title=intent.getStringExtra("title");
        text_title.setText(title);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    Document dc = Jsoup.connect(url).get();
                    final Elements eImg=dc.select("img[src]");
                    arrayList = new ArrayList<New>();
                    Elements elements =dc.select("p[style]");
                    if(elements.attr("style").equals("text-indent: 2em;")||elements.attr("style").equals("TEXT-INDENT: 2em")) {
                        for (Element e : elements) {
                            New news = new New();
                            news.setTitle(e.text());
                            arrayList.add(news);
                        }
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(arrayList!=null){
                                for(int i=0;i<arrayList.size();i++){
                                    TextView textView=new TextView(NbaInfoActivity.this);
                                    textView.setText(arrayList.get(i).getTitle());
                                    llInfo.addView(textView);
                                }

                            }
                            Picasso.with(NbaInfoActivity.this).load(img).into(imageView3);
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    @Override
    protected void onResume() {
        super.onResume();
       /* RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Toast.makeText(NbaInfoActivity.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });*/
    }
}
