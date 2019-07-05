package com.example.anon.astro;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.anon.astro.Adapters.PagerAdapter;
import com.example.anon.astro.Fragments.DialogGeolocation;
import com.example.anon.astro.Fragments.DialogRefreshTime;
import com.example.anon.astro.Fragments.FragmentMoon;
import com.example.anon.astro.Fragments.FragmentSun;

public class MainActivity extends AppCompatActivity{

    private PagerAdapter mPagerAdapter;
    private ViewPager mViewPager;
    private boolean isTablet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isTablet = getResources().getBoolean(R.bool.isTablet);

        //toolbar
        initToolbar();

        //orientation handle
        handleDeviceOrientation();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void openGeolocationSetting(){
        DialogGeolocation dialog = new DialogGeolocation();
        dialog.show(getSupportFragmentManager(), "Geolocation dialog");
    }

    private void openRefreshTimeSetting(){
        DialogRefreshTime dialog = new DialogRefreshTime();
        dialog.show(getSupportFragmentManager(), "Resfresh Time dialog");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.setting_geolocation:
                openGeolocationSetting();
                return true;
            case R.id.setting_refresh_time:
                openRefreshTimeSetting();
                return true;
            default:
                return false;
        }
    }


    private void handleDeviceOrientation() {
        int orientation = getResources().getConfiguration().orientation;

         if (/*orientation == Configuration.ORIENTATION_PORTRAIT && */!isTablet){

             mViewPager = (ViewPager)findViewById(R.id.pager);
             mPagerAdapter = new PagerAdapter(getSupportFragmentManager(), orientation);
             mViewPager.setAdapter(mPagerAdapter);

        } else {
             FragmentManager manager = getSupportFragmentManager();
             FragmentSun fragmentSun = new FragmentSun();
             FragmentMoon fragmentMoon = new FragmentMoon();
             manager.beginTransaction().replace(R.id.layout_sun, fragmentSun).commit();
             manager.beginTransaction().replace(R.id.layout_moon, fragmentMoon).commit();
         }

    }

}
