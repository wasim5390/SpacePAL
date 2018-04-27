package com.roadhourse.spacepal.ui.dashboard;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.roadhourse.spacepal.BaseActivity;
import com.roadhourse.spacepal.R;
import com.roadhourse.spacepal.model.User;
import com.roadhourse.spacepal.util.PreferenceUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sidhu on 4/25/2018.
 */

public class DashboardActivity extends BaseActivity{
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.sliding_tabs)
    TabLayout tabLayout;
    @Override
    public int getID() {
        return R.layout.activity_dashboard;
    }

    @Override
    public void created(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        // setToolBar(toolbar,getText(R.string.login),true);

        tabLayout.setupWithViewPager(viewPager);

        DashboardFragmentPagerAdapter adapter = new DashboardFragmentPagerAdapter(this, getSupportFragmentManager(), PreferenceUtil.getInstance(this).getAccount());
        User user =PreferenceUtil.getInstance(this).getAccount();

        List<OrderListFragment> orderListFragments = new ArrayList<>();
        for(int i=0;i<user.getRoles().size();i++){
            orderListFragments.add(OrderListFragment.getInstance(user.getRoles().get(i),user.getId()));
            new OrderListPresenter(orderListFragments.get(i), PreferenceUtil.getInstance(this));
            adapter.addFragment(orderListFragments.get(i));
        }

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

    }
}
