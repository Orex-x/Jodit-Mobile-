package com.example.testclientjodit2.activities.ui.main;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.testclientjodit2.R;
import com.example.testclientjodit2.activities.MainActivity2;
import com.example.testclientjodit2.fragments.FragmentAccount;
import com.example.testclientjodit2.fragments.FragmentGroup;
import com.example.testclientjodit2.fragments.FragmentSettings;


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    Fragment[] fragments = new Fragment[]{new FragmentGroup(), new FragmentAccount(), new FragmentSettings()};
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
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        MainActivity2.im1.setImageResource(R.drawable.ic_list_gray);
        MainActivity2.im2.setImageResource(R.drawable.ic_user_gray);
        MainActivity2.im3.setImageResource(R.drawable.ic_settings_gray);
        switch (position){
            case 0:  MainActivity2.im1.setImageResource(R.drawable.ic_list_extra_gray); break;
            case 1:  MainActivity2.im2.setImageResource(R.drawable.ic_user_extra_gray); break;
            case 2:  MainActivity2.im3.setImageResource(R.drawable.ic_settings_extra_gray); break;
        }
    }


    @Override
    public int getCount() {
        return 3;
    }
}