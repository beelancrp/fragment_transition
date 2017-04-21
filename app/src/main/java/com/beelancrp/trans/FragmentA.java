package com.beelancrp.trans;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Illya on 19.04.2017.
 */

public class FragmentA extends Fragment implements RatePagerAdapter.OnItemClickListener {

    public static final String TAG = FragmentA.class.getSimpleName();

    Router router;
    CustomSwipeLockViewPager mViewPager;

    public FragmentA() {
        // Required empty public constructor
    }

    public static FragmentA newInstance() {
        return new FragmentA();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setSharedElementReturnTransition(TransitionInflater.from(getContext()).inflateTransition(R.transition.test));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        router = (MainActivity) getActivity();
        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {

        mViewPager = (CustomSwipeLockViewPager) view.findViewById(R.id.viewPager);
        RatePagerAdapter adapter = new RatePagerAdapter();
        adapter.setListener(this);
        mViewPager.setPageMargin(-128);

        mViewPager.setAllowedSwipeDirection(CustomSwipeLockViewPager.SwipeDirection.right);
        mViewPager.setPageTransformer(false, new RatePagerAdapter.FadePageTransformer());
        mViewPager.setAdapter(adapter);

    }

    @Override
    public void onItemClick(Bundle args, List<View> views) {
        FragmentB frg = FragmentB.newInstance(args);
        router.changeFragment(frg, views);

    }
}
