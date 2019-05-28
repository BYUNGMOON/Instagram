package com.example.instagram;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabAdapter extends FragmentPagerAdapter {
    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int tabPosition) {

        switch (tabPosition) {
            case 0:
                return new ProfileTab();
            case 1:
                return new UsersTab();
            case 2:
                return new SharePictureTab();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return "Prifle";
            case 1:
                return "Users";
            case 2:
                return "Share Picture";
            default:
                return null;
        }
    }
}
