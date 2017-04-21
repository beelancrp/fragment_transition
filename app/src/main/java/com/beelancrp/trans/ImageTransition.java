package com.beelancrp.trans;

import android.annotation.TargetApi;
import android.os.Build;
import android.transition.ChangeClipBounds;
import android.transition.ChangeTransform;
import android.transition.TransitionSet;

/**
 * Created by Illya on 19.04.2017.
 */

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class ImageTransition extends TransitionSet {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ImageTransition() {
        setOrdering(ORDERING_TOGETHER);
        setDuration(4000);
        addTransition(new ChangeClipBounds());
        addTransition(new ChangeTransform());
    }
}
