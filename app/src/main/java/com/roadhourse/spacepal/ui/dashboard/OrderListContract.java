package com.roadhourse.spacepal.ui.dashboard;

import com.roadhourse.spacepal.BasePresenter;
import com.roadhourse.spacepal.BaseView;
import com.roadhourse.spacepal.model.response.Order;

import java.util.List;

/**
 * Created by sidhu on 4/27/2018.
 */

public class OrderListContract {
    interface View extends BaseView<Presenter> {
        void showOrders(List<Order> mListOrder);

        void showMessage(String text, boolean alert);
        void showProgressDialog(boolean isInProgress);

    }


    interface Presenter extends BasePresenter {
        void getOrders(String role);
    }
}
