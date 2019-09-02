package com.example.learning;

import android.app.Activity;
import android.content.Context;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(oldStatusBarColor);
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
}
