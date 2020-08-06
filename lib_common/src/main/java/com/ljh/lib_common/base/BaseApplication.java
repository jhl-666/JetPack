package com.ljh.lib_common.base;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.ljh.lib_common.image.ImageLoader;

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

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        // 程序在内存清理的时候执行
        ImageLoader.trimMemory(level);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        // 低内存的时候执行
        ImageLoader.clearAllMemoryCaches();
    }
}
