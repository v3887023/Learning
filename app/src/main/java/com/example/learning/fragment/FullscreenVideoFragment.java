package com.example.learning.fragment;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.VideoView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
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
public class FullscreenVideoFragment extends BaseFragment {

    public static final Instantiable<FullscreenVideoFragment> FACTORY = args -> new FullscreenVideoFragment();
    private static final int MAX_VIDEO_COUNT = 20;
    private VideoView videoView;
    private ViewGroup fullscreenContainer;
    private ViewGroup videoContainer;
    private boolean fullscreen = false;
    private RecyclerView videoRv;
    private VideoAdapter videoAdapter;
    private List<String> videoPathList;
    private MediaMetadataRetriever mediaMetadataRetriever;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mediaMetadataRetriever = new MediaMetadataRetriever();

        videoPathList = new ArrayList<>();
        videoAdapter = new VideoAdapter(videoPathList);

        File rootFile = Environment.getExternalStorageDirectory();
        new Thread(() -> findFirstVideoPathInFile(rootFile)).start();
    }

    private void findFirstVideoPathInFile(File file) {
        if (file != null) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    if (f.getName().contains(".mp4")) {
                        if (videoPathList.size() < MAX_VIDEO_COUNT) {
                            videoPathList.add(f.getAbsolutePath());
                        } else {
                            runOnUiThread(() -> videoAdapter.notifyDataSetChanged());
                            return;
                        }
                    }

                    findFirstVideoPathInFile(f);
                }
            }
        }
    }

    @Override
    protected void intViews() {
        videoView = new VideoView(activity);
        videoContainer = findViewById(R.id.video_container);
        fullscreenContainer = findViewById(R.id.fullscreen_container);

        videoRv = findViewById(R.id.rv_video);
        videoRv.setLayoutManager(new LinearLayoutManager(activity));
        videoRv.setAdapter(videoAdapter);

        videoAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            int id = view.getId();
            if (id == R.id.video_container) {
                ((ViewGroup) view.getParent()).findViewById(R.id.iv_frame).setVisibility(View.GONE);

                ViewGroup oldVideoContainer = videoContainer;
                if (oldVideoContainer != null) {
                    oldVideoContainer.removeView(videoView);
                }

                videoContainer = ((ViewGroup) view);
                videoContainer.addView(videoView);
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) videoView.getLayoutParams();
                lp.gravity = Gravity.CENTER;
                videoView.setLayoutParams(lp);

                videoView.setVideoPath(videoPathList.get(position));
                videoView.start();
                videoAdapter.setPlayingPosition(position);
            } else if (id == R.id.btn_fullscreen) {
                ViewGroup oldVideoContainer = videoContainer;
                if (oldVideoContainer != null) {
                    oldVideoContainer.removeView(videoView);
                }
                videoContainer = ((ViewGroup) view.getParent().getParent()).findViewById(R.id.video_container);
                videoContainer.addView(videoView);
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) videoView.getLayoutParams();
                lp.gravity = Gravity.CENTER;
                videoView.setLayoutParams(lp);

                mediaMetadataRetriever.setDataSource(videoPathList.get(position));

                videoView.setVideoPath(videoPathList.get(position));
                videoView.setVisibility(View.GONE);

                videoView.setOnClickListener(v -> {
                    if (videoView.isPlaying()) {
                        videoView.pause();
                    } else {
                        videoView.start();
                    }
                });

                videoView.setOnCompletionListener(mp -> videoView.start());

                setFullscreen(true);
                fullscreen = true;

                new Thread(() -> {
                    Bitmap firstFrame = mediaMetadataRetriever.getFrameAtTime(0);
                    runOnUiThread(() -> {
                        videoView.setVisibility(View.VISIBLE);
                        if (firstFrame.getHeight() > firstFrame.getWidth()) {
                            requestOrientationPortrait();
                        } else {
                            requestOrientationLandscape();
                        }
                    });
                }).start();
            }
        });
    }

    @Override
    public boolean handleBackPressedEvent() {
        if (!fullscreen) {
            return false;
        }

        setFullscreen(false);
        requestOrientationPortrait();

        return true;
    }

    @Override
    protected void onFullscreenStateChange(boolean fullscreen) {
        this.fullscreen = fullscreen;
        if (fullscreen) {
            fullscreenContainer.setVisibility(View.VISIBLE);
            videoContainer.removeView(videoView);
            fullscreenContainer.addView(videoView);
            videoView.start();
            videoRv.setVisibility(View.GONE);
        } else {
            fullscreenContainer.setVisibility(View.GONE);
            fullscreenContainer.removeView(videoView);
            videoView.start();
            videoRv.setVisibility(View.VISIBLE);
            videoContainer.addView(videoView, 0);
        }
    }

    @Override
    protected void onOrientationChange(int newOrientation) {
        if (isOrientationPortrait()) {
            if (fullscreen) {
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) videoView.getLayoutParams();
                lp.width = DisplayUtil.getPortraitScreenWidth(activity);
                lp.height = DisplayUtil.getPortraitScreenHeight(activity);
                lp.gravity = Gravity.CENTER;
                videoView.setLayoutParams(lp);
            } else {
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) videoView.getLayoutParams();
                lp.width = DisplayUtil.getPortraitScreenWidth(activity);
                lp.height = lp.width * 3 / 4;
                lp.gravity = Gravity.CENTER;
                videoView.setLayoutParams(lp);
            }
        } else {
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) videoView.getLayoutParams();
            lp.height = DisplayUtil.getPortraitScreenWidth(activity);
            lp.width = DisplayUtil.getPortraitScreenHeight(activity);
            lp.gravity = Gravity.CENTER;
            videoView.setLayoutParams(lp);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fullscreen_video;
    }

    private class VideoAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
        private int playingPosition = -1;
        private MediaMetadataRetriever mmr = new MediaMetadataRetriever();



        public VideoAdapter(@Nullable List<String> data) {
            super(R.layout.item_video, data);
        }

        public void setPlayingPosition(int playingPosition) {
            int oldPlayingPosition = this.playingPosition;
            this.playingPosition = playingPosition;

            if (oldPlayingPosition >= 0) {
                notifyItemChanged(oldPlayingPosition);
            }
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            ImageView frameIv = helper.getView(R.id.iv_frame);
            frameIv.setImageBitmap(null);
            frameIv.setTag(item);
            if (playingPosition == helper.getAdapterPosition()) {
                frameIv.setVisibility(View.GONE);
            } else {
                new Thread(() -> {
                    mmr.setDataSource(item);
                    Bitmap firstFrame = mmr.getFrameAtTime(0);
                    runOnUiThread(() -> {
                        frameIv.setVisibility(View.VISIBLE);
                        if (item == helper.getView(R.id.iv_frame).getTag()) {
                            helper.setImageBitmap(R.id.iv_frame, firstFrame);
                        } else {
                            helper.setImageBitmap(R.id.iv_frame, null);
                        }
                    });
                }).start();
            }

            helper.setText(R.id.tv_info, new File(item).getName());

            FrameLayout videoContainer = helper.getView(R.id.video_container);

            ViewGroup.LayoutParams lp = videoContainer.getLayoutParams();
            lp.width = DisplayUtil.getPortraitScreenWidth(activity);
            lp.height = lp.width * 3 / 4;
            videoContainer.setLayoutParams(lp);

            lp = frameIv.getLayoutParams();
            lp.width = DisplayUtil.getPortraitScreenWidth(activity);
            lp.height = lp.width * 3 / 4;
            frameIv.setLayoutParams(lp);

            helper.addOnClickListener(R.id.video_container).addOnClickListener(R.id.btn_fullscreen);
        }
    }
}
