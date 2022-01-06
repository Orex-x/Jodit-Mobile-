package com.example.testclientjodit2.activities.ui.main;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.testclientjodit2.fragments.FragmentAccount;
import com.example.testclientjodit2.fragments.FragmentMissions;
import com.example.testclientjodit2.fragments.FragmentSettings;


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public Fragment[] fragments = new Fragment[]{new FragmentMissions(), new FragmentAccount(), new FragmentSettings()};
    private static final String TAG = "myLogs";

    private final Context mContext;
    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }


    @Override
    public int getCount() {
        return 3;
    }
}