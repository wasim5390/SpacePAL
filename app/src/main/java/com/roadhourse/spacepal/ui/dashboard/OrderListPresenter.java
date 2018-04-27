package com.roadhourse.spacepal.ui.dashboard;

import com.roadhourse.spacepal.model.response.APIError;
import com.roadhourse.spacepal.model.response.Order;
import com.roadhourse.spacepal.source.RetrofitHelper;
import com.roadhourse.spacepal.util.PreferenceUtil;
import com.roadhourse.spacepal.util.Util;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sidhu on 4/27/2018.
 */

public class OrderListPresenter implements OrderListContract.Presenter {

    private OrderListContract.View view;
    private PreferenceUtil preferenceUtil;

    public OrderListPresenter(OrderListContract.View view, PreferenceUtil preferenceUtil) {
        this.view = view;
        this.preferenceUtil = preferenceUtil;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getOrders(String role) {
        Call<List<Order>> call = RetrofitHelper.getInstance().getApi().getOrders(role);
        call.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                view.showProgressDialog(false);
                if (response.code()==200) {
                    view.showOrders(response.body());

                } else {
                    APIError error = Util.parseError(response);
                    view.showMessage(error.getErrorDescription(),true);

                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                view.showProgressDialog(false);
                view.showMessage("Fail...",true);
                t.printStackTrace();
            }
        });
    }
}
