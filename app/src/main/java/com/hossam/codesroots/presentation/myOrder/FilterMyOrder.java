package com.hossam.codesroots.presentation.myOrder;

import com.hossam.codesroots.entities.MyOrderData;
import java.util.List;

public class FilterMyOrder {

    List<MyOrderData> commpleteOrderData;
    List<MyOrderData> notCommpleteOrderData;

    public FilterMyOrder(List<MyOrderData> commpleteOrderData, List<MyOrderData> notCommpleteOrderData) {
        this.commpleteOrderData = commpleteOrderData;
        this.notCommpleteOrderData = notCommpleteOrderData;
    }
}
