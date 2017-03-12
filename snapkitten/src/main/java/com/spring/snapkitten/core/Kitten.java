package com.spring.snapkitten.core;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.spring.snapkitten.interfaces.KittenSizeConversion;
import com.spring.snapkitten.subclass.KittenCondition;
import com.spring.snapkitten.enums.KittenCompare;
import com.spring.snapkitten.enums.KittenPriority;
import com.spring.snapkitten.enums.KittenAlignment;
import com.spring.snapkitten.enums.KittenOrientation;
import com.spring.snapkitten.interfaces.KittenChild;
import com.spring.snapkitten.interfaces.KittenChildMethods;
import com.spring.snapkitten.interfaces.KittenInsertCondition;
import com.spring.snapkitten.interfaces.KittenBuild;
import com.spring.snapkitten.interfaces.KittenParent;
import com.spring.snapkitten.interfaces.KittenParentMethods;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by spring on 26/2/2017.
 */

public final class Kitten implements KittenChildMethods, KittenChild, KittenBuild, KittenParent, KittenParentMethods {
//    private Context context;
    private KittenSizeConversion sizeConversion;

    LinearLayout container;
    ViewGroup parent;
    KittenOrientation orientation;
    protected int startPadding = 0;
    protected int endPadding = 0;
    protected boolean isWeightMode = false;

    List<KittenItem> childs = new ArrayList<>();
    KittenItem currentChild;

    protected KittenAlignment defaultAlignment = KittenAlignment.parent;
    protected boolean isAlignParentEnd = false;

    protected int defaultItemOffset = 0;
    protected int defaultItemSideStartPadding = 0;
    protected int defaultItemSideEndPadding = 0;

//    public static void initialize(Context context){
//        Kitten.context = context;
//    }
//    public static void setup(KittenSizeConversion sizeConvertion){
//        Kitten.sizeConversion = sizeConvertion;
//    }

//    public static Context getContext(){
//        return context;
//    }

    public static KittenParent create(KittenOrientation orientation){
        return new Kitten(orientation, SnapKitten.getContext(), SnapKitten.getSizeConversion());
    }
    private Kitten(KittenOrientation orientation, Context context, KittenSizeConversion conversion){
        this.orientation = orientation;
        this.sizeConversion = conversion;
        container = new LinearLayout(context);
    }
    public KittenParentMethods from(ViewGroup parent){
        this.parent = parent;
        return this;
    }

    @Override
    public KittenParentMethods from(LinearLayout parent) {
        container = parent;
        return this;
    }

    @Override
    public KittenParentMethods from() {
        return this;
    }

    @Override
    public KittenParentMethods defaultAlignment(KittenAlignment alignment) {
        this.defaultAlignment = alignment;
        return this;
    }

    @Override
    public KittenParentMethods startPadding(int value) {
        this.startPadding = value;
        if(sizeConversion !=null){
            this.startPadding = (sizeConversion.paddingConvert(value));
        }
        return this;
    }

    @Override
    public KittenParentMethods endPadding(int value) {
        this.endPadding = value;
        if(sizeConversion !=null){
            this.endPadding = (sizeConversion.paddingConvert(value));
        }
        return this;
    }

    @Override
    public KittenParentMethods itemDefaultOffset(int value) {
        this.defaultItemOffset = value;
        if(sizeConversion !=null){
            this.defaultItemOffset = (sizeConversion.paddingConvert(value));
        }
        return this;
    }

    @Override
    public KittenParentMethods itemDefaultSideStartPadding(int value) {
        this.defaultItemSideStartPadding = value;
        if(sizeConversion !=null){
            this.defaultItemSideStartPadding = (sizeConversion.paddingConvert(value));
        }
        return this;
    }

    @Override
    public KittenParentMethods itemDefaultSideEndPadding(int value) {
        this.defaultItemSideEndPadding = value;
        if(sizeConversion !=null){
            this.defaultItemSideEndPadding = (sizeConversion.paddingConvert(value));
        }
        return this;
    }

    @Override
    public KittenParentMethods isAlignDirectionEnd(boolean isAlign) {
        this.isAlignParentEnd = isAlign;
        return this;
    }

    @Override
    public KittenParentMethods orientation(KittenOrientation orientation) {
        this.orientation = orientation;
        return this;
    }

    @Override
    public KittenParentMethods weightMode(boolean isOn) {
        this.isWeightMode = isOn;
        return this;
    }

    public KittenChildMethods add(View child){
        KittenItem item = new KittenItem(child, defaultAlignment);
        item.itemOffset = defaultItemOffset;
        item.sideStartPadding = defaultItemSideStartPadding;
        item.sideEndPadding = defaultItemSideEndPadding;
        childs.add(item);
        currentChild = item;
        return this;
    }

