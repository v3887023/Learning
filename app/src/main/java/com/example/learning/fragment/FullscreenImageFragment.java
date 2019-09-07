package com.example.learning.fragment;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.learning.BaseFragment;
import com.example.learning.DisplayUtil;
import com.example.learning.Instantiable;
import com.example.learning.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangcx@nemo-inc.com
 * Date: 2019-09-05
 * <p>
 * Description:
 */
public class FullscreenImageFragment extends BaseFragment {

    public static final Instantiable<FullscreenImageFragment> FACTORY = args -> new FullscreenImageFragment();
    private ImageView videoView;
    private FrameLayout fullscreenContainer;
    private Button fullscreenButton;
    private ListView listView;
    private LinearLayout videoContainer;
    private File videoFile;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        File rootFile = Environment.getExternalStorageDirectory();
        String videoPath = findFirstVideoPathInFile(rootFile);

        if (videoPath == null) {
            return;
        }

        videoFile = new File(videoPath);
        if (!videoFile.exists()) {
            videoFile = null;
        }
    }

    private String findFirstVideoPathInFile(File file) {
        if (file != null) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    if (f.getName().contains(".jpg")) {
                        return f.getAbsolutePath();
                    }

                    String s = findFirstVideoPathInFile(f);
                    if (s != null) {
                        return s;
                    }
                }
            }
        }

        return null;
    }

    @Override
    protected void intViews() {
        videoContainer = findViewById(R.id.video_container);
        videoView = findViewById(R.id.iv_image);
        fullscreenButton = findViewById(R.id.btn_fullscreen);
        fullscreenContainer = findViewById(R.id.fullscreen_container);
        listView = findViewById(R.id.listView);

        ViewGroup.LayoutParams lp = videoView.getLayoutParams();
        lp.width = DisplayUtil.getPortraitScreenWidth(activity);
        lp.height = lp.width * 3 / 4;
        videoView.setLayoutParams(lp);

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            list.add(String.valueOf(i));
        }
        listView.setAdapter(new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, list));

        fullscreenButton.setOnClickListener(v -> {
            setFullscreen(true);
            requestOrientationLandscape();
        });

        if (videoFile != null) {
//            videoView.setVideoPath(videoFile.getAbsolutePath());
//            videoView.start();
            videoView.setImageBitmap(BitmapFactory.decodeFile(videoFile.getAbsolutePath()));
        }
    }

    @Override
    public boolean handleBackPressedEvent() {
        if (isOrientationPortrait()) {
            return false;
        }

        setFullscreen(false);
        restoreOrientation();

        return true;
    }

    @Override
    protected void onFullscreenStateChange(boolean fullscreen) {
        if (fullscreen) {
            fullscreenButton.setVisibility(View.GONE);
            fullscreenContainer.setVisibility(View.VISIBLE);
            videoContainer.removeView(videoView);
            fullscreenContainer.addView(videoView);
            listView.setVisibility(View.GONE);
        } else {
            fullscreenButton.setVisibility(View.VISIBLE);
            fullscreenContainer.setVisibility(View.GONE);
            fullscreenContainer.removeView(videoView);
            videoContainer.addView(videoView, 0);
            listView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onOrientationChange(int newOrientation) {
        if (isOrientationPortrait()) {
            ViewGroup.LayoutParams lp = videoView.getLayoutParams();
            lp.width = DisplayUtil.getPortraitScreenWidth(activity);
            lp.height = lp.width * 3 / 4;
            videoView.setLayoutParams(lp);
        } else {
            ViewGroup.LayoutParams lp = videoView.getLayoutParams();
            lp.height = DisplayUtil.getPortraitScreenWidth(activity);
            lp.width = DisplayUtil.getPortraitScreenHeight(activity);
            videoView.setLayoutParams(lp);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

//        videoView.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
//        videoView.pause();
    }

    @Override
    public void onDestroyView() {
//        videoView.stopPlayback();
        super.onDestroyView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fullscreen_image;
    }
}
