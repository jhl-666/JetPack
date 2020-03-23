package com.ljh.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.ljh.lib_common.image.ImageLoader
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ImageLoader.loadImageView("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1584952660974&di=7e3c106ab6033ef9e8e67661fb24b072&imgtype=0&src=http%3A%2F%2Fa3.att.hudong.com%2F68%2F61%2F300000839764127060614318218_950.jpg",imageView)
    }
}
