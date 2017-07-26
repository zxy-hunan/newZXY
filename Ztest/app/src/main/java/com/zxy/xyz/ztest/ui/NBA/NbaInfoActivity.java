package com.zxy.xyz.ztest.ui.NBA;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zxy.xyz.ztest.MainActivity;
import com.zxy.xyz.ztest.R;
import com.zxy.xyz.ztest.ui.NBA.bean.New;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;



/**
 * Created by 51c on 2017/7/10.
 */
public class NbaInfoActivity extends Activity implements View.OnClickListener{
    private String url;
    private Toolbar toolbar;
    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {

        }
    };
    private ArrayList<New> arrayList;
    private LinearLayout llInfo;
    private ImageView imageView3,img_return;
    private String img;
    private String title;
    private TextView text_title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Intent intent=this.getIntent();
        super.onCreate(savedInstanceState);

        setContentView(R.layout.nbainfoactivity);
        llInfo=(LinearLayout)findViewById(R.id.ll_news);
        img_return=(ImageView)findViewById(R.id.img_return);
        imageView3=(ImageView)findViewById(R.id.imageView3);
        text_title=(TextView)findViewById(R.id.text_title);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.toolbar_menu);
        url=intent.getStringExtra("url");
        img=intent.getStringExtra("img");
        title=intent.getStringExtra("title");
        text_title.setText(title);
        img_return.setOnClickListener(this);
        MainActivity.toolbar.setVisibility(View.GONE);
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
                            news.setTitle("    "+e.text());
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
                                    textView.setTextColor(Color.parseColor("#050505"));
                                    textView.setTextSize(17);
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

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_return:
                MainActivity.toolbar.setVisibility(View.VISIBLE);
                this.finish();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            MainActivity.toolbar.setVisibility(View.VISIBLE);
            return true;
        }else {
            return super.onKeyDown(keyCode, event);
        }
    }
}
