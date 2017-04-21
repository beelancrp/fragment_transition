package com.beelancrp.trans;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

/**
 * Created by Illya on 19.04.2017.
 */

public class FragmentB extends Fragment {

    public static final String TAG = FragmentB.class.getSimpleName();

    public FragmentB() {
        // Required empty public constructor
    }

    public static FragmentB newInstance(Bundle args) {
        FragmentB fragment = new FragmentB();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postponeEnterTransition();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(R.transition.test));
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        String name = getArguments().getString("image");
        String name2 = getArguments().getString("text");
        String name3 = getArguments().getString("mask");

        View view = inflater.inflate(R.layout.fragment_b, container, false);

        view.findViewById(R.id.image).setTransitionName(name);
        view.findViewById(R.id.text).setTransitionName(name2);
        view.findViewById(R.id.mask).setTransitionName(name3);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((TextView)view.findViewById(R.id.text)).setText("asfasdf");

        Glide.with(this)
                .load("https://pbs.twimg.com/profile_images/2108427522/fgsdfg.jpg")
                .centerCrop()
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        startPostponedEnterTransition();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        startPostponedEnterTransition();
                        return false;
                    }
                })
                .dontAnimate()
                .into((ImageView) view.findViewById(R.id.image));
    }
}
