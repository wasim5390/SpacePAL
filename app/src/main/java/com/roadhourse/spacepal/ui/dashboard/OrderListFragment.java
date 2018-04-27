package com.roadhourse.spacepal.ui.dashboard;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.roadhourse.spacepal.BaseFragment;
import com.roadhourse.spacepal.R;
import com.roadhourse.spacepal.model.response.Order;

import java.util.List;

import butterknife.BindView;

/**
 * Created by sidhu on 4/26/2018.
 */

public class OrderListFragment extends BaseFragment implements OrderListContract.View{

    public static String ARG_ROLE="role";
    public static String ARG_USER_ID="user_id";

    @BindView(R.id.jobsRecyclerView)
    RecyclerView jobsRecyclerView;

    OrderListContract.Presenter presenter;
    public static OrderListFragment getInstance(String role, String userId){
        Bundle args = new Bundle();
        args.putString(ARG_ROLE,role);
        args.putString(ARG_USER_ID,userId);
        OrderListFragment orderListFragment = new OrderListFragment();
        orderListFragment.setArguments(args);
        return orderListFragment;
    }

    @Override
    public int getID() {
        return R.layout.fragment_jobs_list;
    }

    @Override
    public void initUI(View view) {
        String role = getArguments().getString(ARG_ROLE,"");
        int userId = getArguments().getShort(ARG_USER_ID);
        presenter.getOrders(role);
    }

    @Override
    public void setPresenter(OrderListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showOrders(List<Order> mListOrder) {
        Toast.makeText(mBaseActivity, mListOrder.get(0).getPriority()+"", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(String text, boolean alert) {
        Toast.makeText(mBaseActivity, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressDialog(boolean isInProgress) {
        if(isInProgress)
            showProgress();
        else
            hideProgress();
    }
}
