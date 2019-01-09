package com.hossam.codesroots.presentation.myOrder;

import com.hossam.codesroots.entities.MYOrdersModel;

import java.util.List;

public class FilterMyOrder {

    List<MYOrdersModel.DataBean> commpleteOrderData;
    List<MYOrdersModel.DataBean> notCommpleteOrderData;

    public FilterMyOrder(List<MYOrdersModel.DataBean> commpleteOrderData, List<MYOrdersModel.DataBean> notCommpleteOrderData) {
        this.commpleteOrderData = commpleteOrderData;
        this.notCommpleteOrderData = notCommpleteOrderData;
    }
}
