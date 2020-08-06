package com.ljh.app.apkupdate;

/**
 * 用于对下载过程中的各种状态进行监听和回调
 */

public interface DownloadListener {
    void onProgress(int progress);//通知当前的下载进度
    void onSuccess(String path);//通知下载成功
    void onFailed();//失败
    void onPaused();//暂停
    void onCanceled();//取消
}
