package com.example.learning;

import android.content.Context;
import android.util.DisplayMetrics;

import java.lang.reflect.Field;

public class DisplayUtil {

    private static int sPortraitScreenWidth = 0;
    private static int sPortraitScreenHeight = 0;
    private static int sStatusBarHeight = 0;

    public static int getPortraitScreenWidth(Context context) {
        if (sPortraitScreenWidth > 0) {
            return sPortraitScreenWidth;
        }
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            int width = displayMetrics.widthPixels;
            int height = displayMetrics.heightPixels;
            sPortraitScreenWidth = width < height ? width : height;
            sPortraitScreenHeight = width < height ? height : width;
        } catch (Exception e) {

        }

        return sPortraitScreenWidth;
    }

    public static int getPortraitScreenHeight(Context context) {
        if (sPortraitScreenHeight > 0) {
            return sPortraitScreenHeight;
        }
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            int width = displayMetrics.widthPixels;
            int height = displayMetrics.heightPixels;
            sPortraitScreenWidth = width < height ? width : height;
            sPortraitScreenHeight = width < height ? height : width;
        } catch (Exception e) {

        }

        return sPortraitScreenHeight;
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = null;
        try {
            dm = context.getResources().getDisplayMetrics();
        } catch (Exception e) {

        }
        int width = 0;
        if (dm != null) {
            width = dm.widthPixels;
        }

        return width;
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics dm = null;
        try {
            dm = context.getResources().getDisplayMetrics();
        } catch (Exception e) {

        }
        int height = 0;
        if (dm != null) {
            height = dm.heightPixels;
        }

        return height;
    }

    public static int dp2px(Context context, float dpValue) {
        DisplayMetrics dm = null;
        try {
            dm = context.getResources().getDisplayMetrics();
        } catch (Exception e) {

        }
        int density = 0;
        if (dm != null) {
            density = (int)(dpValue * dm.density + 0.5f);
        } else {
            density = (int)(dpValue * 2);
        }
        return density;
    }

    /**
     * 获取状态栏的高度。
     */
    public static int getScreenStatusBarHeight(Context context) {
        if (sStatusBarHeight > 0) {
            return sStatusBarHeight;
        }
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object o = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = (Integer)field.get(o);
            sStatusBarHeight = context.getResources().getDimensionPixelSize(x);
            if (sStatusBarHeight > 100) {
                sStatusBarHeight = 100;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sStatusBarHeight > 0 ? sStatusBarHeight : 0;
    }

    // 将px值转换为sp值
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }
}
