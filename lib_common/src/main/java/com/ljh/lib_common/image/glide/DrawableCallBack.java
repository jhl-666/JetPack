package com.ljh.lib_common.image.glide;

import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

public interface DrawableCallBack {
    void success(@NonNull Drawable status);
    void failure(String message);
}