package com.ljh.app

import android.Manifest
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.ljh.app.apkupdate.DownloadListener
import com.ljh.app.apkupdate.DownloadService
import com.ljh.app.module.MessageEvent
import com.ljh.app.util.AppUtils
import com.ljh.lib_common.utils.LogUtil
import kotlinx.android.synthetic.main.activity_apk_download.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.io.File

class ApkDownloadActivity : AppCompatActivity() {
    private var downloadBinder: DownloadService.DownloadBinder? = null
    private var apkFile : File? = null
    private final val REQUEST_CODE = 111

    //创建了一个ServiceConnection匿名类
    private val connection: ServiceConnection =
        object : ServiceConnection {
            override fun onServiceConnected(
                name: ComponentName,
                service: IBinder
            ) {
                LogUtil.e("onServiceConnected")
                //获取到DownloadBinder的实例，用这个实例在活动中调用服务提供的各种方法
                downloadBinder = service as DownloadService.DownloadBinder
                downloadBinder?.setDownloadCallback(object : DownloadListener{
                    override fun onSuccess(path: String?) {
                        apkFile = File(path)
                        checkIsAndroidO()
                    }

                    override fun onFailed() {
                    }

                    override fun onProgress(progress: Int) {
                    }

                    override fun onCanceled() {
                    }

                    override fun onPaused() {
                    }

                })
            }

            override fun onServiceDisconnected(name: ComponentName) {}
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apk_download)
        //开启下载后台服务,启动服务可以让服务在后台一直运行
        var intent= Intent(this,DownloadService::class.java)
        startService(intent);
        bindService(intent,connection,BIND_AUTO_CREATE);//绑定服务，可以让服务与活动进行通信

        initClick()
    }

    override fun onResume() {
        super.onResume()
    }

    private fun initClick() {
        start_download.setOnClickListener {

            //可能需要先申请外部存储权限
            val url = "https://img.mayun.cn/download/cloud_shot_yunpai.apk"
            downloadBinder?.startDownload(url)
        }
        pause_download.setOnClickListener {
            downloadBinder?.pauseDownload()
        }
        cancel_download.setOnClickListener {
            downloadBinder?.cancelDownload()
        }
    }

    private fun checkIsAndroidO() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {//8.0
            //来判断应用是否有权限安装apk
            val installAllowed = packageManager.canRequestPackageInstalls()
            //有权限
            if (installAllowed) {
                //安装apk
                installApk()
            } else {
                //无权限 申请权限
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.REQUEST_INSTALL_PACKAGES),
                    REQUEST_CODE)
            }
        } else {//8.0以下
            installApk()
        }
    }

    private fun installApk() {
        if (apkFile?.exists() == true) {
            AppUtils.installApk(this@ApkDownloadActivity,apkFile)
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE -> {
                installApk()
            }
        }
    }

    override fun onDestroy() {
        downloadBinder?.removeCallback()
        unbindService(connection);
        super.onDestroy()
    }
}
