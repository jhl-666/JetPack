package com.ljh.lib_common.base;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.ljh.lib_common.R;


public class BaseDialogFragment extends DialogFragment {

    @NonNull
    @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
        //设置动画效果，在具体的dialog中调用，这个动画是底部弹出动画
        //setStyle(STYLE_NO_TITLE, R.style.LibCommonBottomDialog);
        return super.onCreateDialog(savedInstanceState);
    }

    @Override public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getDialog() != null) {
            /*getDialog().setOnShowListener(null);
            getDialog().setOnCancelListener(null);
            getDialog().setOnDismissListener(null);*/
            WindowManager.LayoutParams params = getDialog().getWindow()
                    .getAttributes();
            params.gravity = Gravity.BOTTOM;
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            getDialog().getWindow()
                    .setAttributes(params);
        }



    }

    public void show(FragmentManager manager) {
        if (manager == null) return;
        if (this.isAdded()) {
            return;
        }

        manager.beginTransaction()
                .add(this, getClass().getName())
                .commitAllowingStateLoss();
    }

    @Override
    public void onDestroyView() {
        /*if (getView() instanceof ViewGroup) {
            ((ViewGroup)getView()).removeAllViews();
        }*/
        super.onDestroyView();
    }

    /**
     * Can not perform this action after onSaveInstanceState
     */
    public void show(AppCompatActivity activity) {
        if (activity == null || activity.isFinishing()) return;
        if (!isAdded()) {
            /*if (activity instanceof BaseActivity) {
                BaseActivity baseActivity = (BaseActivity) activity;
                if (baseActivity.isOnPause) return;
            }*/
            show(activity.getSupportFragmentManager());
        }
    }
}