    @Override
    public KittenChildMethods addChilds(View... views) {
        for(View view : views){
            add(view);
        }
        return this;
    }

    @Override
    public KittenChildMethods addChilds(List<View> views) {
        for(View view : views){
            add(view);
        }
        return this;
    }

    @Override
    public KittenChildMethods fillParent() {
        currentChild.isFillParent = true;
        return this;
    }

    @Override
    public KittenChildMethods with(View view) {
        for(KittenItem child : childs){
            if(child.view.equals(view)){
                currentChild = child;
            }
        }
        Log.w("Kitten", "Kitten Object with(child) : " + view.toString()  + " is not exist, current editing target remains");
        return this;
    }

    @Override
    public KittenChildMethods sideEndPadding(int value) {
        currentChild.sideEndPadding = value;
        if(sizeConversion !=null){
            currentChild.sideEndPadding = (sizeConversion.paddingConvert(value));
        }
        return this;
    }

    @Override
    public KittenChildMethods sideStartPadding(int value) {
        currentChild.sideStartPadding = value;
        if(sizeConversion !=null){
            currentChild.sideStartPadding = (sizeConversion.paddingConvert(value));
        }
        return this;
    }

    @Override
    public KittenChildMethods itemOffset(int value) {
        currentChild.itemOffset = value;
        if(sizeConversion !=null) {
            currentChild.itemOffset = (sizeConversion.paddingConvert(value));
        }
        return this;
    }

    @Override
    public KittenChildMethods align(KittenAlignment align) {
        currentChild.alignment = align;
        return this;
    }

    @Override
    public KittenChildMethods width(Integer value, KittenCompare condition) {
        currentChild.width = new KittenCondition(value, condition);
        if(sizeConversion != null){
            currentChild.width.value = sizeConversion.sizeConvert(value);
        }
        return this;
    }

    @Override
    public KittenChildMethods height(Integer value, KittenCompare condition) {
        currentChild.height = new KittenCondition(value, condition);
        if(sizeConversion != null){
            currentChild.height.value = sizeConversion.sizeConvert(value);
        }
        return this;
    }

    @Override
    public KittenChildMethods size(Integer value, KittenCompare condition) {
        currentChild.width = new KittenCondition(value, condition);
        currentChild.height = new KittenCondition(value, condition);
        if(sizeConversion != null){
            currentChild.width.value = sizeConversion.sizeConvert(value);
            currentChild.height.value = sizeConversion.sizeConvert(value);
        }
        return this;
    }

    @Override
    public KittenChildMethods condition(KittenInsertCondition condition) {
        currentChild.condition = condition;
        return this;
    }

    @Override
    public KittenChildMethods priority(KittenPriority priority) {
        currentChild.priority = priority;
        return this;
    }

    @Override
    public KittenChildMethods weight(int weight) {
        currentChild.weight = weight;
        return this;
    }

    public View build(){
        List<KittenItem> filteredList = new ArrayList<>();
        for(KittenItem child : childs){
            if(child.condition == null || child.condition.isInsert()){
                filteredList.add(child);
            }
        }
        mixBuild(filteredList);
        return container;
    }

    @Override
    public View rebuild() {
        container.removeAllViews();
        return build();
    }

