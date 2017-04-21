package com.beelancrp.trans;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Router {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.foo, FragmentA.newInstance())
                .commit();
    }

    @Override
    public void changeFragment(Fragment frg, List<View> views) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            for (View entry : views) {
                if (entry != null) {
                    transaction.addSharedElement(entry, ViewCompat.getTransitionName(entry));
                }
            }
        }
        transaction
                .addToBackStack(null)
                .replace(R.id.foo, frg, frg.getClass().getSimpleName())
                .commit();
    }
}
