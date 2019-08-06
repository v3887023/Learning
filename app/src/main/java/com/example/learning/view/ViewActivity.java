package com.example.learning.view;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.BounceInterpolator;
import android.widget.TextView;

import com.example.learning.GoButton;
import com.example.learning.R;

public class ViewActivity extends AppCompatActivity {
    private static final String TAG = ViewActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        final TextView fakeBox = findViewById(R.id.fake_box);
        GoButton goButton = findViewById(R.id.btn_go);

        goButton.setOnPressedListener(new GoButton.OnPressedListener() {
            private int offset = 0;
            private long last = 0;

            @Override
            public void onPressed(long duration) {
                Log.d(TAG, "onPressed");
                int d = (int) (9.8 * Math.pow((duration - last) / 1000f, 2));
                offset += d;
//                fakeBox.layout(fakeBox.getLeft(), fakeBox.getTop() - d, fakeBox.getRight(), fakeBox.getBottom() - d);
//                fakeBox.scrollBy(0, d);
                fakeBox.setTranslationY(fakeBox.getTranslationY() - d);
            }

            @Override
            public void onCancel(long duration) {
                Log.d(TAG, "onCancel");
//                fakeBox.layout(fakeBox.getLeft(), fakeBox.getTop() + offset, fakeBox.getRight(), fakeBox.getBottom() + offset);
//                fakeBox.scrollBy(0, -offset);
//                fakeBox.setTranslationY(fakeBox.getTranslationY() + offset);
                final ObjectAnimator animator =
                        ObjectAnimator.ofFloat(fakeBox, "translationY",
                                fakeBox.getTranslationY(), fakeBox.getTranslationY() + offset);
                animator.setInterpolator(new BounceInterpolator());
                animator.setDuration(duration).start();
                offset = 0;
                last = 0;
            }
        });
    }
}
