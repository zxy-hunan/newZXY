package com.zxy.xyz.ztest;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.zxy.xyz.ztest.ui.MapFragment;
import com.zxy.xyz.ztest.ui.NbaFragment;
import com.zxy.xyz.ztest.ui.WeatherFragment;

import org.xutils.x;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private static final String APIKEY="e103e89a494819ff264702cb45eec528";
    private MapView mMapView;
    private BaiduMap baiduMap;
    private Toolbar toolbar;
    FragmentManager fragmentManager;
    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        x.view().inject(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        fragmentManager=getFragmentManager();

        mMapView=(MapView)findViewById(R.id.bmapview);
        baiduMap=mMapView.getMap();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent=new Intent();
        ft=fragmentManager.beginTransaction();

        if (id == R.id.nav_camera) {
            toolbar.setVisibility(View.VISIBLE);
            ft.replace(R.id.content_main,new MapFragment());
        } else if (id == R.id.nav_gallery) {
            try {
                toolbar.setVisibility(View.GONE);
                ft.replace(R.id.content_main,new WeatherFragment());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        } else if (id == R.id.nav_slideshow) {
            toolbar.setVisibility(View.VISIBLE);
            ft.replace(R.id.content_main,new NbaFragment());
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        ft.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void findWeather(){


    }
boolean isBack=false;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction()==MotionEvent.ACTION_DOWN){
            if(!isBack) {
                Toast.makeText(this, "再按一次退出！", Toast.LENGTH_SHORT).show();
                isBack=true;
            }else{
                finish();
            }
        }
        return super.onTouchEvent(event);
    }
}
