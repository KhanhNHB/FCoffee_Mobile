package com.group3.fcoffee.fragments;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class FragmentTableAdapter extends FragmentStatePagerAdapter {
    private static int NUM_PAGE = 4;

    public FragmentTableAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return TableFragment.newInstance();
            case 1:
                return StatisticsFragment.newInstance();
            case 2:
                return AccountFragment.newInstance();
            case 3:
                return LogoutFragment.newInstance();
            default:
                return TableFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return NUM_PAGE;
    }
}