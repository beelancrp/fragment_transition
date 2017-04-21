package com.beelancrp.trans;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by artemkutukov on 30.03.17.
 */

public class RatePagerAdapter extends PagerAdapter {

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        Context context = container.getContext();
        final View view = View.inflate(context, R.layout.item_pager, null);

        final ImageView imageView = (ImageView) view.findViewById(R.id.image);
        final ImageView imageViewM = (ImageView) view.findViewById(R.id.mask);

        Glide.with(context)
                .load("https://pbs.twimg.com/profile_images/2108427522/fgsdfg.jpg")
                .centerCrop()
                .into(imageView);

        final TextView textViewAge = (TextView) view.findViewById(R.id.text);
        textViewAge.setText("years old");
        container.addView(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<View> views = new ArrayList<>();
                imageView.setTransitionName("imageTest" + position);
                imageViewM.setTransitionName("imageMask" + position);
                textViewAge.setTransitionName("textTest" + position);
                views.add(imageView);
                views.add(imageViewM);
                views.add(textViewAge);
                Bundle args = new Bundle();
                args.putString("image", imageView.getTransitionName());
                args.putString("mask", imageViewM.getTransitionName());
                args.putString("text", textViewAge.getTransitionName());
                listener.onItemClick(args, views);
            }
        });

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }



    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public int getCount() {
        return 5;
    }

    public static class FadePageTransformer implements ViewPager.PageTransformer {

        public void transformPage(View view, float position) {
            if (position < -1 || position > 1) {
                view.setAlpha(0);
            } else if (position <= 0 || position <= 1) {
                // Calculate alpha. Position is decimal in [-1,0] or [0,1]
                float alpha = (position <= 0) ? position + 1 : 1 - position;
                view.setAlpha(alpha + 0.2f);
            } else if (position == 0) {
                view.setAlpha(1);
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Bundle args, List<View> views);
    }
}