    private View mixBuild(List<KittenItem> childs){
        if(orientation == KittenOrientation.vertical){
            container.setOrientation(LinearLayout.VERTICAL);
        }else{
            container.setOrientation(LinearLayout.HORIZONTAL);
        }

        int containerLength = ViewGroup.LayoutParams.WRAP_CONTENT;
        if(parent!=null){
            if (orientation == KittenOrientation.vertical){
                if(parent.getLayoutParams().width != ViewGroup.LayoutParams.WRAP_CONTENT){
                    containerLength = ViewGroup.LayoutParams.MATCH_PARENT;
                }
            }else{
                if(parent.getLayoutParams().height != ViewGroup.LayoutParams.WRAP_CONTENT){
                    containerLength = ViewGroup.LayoutParams.MATCH_PARENT;
                }
            }
        }
        ViewGroup.LayoutParams params = container.getLayoutParams();
        if (params == null){
            if(orientation == KittenOrientation.vertical){
                params = new ViewGroup.LayoutParams(containerLength, isAlignParentEnd ? ViewGroup.LayoutParams.MATCH_PARENT : ViewGroup.LayoutParams.WRAP_CONTENT);
            }else{
                params = new ViewGroup.LayoutParams(isAlignParentEnd ? ViewGroup.LayoutParams.MATCH_PARENT : ViewGroup.LayoutParams.WRAP_CONTENT, containerLength);
            }
        }else{
            if (orientation == KittenOrientation.vertical) {
                params.width = containerLength;
                params.height =  isAlignParentEnd ? ViewGroup.LayoutParams.MATCH_PARENT : ViewGroup.LayoutParams.WRAP_CONTENT;
            }else{
                params.width = isAlignParentEnd ? ViewGroup.LayoutParams.MATCH_PARENT : ViewGroup.LayoutParams.WRAP_CONTENT;
                params.height =  containerLength;
            }
        }
        for(KittenItem child : childs){
            container.addView(child.view);
            LinearLayout.LayoutParams linearLayoutParams;
            if(orientation == KittenOrientation.vertical){
                linearLayoutParams = new LinearLayout.LayoutParams(
                        child.alignment == KittenAlignment.parent ? ViewGroup.LayoutParams.MATCH_PARENT : ViewGroup.LayoutParams.WRAP_CONTENT
                        , ViewGroup.LayoutParams.WRAP_CONTENT);
                switch (child.alignment){
                    //set gravity in linear layout params in different case
                    case start:
                        linearLayoutParams.gravity = Gravity.LEFT;
                        break;
                    case center:
                        linearLayoutParams.gravity = Gravity.CENTER_HORIZONTAL;
                        break;
                    case end:
                        linearLayoutParams.gravity = Gravity.RIGHT;
                    case parent:
                        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                        break;
                }
                KittenCommonMethod.setupWidth(child.view, child.width, linearLayoutParams);
            }else{
                linearLayoutParams = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT
                        , child.alignment == KittenAlignment.parent ? ViewGroup.LayoutParams.MATCH_PARENT : ViewGroup.LayoutParams.WRAP_CONTENT);
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
                    case parent:
                        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
                        break;
                }
                KittenCommonMethod.setupHeight(child.view, child.height, linearLayoutParams);
            }
            if(isWeightMode){
                //in weight mode , the height always 0 to make weight handle the rest
                if(orientation == KittenOrientation.vertical) {
                    linearLayoutParams.height = 0;
                }else{
                    linearLayoutParams.width = 0;
                }
                linearLayoutParams.weight = child.weight;

            }else {
                if (orientation == KittenOrientation.vertical) {
                    KittenCommonMethod.setupHeight(child.view, child.height, linearLayoutParams);
                } else {
                    KittenCommonMethod.setupWidth(child.view, child.height, linearLayoutParams);
                }
                if (child.isFillParent) {
                    linearLayoutParams.weight = 1;
                    if (orientation == KittenOrientation.vertical) {
                        linearLayoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
                    } else {
                        linearLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    }
                } else {
                    switch (child.priority) {
                        case low:
                            //a high number related to medium and high
                            linearLayoutParams.weight = 1000;
                            break;
                        case medium:
                            linearLayoutParams.weight = 1;
                            break;
                        case high:
                            linearLayoutParams.weight = 0;
                            break;
                    }
                }
            }

            if(orientation == KittenOrientation.vertical){
                if(childs.indexOf(child) == 0){
                    //first child
                    linearLayoutParams.topMargin = startPadding;
                }else {
                    linearLayoutParams.topMargin = child.itemOffset;
                }
                //setup perpendicular margin
                //to sync with ios side, vertical arrange with ignore end margin except last item
                linearLayoutParams.leftMargin = child.sideStartPadding;
                linearLayoutParams.rightMargin = child.sideEndPadding;
                //setup end side padding
                if(childs.indexOf(child) == childs.size() - 1){
                    //last child
                    linearLayoutParams.bottomMargin = endPadding;
                }
            }else{
                if(childs.indexOf(child) == 0){
                    //first child
                    linearLayoutParams.leftMargin = startPadding;
                }else {
                    linearLayoutParams.leftMargin = child.itemOffset;
                }
                //to sync with ios side, vertical arrange with ignore end margin except last item
                linearLayoutParams.topMargin = child.sideStartPadding;
                linearLayoutParams.bottomMargin = child.sideEndPadding;
                if(childs.indexOf(child) == childs.size() - 1){
                    //last child
                    linearLayoutParams.rightMargin = endPadding;
                }
            }
            child.view.setLayoutParams(linearLayoutParams);
        }
        container.setLayoutParams(params);
        //if no parent, item return itself
        if(parent != null && container.getParent() == null)
            parent.addView(container);
        //always return generated container
        return container;
    }
}
