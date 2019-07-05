package com.example.anon.astro.tools;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.anon.astro.fragments.FragmentAdditionalData;
import com.example.anon.astro.fragments.FragmentBasicData;
import com.example.anon.astro.fragments.FragmentForecast;
import com.example.anon.astro.fragments.FragmentMoon;
import com.example.anon.astro.fragments.FragmentSun;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int orientation;

    public PagerAdapter(FragmentManager fm, int orientation) {
        super(fm);
        this.orientation = orientation;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position)
        {
            case 0:
                return new FragmentSun();
            case 1:
                return new FragmentMoon();
            case 2:
                return new FragmentBasicData();
            case 3:
                return new FragmentAdditionalData();
            case 4:
                return new FragmentForecast();
            default:
                return null;
        }
    }

    @Override
    public float getPageWidth(int position) {
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            return (0.5f);
        }
        return (1.0f);
    }

    @Override
    public int getCount() {
        return 5;
    }
}
