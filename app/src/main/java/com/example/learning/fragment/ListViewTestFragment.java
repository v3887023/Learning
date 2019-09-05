package com.example.learning.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.learning.BaseFragment;
import com.example.learning.DimensUtils;
import com.example.learning.Instantiable;
import com.example.learning.R;
import com.example.learning.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangcx@nemo-inc.com
 * Date: 2019-09-05
 * <p>
 * Description:
 */
public class ListViewTestFragment extends BaseFragment {

    public static final Instantiable<ListViewTestFragment> FACTORY = args -> new ListViewTestFragment();

    @Override
    protected void intViews() {
        ImageView testButton = findViewById(R.id.btn_test);
        testButton.setOnClickListener(v -> {
            View view = View.inflate(activity, R.layout.layout_panel, null);
            ListView listView = view.findViewById(R.id.list_view);

            List<String> list = new ArrayList<>();
            list.add("Pause All");
            list.add("Resume All");
            list.add("Delete");
            list.add("90.3 GB FREE/107.8 GB");

            String maxLengthStr = "";
            for (String str : list) {
                if (str.length() > maxLengthStr.length()) {
                    maxLengthStr = str;
                }
            }

            int margin = DimensUtils.dip2pixel(activity, 12);
            int maxStrWidth = 0;
            if (!TextUtils.isEmpty(maxLengthStr)) {
                maxStrWidth = StringUtils.getTextWidth(maxLengthStr, DimensUtils.dip2pixel(activity, 14)) + DimensUtils.dip2pixel(activity, 60);
            }

            int width = DimensUtils.dip2pixel(activity, 185);
            width = maxStrWidth > width ? maxStrWidth : width;

            ViewGroup.LayoutParams lp = listView.getLayoutParams();
            lp.width = width;
            listView.setLayoutParams(lp);

            width += 2 * margin;

            listView.setAdapter(new SimpleAdapter(activity, list));

            PopupWindow popupWindow = new PopupWindow(view);


            popupWindow.setWidth(width);
            popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            popupWindow.setFocusable(true);
            popupWindow.showAsDropDown(v, 0, -v.getHeight() - margin);
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_listview_test;
    }

    private class SimpleAdapter extends ArrayAdapter<String> {
        public SimpleAdapter(@NonNull Context context, @NonNull List<String> objects) {
            super(context, R.layout.panel_item, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = View.inflate(getContext(), R.layout.panel_item, null);

            TextView textTv = view.findViewById(R.id.tv_text);
            textTv.setText(getItem(position));

            return view;
        }
    }
}
