package com.hossam.codesroots.helper;

/**
 * Created by Hossam on 12/11/2019.
 */
public interface DialogClicks {
    int okChose = 1;
    int cancelChose = 2;
    int Complain = 3;

    void onChose(int chose);
}
