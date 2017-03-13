package com.spring.snapkitten.core;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.spring.snapkitten.enums.CubAction;
import com.spring.snapkitten.enums.KittenSign;
import com.spring.snapkitten.interfaces.CubChild;
import com.spring.snapkitten.interfaces.CubChildMethods;
import com.spring.snapkitten.interfaces.CubParent;
import com.spring.snapkitten.interfaces.CubParentMethods;
import com.spring.snapkitten.interfaces.KittenSizeConversion;
import com.spring.snapkitten.subclass.CubRelativeAction;
import com.spring.snapkitten.subclass.KittenCondition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by spring on 12/3/2017.
 */

public final class Cub implements CubParent, CubParentMethods, CubChild, CubChildMethods {
    private KittenSizeConversion sizeConversion;
    RelativeLayout container;
    ViewGroup parent;

    List<CubItem> childs = new ArrayList<>();
    CubItem currentChild;

    int defaultTopOffset = 0;
    int defaultLeftOffset = 0;
    int defaultRightOffset = 0;
    int defaultBottomOffset = 0;

    public static CubParent create(){
        return new Cub(SnapKitten.getContext(), SnapKitten.getSizeConversion());
    }
    private Cub(Context context, KittenSizeConversion conversion){
        this.sizeConversion = conversion;
        container = new RelativeLayout(context);
    }

    @Override
    public CubParentMethods from(ViewGroup parent) {
        this.parent = parent;
        return this;
    }

    @Override
    public CubParentMethods from() {
        return this;
    }

    @Override
    public CubParentMethods from(RelativeLayout parent) {
        container = parent;
        return this;
    }

    @Override
    public CubParentMethods defaultOffset(int offset) {
        defaultTopOffset = offset;
        defaultLeftOffset = offset;
        defaultBottomOffset = offset;
        defaultRightOffset = offset;
        return this;
    }

    @Override
    public CubChildMethods add(View view) {
        CubItem newChild = new CubItem(view);
        newChild.topOffset = defaultTopOffset;
        newChild.bottomOffset = defaultBottomOffset;
        newChild.leftOffset = defaultLeftOffset;
        newChild.rightOffset = defaultRightOffset;
        childs.add(newChild);
        currentChild = newChild;
        return this;
    }


    @Override
    public CubChildMethods with(View view) {
        for(CubItem child : childs){
            if(child.view.equals(view)){
                currentChild = child;
            }
        }
        Log.w("Kitten", "Kitten Cub Object with(child) : " + view.toString()  + " is not exist, current editing target remains");
        return this;
    }

    @Override
    public CubChildMethods alignParentTop() {
        currentChild.topAction = new CubRelativeAction(null, CubAction.alignParentTop);
        return this;
    }

    @Override
    public CubChildMethods alignParentLeft() {
        currentChild.leftAction = new CubRelativeAction(null, CubAction.alignParentLeft);
        return this;
    }

    //note : there is a known difference in ios and android in alignParentRight
    //if the width is unknown, android side will try to fit the item the most right of container / parent
    //ios side will just align to right most item in the container
    @Override
    public CubChildMethods alignParentRight() {
        currentChild.rightAction = new CubRelativeAction(null, CubAction.alignParentRight);
        return this;
    }

    @Override
    public CubChildMethods alignParentBottom() {
        currentChild.bottomAction = new CubRelativeAction(null, CubAction.alignParentBottom);
        return this;
    }

    @Override
    public CubChildMethods alignLeft(View target) {
        currentChild.leftAction = new CubRelativeAction(target, CubAction.alignLeft);
        return this;
    }

    @Override
    public CubChildMethods alignTop(View target) {
        currentChild.topAction = new CubRelativeAction(target, CubAction.alignTop);
        return this;
    }

    @Override
    public CubChildMethods alignRight(View target) {
        currentChild.rightAction = new CubRelativeAction(target, CubAction.alignRight);
        return this;
    }

    @Override
    public CubChildMethods alignBottom(View target) {
        currentChild.bottomAction = new CubRelativeAction(target, CubAction.alignBottom);
        return this;
    }

    @Override
    public CubChildMethods above(View target) {
        currentChild.bottomAction = new CubRelativeAction(target, CubAction.above);
        return this;
    }

    @Override
    public CubChildMethods below(View target) {
        currentChild.topAction = new CubRelativeAction(target, CubAction.below);
        return this;
    }

    @Override
    public CubChildMethods leftOf(View target) {
        currentChild.rightAction = new CubRelativeAction(target, CubAction.rightOf);
        return this;
    }

    @Override
    public CubChildMethods rightOf(View target) {
        currentChild.leftAction = new CubRelativeAction(target, CubAction.rightOf);
        return this;
    }

    @Override
    public CubChildMethods center(boolean isCenter) {
        currentChild.isCenter = isCenter;
        return this;
    }

    @Override
    public CubChildMethods centerX(boolean isCenter) {
        currentChild.isCenterX = isCenter;
        return this;
    }

