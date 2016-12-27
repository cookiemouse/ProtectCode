package com.mouse.cookie.protectcode.dataworkinterface;

/**
 * Created by cookie on 2016/12/20.
 */

public interface OnDataCompleteListener extends OnDataBaseListener{
    void onComplete();
    void onFailure();
}
