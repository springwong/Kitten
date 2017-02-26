package com.spring.snapkitten;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by spring on 26/2/2017.
 */

public final class SnapKitten {
    Context context;
    ViewGroup parent;
    List<View> childs = new ArrayList<>();
    View currentChild;


    protected SnapKittenAlignment defaultAlignment = SnapKittenAlignment.center;
    protected boolean isAlignParentEnd = false;

    public SnapKitten(Context context){
        this.context = context;
    }
    public SnapKitten from(ViewGroup parent){
        this.parent = parent;
        return this;
    }
    public SnapKitten add(View child){
        childs.add(child);
        currentChild = child;
        return this;
    }
    public void build(){
        LinearLayout container = new LinearLayout(context);
        container.setOrientation(LinearLayout.VERTICAL);
        container.setBackgroundColor(context.getResources().getColor(android.R.color.holo_green_light));
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, isAlignParentEnd ? ViewGroup.LayoutParams.MATCH_PARENT : ViewGroup.LayoutParams.WRAP_CONTENT);
        container.setLayoutParams(params);
        for(View child : childs){
            container.addView(child);
            LinearLayout.LayoutParams linearLayoutParams
                    = new LinearLayout.LayoutParams(defaultAlignment == SnapKittenAlignment.parent ? ViewGroup.LayoutParams.MATCH_PARENT : ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            switch (defaultAlignment){
                case start:
                    linearLayoutParams.gravity = Gravity.LEFT;
                    break;
                case center:
                    linearLayoutParams.gravity = Gravity.CENTER_HORIZONTAL;
                    break;
                case end:
                    linearLayoutParams.gravity = Gravity.END;
                    break;
            }
            linearLayoutParams.topMargin = 10;
            child.setLayoutParams(linearLayoutParams);
        }
        parent.addView(container);
    }
}
