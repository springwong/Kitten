package com.spring.snapkitten;

import android.view.View;

import com.spring.snapkitten.enums.KittenCompareEnum;
import com.spring.snapkitten.enums.KittenWeight;
import com.spring.snapkitten.enums.SnapKittenAlignment;
import com.spring.snapkitten.interfaces.KittenInsertCondition;

/**
 * Created by spring on 26/2/2017.
 */

class SnapKittenItem {
    View view;

    int itemOffset = 0;
    int sideStartPadding = 0;
    int sideEndPadding = 0;

    int compressionResistancePriority = 1000;
    KittenWeight weight = KittenWeight.medium;

    KittenCondition width;
    KittenCondition height;
    SnapKittenAlignment alignment;
    boolean isFillParent = false;
    KittenInsertCondition condition;

    public SnapKittenItem(View child, SnapKittenAlignment alignment) {
        this.view = child;
        this.alignment = alignment;
    }
}
