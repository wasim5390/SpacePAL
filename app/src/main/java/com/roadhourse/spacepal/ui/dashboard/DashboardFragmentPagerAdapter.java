package com.roadhourse.spacepal.ui.dashboard;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.roadhourse.spacepal.model.Role;

import java.util.List;

/**
 * Created by sidhu on 4/26/2018.
 */

public class DashboardFragmentPagerAdapter extends FragmentStatePagerAdapter{

    private Context mContext;
    private List<Role> mRolesList;
    private String userId;
    public DashboardFragmentPagerAdapter(Context context, FragmentManager fm, List<Role> roles, String userId) {
        super(fm);
        mContext = context;
        this.mRolesList = roles;
        this.userId = userId;
    }

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        return JobListFragment.getInstance(mRolesList.get(position).getName(),userId);
    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return mRolesList.size();
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return mRolesList.get(position).getName();
    }
}
