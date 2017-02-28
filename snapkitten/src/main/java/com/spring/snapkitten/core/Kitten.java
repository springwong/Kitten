package com.spring.snapkitten.core;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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
    private static Context context;

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

    public static void initialize(Context context){
        Kitten.context = context;
    }

    public static Context getContext(){
        return context;
    }

    public static KittenParent create(KittenOrientation orientation){
        return new Kitten(orientation);
    }
    private Kitten(KittenOrientation orientation){
        this.orientation = orientation;
        container = new LinearLayout(context);
    }
    public KittenParentMethods from(ViewGroup parent){
        this.parent = parent;
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
        return this;
    }

    @Override
    public KittenParentMethods endPadding(int value) {
        this.endPadding = value;
        return this;
    }

    @Override
    public KittenParentMethods itemDefaultOffset(int value) {
        this.defaultItemOffset = value;
        return this;
    }

    @Override
    public KittenParentMethods itemDefaultSideStartPadding(int value) {
        this.defaultItemSideStartPadding = value;
        return this;
    }

    @Override
    public KittenParentMethods itemDefaultSideEndPadding(int value) {
        this.defaultItemSideEndPadding = value;
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
        return this;
    }

    @Override
    public KittenChildMethods sideStartPadding(int value) {
        currentChild.sideStartPadding = value;
        return this;
    }

    @Override
    public KittenChildMethods itemOffset(int value) {
        currentChild.itemOffset = value;
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
        return this;
    }

    @Override
    public KittenChildMethods height(Integer value, KittenCompare condition) {
        currentChild.height = new KittenCondition(value, condition);
        return this;
    }

    @Override
    public KittenChildMethods size(Integer value, KittenCompare condition) {
        currentChild.width = new KittenCondition(value, condition);
        currentChild.height = new KittenCondition(value, condition);
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
        if(orientation == KittenOrientation.vertical){
            return verticalBuild(filteredList);
        }
        if(orientation == KittenOrientation.horizontal){
            return  horizontalBuild(filteredList);
        }
        return container;
    }

    @Override
    public View rebuild() {
        container.removeAllViews();
        return build();
    }

    private View verticalBuild(List<KittenItem> childs){
        container.setOrientation(LinearLayout.VERTICAL);
        //what affect containerLength is it's parent, if parent is not wrap_content, follow it to expand container size
        int containerLength = ViewGroup.LayoutParams.WRAP_CONTENT;
        if(parent != null){
            if(parent.getLayoutParams().width != ViewGroup.LayoutParams.WRAP_CONTENT){
                containerLength = ViewGroup.LayoutParams.MATCH_PARENT;
            }
        }
        ViewGroup.LayoutParams params = container.getLayoutParams();
        if(params == null){
            //if align parent end, the length of container should be try to fit the while size of parent
            params = new ViewGroup.LayoutParams(containerLength, isAlignParentEnd ? ViewGroup.LayoutParams.MATCH_PARENT : ViewGroup.LayoutParams.WRAP_CONTENT);
        }else{
            //should i set the params width height here?
            //if it's update, update the width / height instead of apply params as some error may occur
            params.width = containerLength;
            params.height =  isAlignParentEnd ? ViewGroup.LayoutParams.MATCH_PARENT : ViewGroup.LayoutParams.WRAP_CONTENT;
        }
        for(KittenItem child : childs){
            container.addView(child.view);
            //perpendicular direction try to match_parent if align parent
            LinearLayout.LayoutParams linearLayoutParams
            = new LinearLayout.LayoutParams(child.alignment == KittenAlignment.parent ? ViewGroup.LayoutParams.MATCH_PARENT : ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            switch (child.alignment){
                //set gravity in linear layout params in different case
                case start:
                    linearLayoutParams.gravity = Gravity.LEFT;
                    break;
                case center:
                    linearLayoutParams.gravity = Gravity.CENTER_HORIZONTAL;
                    break;
                case parent:
                    params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                case end:
                    linearLayoutParams.gravity = Gravity.RIGHT;
                    break;
            }
            //setup perpendicular size
            setupWidth(child, linearLayoutParams);

            if(isWeightMode){
                //in weight mode , the height always 0 to make weight handle the rest
                linearLayoutParams.weight = child.weight;
                linearLayoutParams.height = 0;
            }else{
                //allow user to set height in non-weight mode
                setupHeight(child, linearLayoutParams);
                if (child.isFillParent){
                    //if fill parent , make its orientation match_parent, and the weight become lower than default
                    linearLayoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
                    linearLayoutParams.weight = 1;
                }else {
                    //for non fill parent case, the weight can be set with priority
                    //height priority make it always show if possible
                    //priority with low and medium always compress by high priority item
                    switch (child.priority){
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
            //set up offset and padding
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

            child.view.setLayoutParams(linearLayoutParams);
        }
        container.setLayoutParams(params);
        //if no parent, item return itself
        if(parent != null && container.getParent() == null)
            parent.addView(container);
        //always return generated container
        return container;
    }
    private View horizontalBuild(List<KittenItem> childs){
        container.setOrientation(LinearLayout.HORIZONTAL);
        int containerLength = ViewGroup.LayoutParams.WRAP_CONTENT;
        if(parent != null){
            if(parent.getLayoutParams().height != ViewGroup.LayoutParams.WRAP_CONTENT){
                containerLength = ViewGroup.LayoutParams.MATCH_PARENT;
            }
        }
        ViewGroup.LayoutParams params = container.getLayoutParams();
        if(params == null){
            //if align parent end, the length of container should be try to fit the while size of parent
            params = new ViewGroup.LayoutParams(isAlignParentEnd ? ViewGroup.LayoutParams.MATCH_PARENT : ViewGroup.LayoutParams.WRAP_CONTENT, containerLength);
        }else{
            //should i set the params width height here?
            //if it's update, update the width / height instead of apply params as some error may occur
            params.width = isAlignParentEnd ? ViewGroup.LayoutParams.MATCH_PARENT : ViewGroup.LayoutParams.WRAP_CONTENT;
            params.height =  containerLength;
        }
        for(KittenItem child : childs){
            container.addView(child.view);
            ViewGroup.LayoutParams layoutParams = child.view.getLayoutParams();
            LinearLayout.LayoutParams linearLayoutParams
                    = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, child.alignment == KittenAlignment.parent ? ViewGroup.LayoutParams.MATCH_PARENT : ViewGroup.LayoutParams.WRAP_CONTENT);
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
            setupHeight(child, linearLayoutParams);

            if(isWeightMode){
                linearLayoutParams.weight = child.weight;
                linearLayoutParams.width = 0;
            }else{
                setupWidth(child, linearLayoutParams);
                if (child.isFillParent) {
                    linearLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    linearLayoutParams.weight = 1;
                }else{
                    switch (child.priority){
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
            child.view.setLayoutParams(linearLayoutParams);
        }
        container.setLayoutParams(params);
        if (parent != null)
            parent.addView(container);
        return container;
    }

    private boolean setSizeMethod(String methodName, View view, int value){
        try{
            Method method = view.getClass().getMethod(methodName, int.class);
            method.invoke(view, value);
            return true;
        }catch (NoSuchMethodException ex){

        }catch (IllegalAccessException ex){

        }catch (InvocationTargetException ex){

        }
        return false;
    }
    private void setMaxWidth(View view, int value){
        setSizeMethod("setMaxWidth", view, value);
    }
    private void setMaxHeight(View view, int value){
        setSizeMethod("setMaxHeight", view, value);
    }
    private void setMinWidth(View view, int value){
        if(!setSizeMethod("setMinWidth", view, value)){
            view.setMinimumWidth(value);
        }
    }
    private void setMinHeight(View view, int value){
        if(setSizeMethod("setMinHeight", view, value)){
            view.setMinimumHeight(value);
        }
    }
    private void setupWidth(KittenItem child, LinearLayout.LayoutParams linearLayoutParams) {
        if (child.width != null && child.width.value != null){
            Integer value = child.width.value;
            switch (child.width.condition){
                case equal:
                    linearLayoutParams.width = value;
                    break;
                case min:
                    setMinWidth(child.view, value);
                    break;
                case max:
                    setMaxWidth(child.view, value);
                    break;
            }
        }
    }
    private void setupHeight(KittenItem child, LinearLayout.LayoutParams linearLayoutParams){
        if (child.height != null && child.height.value != null){
            Integer value = child.height.value;
            switch (child.height.condition){
                case equal:
                    linearLayoutParams.height = value;
                    break;
                case min:
                    setMinHeight(child.view, value);
                    break;
                case max:
                    setMaxHeight(child.view, value);
                    break;
            }
        }
    }
}
