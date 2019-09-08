package com.example.learning;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by zhangcx@nemo-inc.com
 * Date: 2019-09-01
 * <p>
 * Description:
 */
public abstract class BaseFragment extends Fragment {
    protected Activity activity;
    private View contentView;
    private int oldStatusBarColor;
    private int originScreenOrientation;

    public View getContentView() {
        return contentView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        activity = (Activity) context;
    }

    protected void runOnUiThread(Runnable runnable) {
        if (activity != null) {
            activity.runOnUiThread(runnable);
        }
    }

    protected void setFullscreen(boolean fullscreen) {
        if (fullscreen) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        onFullscreenStateChange(fullscreen);
    }

    protected void onFullscreenStateChange(boolean fullscreen) {

    }

    protected void onOrientationChange(int newOrientation) {

    }

    protected void setStatusBar(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.setStatusBarColor(color);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        originScreenOrientation = getRequestedOrientation();

        if (fullScreen()) {
            setFullscreen(true);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            oldStatusBarColor = window.getStatusBarColor();
            window.setStatusBarColor(getStatusBarColor());
        }

        View view = inflater.inflate(getLayoutId(), container, false);
        this.contentView = view;
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        intViews();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();


        if (fullScreen()) {
            setFullscreen(false);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(oldStatusBarColor);
        }
    }

    protected int getRequestedOrientation() {
        return activity == null ? ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED : activity.getRequestedOrientation();
    }

    protected void setRequestedOrientation(int requestedOrientation) {
        if (activity != null) {
            activity.setRequestedOrientation(requestedOrientation);
            onOrientationChange(requestedOrientation);
        }
    }

    protected boolean isOrientationPortrait() {
        return getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
    }

    protected void requestOrientationPortrait() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    protected void requestOrientationLandscape() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    protected void restoreOrientation() {
        if (originScreenOrientation != getRequestedOrientation()) {
            setRequestedOrientation(originScreenOrientation);
        }
    }

    protected abstract void intViews();

    protected <T extends View> T findViewById(@IdRes int id) {
        return contentView.findViewById(id);
    }

    public abstract int getLayoutId();

    /**
     * 处理返回事件
     *
     * @return 是否消费了此事件，是则该事件不会再继续传递下去
     */
    public boolean handleBackPressedEvent() {
        return false;
    }

    public int getStatusBarColor() {
        return Color.WHITE;
    }

    public boolean fullScreen() {
        return false;
    }
}
