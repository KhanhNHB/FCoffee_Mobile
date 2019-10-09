package com.example.fcoffee.modules.table.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.fcoffee.modules.statistics.activity.StatisticsFragment;
import com.example.fcoffee.modules.table.activity.TableFragment;
import com.example.fcoffee.modules.account.activities.AccountFragment;
import com.example.fcoffee.modules.account.activities.LogoutFragment;

public class FragmentTableAdapter extends FragmentStatePagerAdapter {
    private static int NUM_PAGE = 3;

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
            default:
                return TableFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return NUM_PAGE;
    }
}
