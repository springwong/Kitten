package com.spring.snapkittenexample;

import android.app.Application;
import android.util.TypedValue;

import com.spring.snapkitten.core.Kitten;
import com.spring.snapkitten.core.SnapKitten;
import com.spring.snapkitten.interfaces.KittenSizeConversion;

/**
 * Created by spring on 26/2/2017.
 */

public class SampleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SnapKitten.initialize(getApplicationContext());
        SnapKitten.setup(new KittenSizeConversion() {
            @Override
            public int paddingConvert(int input) {
                return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, input, getResources().getDisplayMetrics());
            }

            @Override
            public int sizeConvert(int input) {
                return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, input, getResources().getDisplayMetrics());
            }
        });
    }
}
