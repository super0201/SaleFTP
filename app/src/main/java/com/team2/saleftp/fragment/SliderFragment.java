package com.team2.saleftp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created By JohnNguyen - Onesoft on 11/12/2018
 */
public class SliderFragment extends Fragment {
    private static final String IMAGE_URL = "image_url";

    public static SliderFragment newInstance(String imageUrl) {
        Bundle args = new Bundle();
        args.putString(IMAGE_URL, imageUrl);
        SliderFragment fragment = new SliderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ImageView view = (ImageView) inflater.inflate(R.layout.item_slider, container, false);
        String url = getUrlFromInstance();
        loadImage(view, url);
        return view;
    }

    /**
     * load image use glide
     *
     * @param view view to show image from url
     * @param url  url of image
     */
    private void loadImage(ImageView view, String url) {
        Glide.with(this).load(url).into(view);
    }

    /**
     * @return url of image passing from newInstance
     */
    private String getUrlFromInstance() {
        return getArguments().getString(IMAGE_URL);
    }
}
