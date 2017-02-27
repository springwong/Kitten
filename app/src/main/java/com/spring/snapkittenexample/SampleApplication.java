package com.spring.snapkittenexample;

import android.app.Application;

import com.spring.snapkitten.core.Kitten;

/**
 * Created by spring on 26/2/2017.
 */

public class SampleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Kitten.initialize(getApplicationContext());
    }
}
