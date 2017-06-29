package com.siemens.diary.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.siemens.diary.fragments.f_tab1;

public class TabsPageFragmentAdapter extends FragmentPagerAdapter {

    private String[] tabs;

    public TabsPageFragmentAdapter(FragmentManager fm) {
        super(fm);
        tabs = new String[] {
                "Tab 1",
                "Tab 2",
                "Tab 3",
                "Tab 4",
                "Tab 5",
                "Tab 6" };
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return f_tab1.getInstance();
            case 1:
                return f_tab1.getInstance();
            case 2:
                return f_tab1.getInstance();
            case 3:
                return f_tab1.getInstance();
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

    @Override
    public int getCount() {
        return tabs.length;
    }
}
