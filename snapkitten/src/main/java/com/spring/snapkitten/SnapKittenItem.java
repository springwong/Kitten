package com.spring.snapkitten;

import android.view.View;

import com.spring.snapkitten.enums.SnapKittenAlignment;

/**
 * Created by spring on 26/2/2017.
 */

class SnapKittenItem {
    View view;
    int top = 0;
    int bottom = 0;
    int left = 0;
    int right = 0;
    SnapKittenAlignment alignment;
    public SnapKittenItem(View child, SnapKittenAlignment alignment){
        this.view = child;
        this.alignment = alignment;
    }

    public void setEdge(int value){
        this.top = value;
        this.left = value;
        this.right = value;
        this.bottom = value;
    }
}
