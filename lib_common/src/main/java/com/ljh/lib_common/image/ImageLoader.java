package com.ljh.lib_common.image;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.ljh.lib_common.GlideApp;
import com.ljh.lib_common.base.BaseApplication;
import com.ljh.lib_common.image.glide.CircleEdgeColorTransform;
import com.ljh.lib_common.image.glide.DrawableCallBack;


/**
 * Created by ljh on 2018/6/4.
 */

public class ImageLoader {

    /*private ImageLoader() {

    }
    private static class ImageLoaderHolder {
        private static ImageLoader INSTANCE = new ImageLoader();
    }

    public static ImageLoader getInstance() {
        return ImageLoaderHolder.INSTANCE;
    }*/

    private static  final int crossFadeTime = 800;//渐显效果时长
    private static  final int defaultRound = 4;//默认圆角

    /**
     * 默认加载
     */
    public  static void loadImageView(Context context,Object path, ImageView mImageView) {
        Glide.with(context).load(path)
                .into(mImageView);
    }
    public  static void loadImageView(Activity context,Object path, ImageView mImageView) {
        Glide.with(context).load(path)
                .into(mImageView);
    }
    public  static void loadImageView(Fragment context, Object path, ImageView mImageView) {
        Glide.with(context).load(path)
                .into(mImageView);
    }

    /**
     * CenterCrop
     */
    public  static void loadImageViewCenterCrop(Context context,Object path, ImageView mImageView) {
        Glide.with(context)
                .load(path)
                .transition(new DrawableTransitionOptions().crossFade(crossFadeTime))//渐显效果
                .into(mImageView);
    }
    public  static void loadImageViewCenterCrop(Activity context,Object path, ImageView mImageView) {
        Glide.with(context)
                .load(path)
                .transition(new DrawableTransitionOptions().crossFade(crossFadeTime))//渐显效果
                .into(mImageView);
    }
    public  static void loadImageViewCenterCrop(Fragment context,Object path, ImageView mImageView) {
        Glide.with(context)
                .load(path)
                .transition(new DrawableTransitionOptions().crossFade(crossFadeTime))//渐显效果
                .into(mImageView);
    }

    /**
     * 圆形
     */
    public static  void loadCircleImageView(Context context,Object path, ImageView mImageView) {
        Glide.with(context)
                .load(path)
                .into(mImageView);
    }
    public static  void loadCircleImageView(Activity context,Object path, ImageView mImageView) {
        Glide.with(context)
                .load(path)
                .into(mImageView);
    }
    public static  void loadCircleImageView(Fragment context,Object path, ImageView mImageView) {
        Glide.with(context)
                .load(path)
                .into(mImageView);
    }

    /**
     * 带边框的圆形
     */
    public  static void loadCircleEdgeColorImageView(Context context,Object path, ImageView mImageView, int borderWidth, int color) {
        Glide.with(context)
                .load(path)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .skipMemoryCache(true)
                .transform(new CircleEdgeColorTransform(borderWidth, color))
                .into(mImageView);
    }
    public  static void loadCircleEdgeColorImageView(Activity context,Object path, ImageView mImageView, int borderWidth, int color) {
        Glide.with(context)
                .load(path)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .skipMemoryCache(true)
                .transform(new CircleEdgeColorTransform(borderWidth, color))
                .into(mImageView);
    }

    public  static void loadCircleEdgeColorImageView(Fragment context,Object path, ImageView mImageView, int borderWidth, int color) {
        Glide.with(context)
                .load(path)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .skipMemoryCache(true)
                .transform(new CircleEdgeColorTransform(borderWidth, color))
                .into(mImageView);
    }


    /**
     * 默认圆角
     */
    public  static void loadDefaultRoundImageView(Context context,Object path, ImageView mImageView) {
        Glide.with(context)
                .load(path)
                .transform(new RoundedCorners(defaultRound))
                .into(mImageView);
    }
    public  static void loadDefaultRoundImageView(Activity context,Object path, ImageView mImageView) {
        Glide.with(context)
                .load(path)
                .transform(new RoundedCorners(defaultRound))
                .into(mImageView);
    }
    public  static void loadDefaultRoundImageView(Fragment context,Object path, ImageView mImageView) {
        Glide.with(context)
                .load(path)
                .transform(new RoundedCorners(defaultRound))
                .into(mImageView);
    }


    /**
     * 可变圆角
     */
    public  static void loadRoundImageView(Context context,Object path, ImageView mImageView, int radius) {
        Glide.with(context)
                .load(path)
                .transform(new RoundedCorners(radius))
                .into(mImageView);
    }
    public  static void loadRoundImageView(Activity context,Object path, ImageView mImageView, int radius) {
        Glide.with(context)
                .load(path)
                .transform(new RoundedCorners(radius))
                .into(mImageView);
    }
    public  static void loadRoundImageView(Fragment context,Object path, ImageView mImageView, int radius) {
        Glide.with(context)
                .load(path)
                .transform(new RoundedCorners(radius))
                .into(mImageView);
    }

    /**
     * 先加载小图
     */
    public  static void loadImageViewThumbnail(Context context,Object path, float thumbnail, ImageView mImageView) {
        Glide.with(context)
                .load(path)
                .thumbnail(thumbnail)
                .into(mImageView);
    }

    public  static void loadImageViewThumbnail(Activity context,Object path, float thumbnail, ImageView mImageView) {
        Glide.with(context)
                .load(path)
                .thumbnail(thumbnail)
                .into(mImageView);
    }
    public  static void loadImageViewThumbnail(Fragment context,Object path, float thumbnail, ImageView mImageView) {
        Glide.with(context)
                .load(path)
                .thumbnail(thumbnail)
                .into(mImageView);
    }


    public static void getDrawable(Context context, Object path, final DrawableCallBack callBack) {
        Glide.with(context)
                .load(path)
                .into(new CustomTarget<Drawable>() {

                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        callBack.success(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                        callBack.failure("");
                    }
                });
    }
    public static void getDrawable(Activity context, Object path, final DrawableCallBack callBack) {
        Glide.with(context)
                .load(path)
                .into(new CustomTarget<Drawable>() {

                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        callBack.success(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                        callBack.failure("");
                    }
                });
    }
    public static void getDrawable(Fragment context, Object path, final DrawableCallBack callBack) {
        Glide.with(context)
                .load(path)
                .into(new CustomTarget<Drawable>() {

                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        callBack.success(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                        callBack.failure("");
                    }
                });
    }

    public static void trimMemory(int level) {
        Glide.with(BaseApplication.getContext()).onTrimMemory(level);
    }

    public static void clearAllMemoryCaches() {
        Glide.with(BaseApplication.getContext()).onLowMemory();
    }

}
