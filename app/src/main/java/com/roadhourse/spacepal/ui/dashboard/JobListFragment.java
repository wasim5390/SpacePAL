package com.roadhourse.spacepal.ui.dashboard;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.roadhourse.spacepal.BaseFragment;
import com.roadhourse.spacepal.R;

import butterknife.BindView;

/**
 * Created by sidhu on 4/26/2018.
 */

public class JobListFragment extends BaseFragment {

    public static String ARG_ROLE="role";
    public static String ARG_USER_ID="user_id";

    @BindView(R.id.jobsRecyclerView)
    RecyclerView jobsRecyclerView;

    public static JobListFragment getInstance(String role,String userId){
        Bundle args = new Bundle();
        args.putString(ARG_ROLE,role);
        args.putString(ARG_USER_ID,userId);
        JobListFragment jobListFragment = new JobListFragment();
        jobListFragment.setArguments(args);
        return jobListFragment;
    }

    @Override
    public int getID() {
        return R.layout.fragment_jobs_list;
    }

    @Override
    public void initUI(View view) {
        String role = getArguments().getString(ARG_ROLE,"");
        int userId = getArguments().getShort(ARG_USER_ID);
    }
}