    @Override
    public CubChildMethods centerY(boolean isCenter) {
        currentChild.isCenterY = isCenter;
        return this;
    }

    @Override
    public CubChildMethods width(Integer value, KittenSign condition) {
        currentChild.width = new KittenCondition(value, condition);
        if(sizeConversion != null){
            currentChild.width.value = sizeConversion.sizeConvert(value);
        }
        return this;
    }

    @Override
    public CubChildMethods height(Integer value, KittenSign condition) {
        currentChild.height = new KittenCondition(value, condition);
        if(sizeConversion != null){
            currentChild.height.value = sizeConversion.sizeConvert(value);
        }
        return this;
    }

    @Override
    public CubChildMethods size(Integer value, KittenSign condition) {
        currentChild.width = new KittenCondition(value, condition);
        currentChild.height = new KittenCondition(value, condition);
        if(sizeConversion != null){
            currentChild.width.value = sizeConversion.sizeConvert(value);
            currentChild.height.value = sizeConversion.sizeConvert(value);
        }
        return this;
    }

    @Override
    public CubChildMethods offset(int value) {
        currentChild.topOffset = value;
        currentChild.leftOffset = value;
        currentChild.bottomOffset = value;
        currentChild.rightOffset = value;
        return this;
    }

    @Override
    public CubChildMethods leftOffset(int value) {
        currentChild.leftOffset = value;
        return this;
    }

    @Override
    public CubChildMethods rightOffset(int value) {
        currentChild.rightOffset = value;
        return this;
    }

    @Override
    public CubChildMethods topOffset(int value) {
        currentChild.topOffset = value;
        return this;
    }

    @Override
    public CubChildMethods bottomOffset(int value) {
        currentChild.bottomOffset = value;
        return this;
    }

    @Override
    public View build() {
        for(CubItem child : childs){
            Log.d("Test", "What's their id ? " + child.view.getId());
            if (child.view.getId() == -1) {
                child.view.setId(View.generateViewId());
            }
            container.addView(child.view);
        }
        ViewGroup.LayoutParams containerParams = container.getLayoutParams();
        if(containerParams == null){
            containerParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        if(parent!=null){
            if(parent.getLayoutParams().width != ViewGroup.LayoutParams.WRAP_CONTENT){
                containerParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            }
            if(parent.getLayoutParams().height != ViewGroup.LayoutParams.WRAP_CONTENT){
                containerParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            }
        }
        for(CubItem child : childs) {
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            if(child.isCenter) {
                params.addRule(RelativeLayout.CENTER_IN_PARENT);
            }
            if(child.isCenterX) {
                params.addRule(RelativeLayout.CENTER_HORIZONTAL);
            }
            if(child.isCenterY) {
                params.addRule(RelativeLayout.CENTER_VERTICAL);
            }
            if(child.topAction != null) {
                CubRelativeAction action = child.topAction;
                if(action.action == CubAction.alignParentTop){
                    params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                }else if(action.action == CubAction.alignTop){
                    params.addRule(RelativeLayout.ALIGN_TOP, action.target.getId());
                }else if(action.action == CubAction.below) {
                    params.addRule(RelativeLayout.BELOW, action.target.getId());
                }
            }
            if(child.bottomAction != null){
                CubRelativeAction action = child.bottomAction;
                if(action.action == CubAction.alignParentBottom){
                    params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                }else if(action.action == CubAction.alignBottom){
                    params.addRule(RelativeLayout.ALIGN_BOTTOM, action.target.getId());
                }else if(action.action == CubAction.above) {
                    params.addRule(RelativeLayout.ABOVE, action.target.getId());
                }
            }
            if(child.leftAction != null){
                CubRelativeAction action = child.leftAction;
                if(action.action == CubAction.alignParentLeft){
                    params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                }else if(action.action == CubAction.alignLeft){
                    params.addRule(RelativeLayout.ALIGN_LEFT, action.target.getId());
                }else if(action.action == CubAction.rightOf) {
                    params.addRule(RelativeLayout.RIGHT_OF, action.target.getId());
                }
            }
            if(child.rightAction != null){
                CubRelativeAction action = child.rightAction;
                if(action.action == CubAction.alignParentRight){
                    params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                }else if(action.action == CubAction.alignRight){
                    params.addRule(RelativeLayout.ALIGN_RIGHT, action.target.getId());
                }else if(action.action == CubAction.leftOf) {
                    params.addRule(RelativeLayout.LEFT_OF, action.target.getId());
                }
            }
            params.leftMargin = child.leftOffset;
            params.rightMargin = child.rightOffset;
            params.topMargin = child.topOffset;
            params.bottomMargin = child.bottomOffset;
            KittenCommonMethod.setupWidth(child.view, child.width, params);
            KittenCommonMethod.setupHeight(child.view, child.height, params);
            child.view.setLayoutParams(params);
         }
        container.setLayoutParams(containerParams);
        if(parent != null && container.getParent() == null)
            parent.addView(container);
        //always return generated container
        return container;
    }

    @Override
    public View rebuild() {
        return null;
    }
}