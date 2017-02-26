package com.spring.snapkitten;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.spring.snapkitten.enums.KittenCompareEnum;
import com.spring.snapkitten.enums.SnapKittenAlignment;
import com.spring.snapkitten.enums.SnapKittenOrientation;
import com.spring.snapkitten.interfaces.KittenInsertCondition;
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


    protected SnapKittenAlignment defaultAlignment = SnapKittenAlignment.parent;
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
    public SnapKittenChildMethods fillParent() {
        currentChild.isFillParent = true;
        return this;
    }

    @Override
    public SnapKittenChildMethods with(View view) {
        for(SnapKittenItem child : childs){
            if(child.view.equals(view)){
                currentChild = child;
            }
        }
        Log.w("SnapKitten", "Kitten Object with(child) : " + view.toString()  + " is not exist, current editing target remains");
        return this;
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

    @Override
    public SnapKittenChildMethods size(Integer value, KittenCompareEnum condition) {
        currentChild.width = new KittenCondition(value, condition);
        currentChild.height = new KittenCondition(value, condition);
        return this;
    }

    @Override
    public SnapKittenChildMethods condition(KittenInsertCondition condition) {
        currentChild.condition = condition;
        return this;
    }

    public LinearLayout build(){
        List<SnapKittenItem> filteredList = new ArrayList<>();
        for(SnapKittenItem child : childs){
            if(child.condition == null || child.condition.isInsert()){
                filteredList.add(child);
            }
        }
        if(orientation == SnapKittenOrientation.vertical){
            return verticalBuild(filteredList);
        }
        if(orientation == SnapKittenOrientation.horizontal){
            return  horizontalBuild(filteredList);
        }
        return container;
    }

    @Override
    public LinearLayout rebuild() {
        container.removeAllViews();
        return build();
    }

    private LinearLayout verticalBuild(List<SnapKittenItem> childs){
        container.setOrientation(LinearLayout.VERTICAL);
        ViewGroup.LayoutParams params = container.getLayoutParams();
        if(params == null){
            params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, isAlignParentEnd ? ViewGroup.LayoutParams.MATCH_PARENT : ViewGroup.LayoutParams.WRAP_CONTENT);
        }else{
            //should i set the params width height here?
            params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            params.height =  isAlignParentEnd ? ViewGroup.LayoutParams.MATCH_PARENT : ViewGroup.LayoutParams.WRAP_CONTENT;
        }
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
                case parent:
                    params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                case end:
                    linearLayoutParams.gravity = Gravity.RIGHT;
                    break;
            }
            setupSize(child, linearLayoutParams);
            linearLayoutParams.topMargin = child.top;
            //to sync with ios side, vertical arrange with ignore end margin except last item
            linearLayoutParams.leftMargin = child.left;
            linearLayoutParams.rightMargin = child.right;
            if(childs.indexOf(child) == childs.size() - 1){
                //last child
                linearLayoutParams.bottomMargin = child.bottom;
            }
            if (child.isFillParent){
                linearLayoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            }
            linearLayoutParams.weight = 1000 - child.compressionResistancePriority;
            child.view.setLayoutParams(linearLayoutParams);
        }
        container.setLayoutParams(params);
        if(parent != null && container.getParent() == null)
            parent.addView(container);
        return container;
    }
    private LinearLayout horizontalBuild(List<SnapKittenItem> childs){
        container.setOrientation(LinearLayout.HORIZONTAL);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(isAlignParentEnd ? ViewGroup.LayoutParams.MATCH_PARENT : ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        for(SnapKittenItem child : childs){
            container.addView(child.view);
            ViewGroup.LayoutParams layoutParams = child.view.getLayoutParams();
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
                case parent:
                    params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    break;
            }
            setupSize(child, linearLayoutParams);

            linearLayoutParams.topMargin = child.top;
            //to sync with ios side, vertical arrange with ignore end margin except last item
            linearLayoutParams.leftMargin = child.left;
            linearLayoutParams.bottomMargin = child.bottom;
            if(childs.indexOf(child) == childs.size() - 1){
                //last child
                linearLayoutParams.rightMargin = child.right;
            }
            if (child.isFillParent){
                linearLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            }
            linearLayoutParams.weight = 1000 - child.compressionResistancePriority;
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
    private  void setupSize(SnapKittenItem child, LinearLayout.LayoutParams linearLayoutParams){
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
