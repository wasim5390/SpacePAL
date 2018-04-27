package com.roadhourse.spacepal.ui.dashboard;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.roadhourse.spacepal.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sidhu on 4/26/2018.
 */

public class DashboardFragmentPagerAdapter extends FragmentStatePagerAdapter{
    private List<Fragment> fragments = new ArrayList<>();
    private Context mContext;
    User user;
    public DashboardFragmentPagerAdapter(Context context, FragmentManager fm, User user) {
        super(fm);
        mContext = context;
        this.user=user;
    }

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
       return fragments.get(position);
    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return user.getRoles().size();
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return user.getRoles().get(position);
    }

    public void addFragment(Fragment fragment){
        fragments.add(fragment);
    }
}
