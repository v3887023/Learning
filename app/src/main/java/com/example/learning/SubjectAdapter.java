package com.example.learning;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by zhangcx@nemo-inc.com
 * Date: 2019-08-31
 * <p>
 * Description:
 */
public class SubjectAdapter extends BaseQuickAdapter<Subject, BaseViewHolder> {
    public SubjectAdapter(@Nullable List<Subject> data) {
        super(R.layout.item_subject, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Subject item) {
        setText(helper.getView(R.id.tv_title), item.getTitle());
        setText(helper.getView(R.id.tv_subtitle), item.getSubtitle());
    }

    private void setText(TextView textView, String text) {
        if (TextUtils.isEmpty(text)) {
            textView.setVisibility(View.GONE);
        } else {
            textView.setVisibility(View.VISIBLE);
            textView.setText(text);
        }
    }
}
