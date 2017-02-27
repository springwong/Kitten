package com.spring.snapkitten.core;

import android.view.View;

import com.spring.snapkitten.subclass.KittenCondition;
import com.spring.snapkitten.enums.KittenPriority;
import com.spring.snapkitten.enums.KittenAlignment;
import com.spring.snapkitten.interfaces.KittenInsertCondition;

/**
 * Created by spring on 26/2/2017.
 */

class KittenItem {
    View view;

    int itemOffset = 0;
    int sideStartPadding = 0;
    int sideEndPadding = 0;

    int compressionResistancePriority = 1000;
    KittenPriority priority = KittenPriority.medium;

    KittenCondition width;
    KittenCondition height;
    KittenAlignment alignment;
    boolean isFillParent = false;
    KittenInsertCondition condition;

    public KittenItem(View child, KittenAlignment alignment) {
        this.view = child;
        this.alignment = alignment;
    }
}
