package com.spring.snapkitten;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.spring.snapkitten.enums.SnapKittenAlignment;
import com.spring.snapkitten.enums.SnapKittenOrientation;
import com.spring.snapkitten.interfaces.SnapKittenBuild;
import com.spring.snapkitten.interfaces.SnapKittenChild;
import com.spring.snapkitten.interfaces.SnapKittenChildMethods;
import com.spring.snapkitten.interfaces.SnapKittenParent;
import com.spring.snapkitten.interfaces.SnapKittenParentMethods;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by spring on 26/2/2017.
 */

public final class SnapKitten implements SnapKittenChildMethods, SnapKittenChild, SnapKittenBuild, SnapKittenParent, SnapKittenParentMethods {
    Context context;

    ViewGroup parent;
    SnapKittenOrientation orientation;

    List<SnapKittenItem> childs = new ArrayList<>();
    SnapKittenItem currentChild;


    protected SnapKittenAlignment defaultAlignment = SnapKittenAlignment.center;
    protected boolean isAlignParentEnd = false;

    protected int defaultTop = 0;
    protected int defaultLeft = 0;
    protected int defaultRight = 0;
    protected int defaultBottom = 0;

    public SnapKitten(Context context, SnapKittenOrientation orientation){
        this.context = context;
        this.orientation = orientation;
    }
    public SnapKittenParentMethods from(ViewGroup parent){
        this.parent = parent;
        return this;
    }

    @Override
    public SnapKittenParentMethods defaultAlignment(SnapKittenAlignment alignment) {
        this.defaultAlignment = alignment;
        return this;
    }

    @Override
    public SnapKittenParentMethods defaultEdge(int top, int left, int bottom, int right) {
        this.defaultTop = top;
        this.defaultLeft = left;
        this.defaultBottom = bottom;
        this.defaultRight = right;
        return this;
    }

    @Override
    public SnapKittenParentMethods isAlignDirectionEnd(boolean isAlign) {
        this.isAlignParentEnd = isAlign;
        return this;
    }

    @Override
    public SnapKittenParentMethods orientation(SnapKittenOrientation orientation) {
        this.orientation = orientation;
        return this;
    }

    public SnapKittenChildMethods add(View child){
        SnapKittenItem item = new SnapKittenItem(child, defaultAlignment);
        item.top = defaultTop;
        item.left = defaultLeft;
        item.right = defaultRight;
        item.bottom = defaultBottom;
        childs.add(item);
        currentChild = item;
        return this;
    }

    @Override
    public SnapKittenChildMethods addSpace() {
        return null;
    }

    @Override
    public SnapKittenChildMethods with(View view) {
        return null;
    }

    @Override
    public SnapKittenChildMethods top(int value) {
        currentChild.top = value;
        return this;
    }

    @Override
    public SnapKittenChildMethods left(int value) {
        currentChild.left = value;
        return this;
    }

    @Override
    public SnapKittenChildMethods right(int value) {
        currentChild.right = value;
        return this;
    }

    @Override
    public SnapKittenChildMethods bottom(int value) {
        currentChild.bottom = value;
        return this;
    }

    @Override
    public SnapKittenChildMethods edge(int value) {
        currentChild.setEdge(value);
        return this;
    }

    @Override
    public SnapKittenChildMethods align(SnapKittenAlignment align) {
        currentChild.alignment = align;
        return this;
    }

    public LinearLayout build(){
        LinearLayout container = new LinearLayout(context);
        container.setOrientation(LinearLayout.VERTICAL);
        container.setBackgroundColor(context.getResources().getColor(android.R.color.holo_green_light));
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, isAlignParentEnd ? ViewGroup.LayoutParams.MATCH_PARENT : ViewGroup.LayoutParams.WRAP_CONTENT);
        container.setLayoutParams(params);
        for(SnapKittenItem child : childs){
            container.addView(child.view);
            LinearLayout.LayoutParams linearLayoutParams
                    = new LinearLayout.LayoutParams(child.alignment == SnapKittenAlignment.parent ? ViewGroup.LayoutParams.MATCH_PARENT : ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            switch (child.alignment){
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
            linearLayoutParams.topMargin = child.top;
            //to sync with ios side, vertical arrange with ignore bottom margin except last item
//            linearLayoutParams.bottomMargin = child.bottom;
            linearLayoutParams.leftMargin = child.left;
            linearLayoutParams.rightMargin = child.right;
            if(childs.indexOf(child) == childs.size() - 1){
                //last child
                linearLayoutParams.bottomMargin = child.bottom;
            }
            child.view.setLayoutParams(linearLayoutParams);
        }
        parent.addView(container);
        return container;
    }
}
