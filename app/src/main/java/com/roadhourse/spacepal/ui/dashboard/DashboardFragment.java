package com.roadhourse.spacepal.ui.dashboard;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.roadhourse.spacepal.BaseFragment;
import com.roadhourse.spacepal.R;
import com.roadhourse.spacepal.model.realm.RealmController;

import butterknife.BindView;

/**
 * Created by sidhu on 4/25/2018.
 */

public class DashboardFragment extends BaseFragment {

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.sliding_tabs)
    TabLayout tabLayout;

    @Override
    public int getID() {
        return R.layout.fragment_dashboard;
    }

    @Override
    public void initUI(View view) {
        tabLayout.setupWithViewPager(viewPager);
        DashboardFragmentPagerAdapter adapter = new DashboardFragmentPagerAdapter(getActivity(), getChildFragmentManager(), RealmController.with(getActivity()).getRoles(),RealmController.getInstance().getUser().getId());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);
    }

    public static DashboardFragment newInstance() {
        Bundle args = new Bundle();
        DashboardFragment fragment = new DashboardFragment();
        fragment.setArguments(args);
        return fragment;
    }


}
