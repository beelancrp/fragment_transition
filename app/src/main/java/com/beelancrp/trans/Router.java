package com.beelancrp.trans;

import android.support.v4.app.Fragment;
import android.view.View;

import java.util.List;

/**
 * Created by Illya on 20.04.2017.
 */

public interface Router {
    void changeFragment(Fragment frg, List<View> views);
}
