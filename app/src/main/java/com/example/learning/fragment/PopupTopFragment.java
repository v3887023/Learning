package com.example.learning.fragment;

import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.learning.BaseFragment;
import com.example.learning.Instantiable;
import com.example.learning.R;

/**
 * Created by zhangcx@nemo-inc.com
 * Date: 2019-09-05
 * <p>
 * Description:
 */
public class PopupTopFragment extends BaseFragment {

    public static final Instantiable<PopupTopFragment> FACTORY = args -> new PopupTopFragment();
    private PopupWindow mPopupWindow;
    private float mLastY;
    private int mOffsetY = 0;

    @Override
    protected void intViews() {
        findViewById(R.id.btn_show).setOnClickListener(v -> {
            if (!mPopupWindow.isShowing()) {
                mPopupWindow.showAtLocation(getView(), Gravity.TOP, 0, 0);
            }
        });

        findViewById(R.id.btn_dismiss).setOnClickListener(v -> {
            if (mPopupWindow.isShowing()) {
                mPopupWindow.dismiss();
            }
        });

        initPopupWindow();
    }

    private void initPopupWindow() {
        mPopupWindow = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        View contentView = getLayoutInflater().inflate(R.layout.layout_notice, null);
        ((TextView) contentView.findViewById(R.id.tv_title)).setText("新通知");
        ((TextView) contentView.findViewById(R.id.tv_content)).setText("通知内容");
        mPopupWindow.setContentView(contentView);
        mPopupWindow.setAnimationStyle(R.style.TopPopupWindowStyle);
        mPopupWindow.setOutsideTouchable(false);
        mPopupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float y = event.getY();
                int offsetY = (int) (y - mLastY);
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        mLastY = y;
                        mOffsetY = 0;
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        mLastY = y;
                        boolean canScroll = mOffsetY < 0 || mOffsetY == 0 && offsetY < 0;
                        if (canScroll) {
                            v.scrollBy(0, -offsetY);
                            mOffsetY += offsetY;
                            if (mOffsetY > v.getHeight()) {
                                mOffsetY = v.getHeight();
                            }
                        }
                        return true;
                    case MotionEvent.ACTION_UP:
                        if (Math.abs(mOffsetY) > v.getHeight() / 4f) {
                            mPopupWindow.dismiss();
                        } else {
                            v.scrollBy(0, mOffsetY);
                        }
                        return false;
                    default:
                        return false;
                }
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_popup_top;
    }
}
