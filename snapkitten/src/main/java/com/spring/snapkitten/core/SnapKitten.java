package com.spring.snapkitten.core;

import android.content.Context;

import com.spring.snapkitten.interfaces.KittenSizeConversion;

/**
 * Created by spring on 12/3/2017.
 */

public final class SnapKitten {
//    private static Context context;
    private static KittenSizeConversion sizeConversion;

//    public static void initialize(Context context){
//        SnapKitten.context = context;
//    }
    public static void setup(KittenSizeConversion sizeConvertion){
        SnapKitten.sizeConversion = sizeConvertion;
    }

//    public static Context getContext() {
//        return context;
//    }

    public static KittenSizeConversion getSizeConversion() {
        return sizeConversion;
    }
}
