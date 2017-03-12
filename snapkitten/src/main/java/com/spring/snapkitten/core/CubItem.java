package com.spring.snapkitten.core;

import android.view.View;

import com.spring.snapkitten.subclass.CubRelativeAction;
import com.spring.snapkitten.subclass.KittenCondition;

/**
 * Created by spring on 12/3/2017.
 */

public class CubItem {
    View view;

    boolean isCenter = false;
    boolean isCenterX = false;
    boolean isCenterY = false;

    int topOffset = 0;
    int bottomOffset = 0;
    int leftOffset = 0;
    int rightOffset = 0;

    CubRelativeAction topAction;
    CubRelativeAction bottomAction;
    CubRelativeAction leftAction;
    CubRelativeAction rightAction;

    KittenCondition width;
    KittenCondition height;

    public CubItem(View view) {
        this.view = view;
    }
}
