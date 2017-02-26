package com.spring.snapkitten;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.spring.snapkitten.enums.KittenCompareEnum;
import com.spring.snapkitten.enums.SnapKittenAlignment;
import com.spring.snapkitten.enums.SnapKittenOrientation;
import com.spring.snapkitten.interfaces.SnapKittenBuild;
import com.spring.snapkitten.interfaces.SnapKittenChild;
import com.spring.snapkitten.interfaces.SnapKittenChildMethods;
import com.spring.snapkitten.interfaces.SnapKittenParent;
import com.spring.snapkitten.interfaces.SnapKittenParentMethods;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by spring on 26/2/2017.
 */

public final class SnapKitten implements SnapKittenChildMethods, SnapKittenChild, SnapKittenBuild, SnapKittenParent, SnapKittenParentMethods {
    Context context;

    LinearLayout container;
    ViewGroup parent;
    SnapKittenOrientation orientation;

    List<SnapKittenItem> childs = new ArrayList<>();
    SnapKittenItem currentChild;


    protected SnapKittenAlignment defaultAlignment = SnapKittenAlignment.start;
    protected boolean isAlignParentEnd = false;

    protected int defaultTop = 0;
    protected int defaultLeft = 0;
    protected int defaultRight = 0;
    protected int defaultBottom = 0;

    public SnapKitten(Context context, SnapKittenOrientation orientation){
        this.context = context;
        this.orientation = orientation;
        container = new LinearLayout(context);
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

    @Override
    public SnapKittenChildMethods compressResistance(int priority) {
        currentChild.compressionResistancePriority = priority;
        if(currentChild.compressionResistancePriority > 1000){
            currentChild.compressionResistancePriority = 1000;
        }
        if(currentChild.compressionResistancePriority <= 0){
            currentChild.compressionResistancePriority = 1;
        }
        return this;
    }

    @Override
    public SnapKittenChildMethods width(Integer value, KittenCompareEnum condition) {
        currentChild.width = new KittenCondition(value, condition);
        return this;
    }

    @Override
    public SnapKittenChildMethods height(Integer value, KittenCompareEnum condition) {
        currentChild.height = new KittenCondition(value, condition);
        return this;
    }

    public LinearLayout build(){
        if(orientation == SnapKittenOrientation.vertical){
            return verticalBuild();
        }
        if(orientation == SnapKittenOrientation.horizontal){
            return horizontalBuild();
        }
        return container;
    }
    private LinearLayout verticalBuild(){
        container.setOrientation(LinearLayout.VERTICAL);
        container.setBackgroundColor(context.getResources().getColor(android.R.color.holo_green_light));
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, isAlignParentEnd ? ViewGroup.LayoutParams.MATCH_PARENT : ViewGroup.LayoutParams.WRAP_CONTENT);
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
                    linearLayoutParams.gravity = Gravity.RIGHT;
                    break;
            }
            linearLayoutParams.topMargin = child.top;
            //to sync with ios side, vertical arrange with ignore end margin except last item
            linearLayoutParams.leftMargin = child.left;
            linearLayoutParams.rightMargin = child.right;
            if(childs.indexOf(child) == childs.size() - 1){
                //last child
                linearLayoutParams.bottomMargin = child.bottom;
            }
            linearLayoutParams.weight = 1000 - child.compressionResistancePriority;
            child.view.setLayoutParams(linearLayoutParams);
        }
        if(parent != null)
            parent.addView(container);
        return container;
    }
    private LinearLayout horizontalBuild(){
        container.setOrientation(LinearLayout.HORIZONTAL);
        container.setBackgroundColor(context.getResources().getColor(android.R.color.holo_green_light));
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(isAlignParentEnd ? ViewGroup.LayoutParams.MATCH_PARENT : ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        container.setLayoutParams(params);
        for(SnapKittenItem child : childs){
            container.addView(child.view);
            LinearLayout.LayoutParams linearLayoutParams
                    = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, child.alignment == SnapKittenAlignment.parent ? ViewGroup.LayoutParams.MATCH_PARENT : ViewGroup.LayoutParams.WRAP_CONTENT);
            switch (child.alignment){
                case start:
                    linearLayoutParams.gravity = Gravity.TOP;
                    break;
                case center:
                    linearLayoutParams.gravity = Gravity.CENTER_VERTICAL;
                    break;
                case end:
                    linearLayoutParams.gravity = Gravity.BOTTOM;
                    break;
            }
            if (child.width != null && child.width.value != null){
                Integer value = child.width.value;
                switch (child.width.condition){
                    case equal:
                        linearLayoutParams.width = value;
                        break;
                    case min:
                        child.view.setMinimumWidth(value);
                        break;
                    case max:
                        setMaxWidth(child.view, value);
                        break;
                }
            }
            linearLayoutParams.topMargin = child.top;
            //to sync with ios side, vertical arrange with ignore end margin except last item
            linearLayoutParams.leftMargin = child.left;
            linearLayoutParams.bottomMargin = child.bottom;
            if(childs.indexOf(child) == childs.size() - 1){
                //last child
                linearLayoutParams.rightMargin = child.right;
            }
            linearLayoutParams.weight = 1000 - child.compressionResistancePriority;
            child.view.setLayoutParams(linearLayoutParams);
        }
        if (parent != null)
            parent.addView(container);
        return container;
    }

    void setMaxWidth(View view, int value){
        try{
            Method method = view.getClass().getMethod("setMaxWidth", int.class);
            method.invoke(view, value);
        }catch (NoSuchMethodException ex){

        }catch (IllegalAccessException ex){

        }catch (InvocationTargetException ex){

        }

    }
}
