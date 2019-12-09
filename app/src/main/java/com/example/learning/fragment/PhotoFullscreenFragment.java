package com.example.learning.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.learning.BaseFragment;
import com.example.learning.PhotoBean;
import com.example.learning.R;
import com.squareup.picasso.Picasso;

import static android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE;

/**
 * @Description:
 * @Author: zcx
 * @Copyright: 浙江集商优选电子商务有限公司
 * @CreateDate: 2019/12/3 16:17
 * @Version: 1.0.0
 */
public class PhotoFullscreenFragment extends BaseFragment {
    private PhotoBean photoBean;

    public static PhotoFullscreenFragment newInstance(PhotoBean photoBean) {
        PhotoFullscreenFragment fragment = new PhotoFullscreenFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("photoBean", photoBean);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();
        if (arguments != null) {
            photoBean = (PhotoBean) arguments.getSerializable("photoBean");
        }
    }

    @Override
    protected void intViews() {
        ImageView photoIv = findViewById(R.id.iv_photo);
        Glide.with(photoIv).load(photoBean.getThumUrl()).into(photoIv);
//        Glide.with(photoIv).load(photoBean.getDownloadUrl()).into(photoIv);
    }

    @Override
    public boolean fullScreen() {
        return true;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_photo_fullscreen;
    }
}
