package com.spring.snapkitten.interfaces;

import android.view.View;

/**
 * Created by spring on 26/2/2017.
 */
public interface KittenChild extends KittenBuild {
    KittenChildMethods add(View view);
    KittenChildMethods with(View view);
}
