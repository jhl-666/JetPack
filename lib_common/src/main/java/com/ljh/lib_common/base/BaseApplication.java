package com.ljh.lib_common.base;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

/**
 * Created by ljh on 2018/6/4.
 */

public abstract class BaseApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }


    public static Context getContext() {
        return mContext;
    }
}
