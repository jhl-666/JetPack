package com.ljh.app

import android.Manifest
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.bumptech.glide.Glide
import com.ljh.app.util.AppUtils
import com.ljh.lib_common.image.ImageLoader
import com.ljh.lib_common.image.glide.DrawableCallBack
import com.ljh.lib_common.utils.DownloadUtil
import com.ljh.lib_common.utils.FileUtil
import com.ljh.lib_common.utils.LogUtil
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ImageLoader.loadImageView(this,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1584952660974&di=7e3c106ab6033ef9e8e67661fb24b072&imgtype=0&src=http%3A%2F%2Fa3.att.hudong.com%2F68%2F61%2F300000839764127060614318218_950.jpg",imageView)

        ImageLoader.getDrawable(this,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1584952660974&di=7e3c106ab6033ef9e8e67661fb24b072&imgtype=0&src=http%3A%2F%2Fa3.att.hudong.com%2F68%2F61%2F300000839764127060614318218_950.jpg",
        object:DrawableCallBack{
            override fun success(status: Drawable) {
                Toast.makeText(this@MainActivity,"sssss",1).show()
                imageView.setImageDrawable(status)

            }

            override fun failure(message: String?) {
            }

        })

        //var file = FileUtil.getFile("aaa","wode.apk",false)

        //AppUtils.installApk(this@MainActivity,file)

       /* DownloadUtil.getInstance().download("https://img.mayun.cn/download/cloud_shot_yunpai.apk",file.absolutePath,object : DownloadUtil.OnDownloadListener{
            override fun onDownloading(progress: Int) {
                LogUtil.e("onDownloading--${progress}")
            }

            override fun onDownloadFailed() {
                LogUtil.e("onDownloadFailed")
            }

            override fun onDownloadSuccess(path: String?) {
                AppUtils.installApk(this@MainActivity,file)
                LogUtil.e("onDownloadSuccess")
            }

        })
*/

        downLoadApk.setOnClickListener {
            startActivity(Intent(this,ApkDownloadActivity::class.java))
        }
    }


}
