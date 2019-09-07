package com.example.learning.fragment;

import android.media.SoundPool;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.SoundEffectConstants;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.learning.BaseFragment;
import com.example.learning.Instantiable;
import com.example.learning.R;
import com.example.learning.widget.FlowLayout;

import java.util.Random;

/**
 * Created by zhangcx@nemo-inc.com
 * Date: 2019-09-05
 * <p>
 * Description:
 */
public class FlowLayoutFragment extends BaseFragment {

    public static final Instantiable<FlowLayoutFragment> FACTORY = args -> new FlowLayoutFragment();
    private FlowLayout flowLayout;
    private EditText textEt;
    private Button addButton;
    private Random random = new Random(System.currentTimeMillis());

    @Override
    protected void intViews() {
        flowLayout = findViewById(R.id.flow_layout);

        textEt = findViewById(R.id.et_text);
        textEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                event.getAction();
                return false;
            }
        });

        addButton = findViewById(R.id.btn_add);
        addButton.setOnClickListener(v -> {
            int padding = random.nextInt(50);
            String text = textEt.getText().toString();
            if (!TextUtils.isEmpty(text)) {
                addTextToFlowLayout(text, padding);
                textEt.setText("");
            }
        });
    }

    private void addTextToFlowLayout(String text) {
        addTextToFlowLayout(text, 0);
    }

    private void addTextToFlowLayout(String text, int padding) {
        TextView textView = new TextView(activity);
        textView.setText(text);
        textView.setPadding(padding, padding, padding, padding);
        textView.setBackgroundResource(R.drawable.bg_border);
        flowLayout.addView(textView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_flow_layout;
    }
}
