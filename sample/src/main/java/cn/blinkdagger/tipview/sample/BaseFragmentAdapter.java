package cn.blinkdagger.tipview.sample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class BaseFragmentAdapter extends FragmentPagerAdapter {

    private String[] titles;
    private Fragment[] fragments;

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    public BaseFragmentAdapter(FragmentManager fm, Fragment[] fragments, String[] titles) {
        super(fm);
        this.titles = titles;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return titles.length;
    }
}
