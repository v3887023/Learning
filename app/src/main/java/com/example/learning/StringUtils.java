package com.example.learning;

import android.graphics.Paint;
import android.text.TextPaint;

/**
 * 字符串工具类
 */
public class StringUtils {
    public static int getTextWidth(String str, float size) {
        Paint paint = new TextPaint();
        paint.setTextSize(size);
        int iRet = 0;
        if (str != null && str.length() > 0) {
            int len = str.length();
            float[] widths = new float[len];
            paint.getTextWidths(str, widths);
            for (int j = 0; j < len; j++) {
                iRet += (int) Math.ceil(widths[j]);
            }
        }
        return iRet;
    }
}
