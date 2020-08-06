package com.ljh.app.apkupdate;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Binder;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

import com.ljh.app.MainActivity;
import com.ljh.app.R;
import com.ljh.app.module.MessageEvent;
import com.ljh.lib_common.utils.FileUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.List;

/**
 * 为了保证下载任务可以一直在后台运行，我们创建一个下载的服务
 */
public class DownloadService extends Service {
 
    private DownloadTask downloadTask;
    private int notifyId = 1;
    private String downloadUrl;
    private DownloadListener mCallback;
 
    //创建 DownloadListener的匿名类实例，并在类中实现了5个方法
     private DownloadListener listener=new DownloadListener() {
         @Override
         public void onProgress(int progress) {
             //调用getNotification方法构建了一个用于显示下载进度的通知
             //然后调用NotificationManager()的notify方法去触发这个通知
             //这样就可以在状态栏看到下载进度了
             getNotificationManager().notify(notifyId,getNotification("已下载",progress));

             if (mCallback != null) {
                 mCallback.onProgress(progress);
             }
         }
 
 
         @Override
         public void onSuccess(String path) {
             downloadTask=null;
             //下载成功时，将前台服务通知关闭，并创建一个下载成功的通知
             //关闭前台服务通知
             //stopForeground(true);
             //getNotificationManager().notify(notifyId,getNotification("下载成功",-1));
             //Toast.makeText(DownloadService.this,"Download Success",Toast.LENGTH_SHORT).show();
             getNotificationManager().cancel(notifyId);
             stopForeground(true);

             if (mCallback != null) {
                 mCallback.onSuccess(path);
             }
         }
 
         @Override
         public void onFailed() {
             downloadTask=null;
             //下载失败时，将前台服务通知关闭，并创建一个下载失败的通知
             //关闭前台服务通知
             stopForeground(true);
             getNotificationManager().notify(notifyId,getNotification("下载失败",-1));
            // Toast.makeText(DownloadService.this,"Download Failed",Toast.LENGTH_SHORT).show();

             if (mCallback != null) {
                 mCallback.onFailed();
             }
         }
 
         @Override
         public void onPaused() {
             downloadTask=null;
             //Toast.makeText(DownloadService.this,"Paused",Toast.LENGTH_SHORT).show();
             if (mCallback != null) {
                 mCallback.onPaused();
             }
         }
 
         @Override
         public void onCanceled() {
             downloadTask=null;
             stopForeground(true);
             //Toast.makeText(DownloadService.this,"Canceled",Toast.LENGTH_SHORT).show();
             if (mCallback != null) {
                 mCallback.onCanceled();
             }
         }
     };
 
 
    /**
     * 下面的内容是为了让服务（service）与活动（Activity）进行通信
     */
    private DownloadBinder mBinder = new DownloadBinder(this);
 
    @Override
    public IBinder onBind(Intent intent) {
       return mBinder;
    }

    //创建DownloadBinder
    public class DownloadBinder extends Binder {

        private final WeakReference<DownloadService> mService;

        public DownloadBinder(DownloadService downloadService) {
            mService = new WeakReference<>(downloadService);
        }

        public void setDownloadCallback(DownloadListener downloadCallback) {
            if (mService.get() != null) {
                mService.get().setDownloadCallback(downloadCallback);
            }
        }

        public void removeCallback() {
            if (mService.get() != null) {
                mService.get().removeCallback();
            }
        }

        //开始下载
        public void startDownload(String url){
            if (downloadTask==null){
                downloadUrl=url;
                //创建一个downloadTask实例，把DownloadListener作为参数传入
                downloadTask=new DownloadTask(listener);
                //调用execute方法开始下载，方法中传入下载的url
                downloadTask.execute(downloadUrl);
                //前台显示下载
                startForeground(notifyId,getNotification("已下载",0));
                //Toast.makeText(DownloadService.this,"Downloading...",Toast.LENGTH_SHORT).show();
            }
        }
 
        //暂停下载
        public void pauseDownload(){
            //暂停下载，直接调用DownloadTask的pauseDownload方法
            if (downloadTask!=null){
                downloadTask.pauseDownload();
            }
 
        }
        //取消下载
        public void cancelDownload(){
            if (downloadTask!=null){
                downloadTask.cancelDownload();
            }
            if (downloadUrl!=null){
                //取消下载时需要将文件删除，并将通知关闭
                //根据URL解析出下载的文件名
                String fileName=downloadUrl.substring(downloadUrl.lastIndexOf("/"));
                File file = FileUtil.getFile("NewApk",fileName);

                //取消下载时需要将文件删除，并将通知关闭
                if (file.exists()){
                    file.delete();
                }
                getNotificationManager().cancel(notifyId);
                stopForeground(true);
                //Toast.makeText(DownloadService.this,"Canceled",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void removeCallback() {
        mCallback = null;
    }

    private void setDownloadCallback(DownloadListener downloadCallback) {
        mCallback = downloadCallback;
    }


    private NotificationManager getNotificationManager(){
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }
 
    private Notification getNotification(String title, int progress){
        //Intent intent=new Intent(this, MainActivity.class);
       // PendingIntent pi=PendingIntent.getActivity(this,0,intent,0);
        Notification.Builder builder=new Notification.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        //builder.setContentIntent(pi);
        builder.setContentTitle(title);
        if (progress>=0){
            //当progress大于等于0时才需要显示下载进度
            builder.setContentText(progress+"%");
            //第一个参数传入通知的最大进度，第二个是通知的当前进度，第三个是是否使用模糊进度条
            builder.setProgress(100,progress,false);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {//8.0及以上设置通知渠道
            NotificationChannel channel = new NotificationChannel("001","apk_download",NotificationManager.IMPORTANCE_LOW);
            channel.enableLights(false); //是否在桌面icon右上角展示小红点
            channel.setLightColor(Color.RED); //小红点颜色
            channel.setShowBadge(false); //是否在久按桌面图标时显示此渠道的通知
            getNotificationManager().createNotificationChannel(channel);
            builder.setChannelId("001");

        }

        return builder.build();
    }

}