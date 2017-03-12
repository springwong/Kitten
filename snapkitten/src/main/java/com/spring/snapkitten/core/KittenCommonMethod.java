package com.spring.snapkitten.core;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.spring.snapkitten.subclass.KittenCondition;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by spring on 12/3/2017.
 */

public class KittenCommonMethod {
    private static boolean setSizeMethod(String methodName, View view, int value){
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
    private static void setMaxWidth(View view, int value){
        setSizeMethod("setMaxWidth", view, value);
    }
    private static void setMaxHeight(View view, int value){
        setSizeMethod("setMaxHeight", view, value);
    }
    private static void setMinWidth(View view, int value){
        if(!setSizeMethod("setMinWidth", view, value)){
            view.setMinimumWidth(value);
        }
    }
    private static void setMinHeight(View view, int value){
        if(setSizeMethod("setMinHeight", view, value)){
            view.setMinimumHeight(value);
        }
    }
    public static void setupWidth(View view, KittenCondition width, ViewGroup.LayoutParams linearLayoutParams) {
        if (width != null && width.value != null){
            Integer value = width.value;
            switch (width.condition){
                case equal:
                    linearLayoutParams.width = value;
                    break;
                case min:
                    setMinWidth(view, value);
                    break;
                case max:
                    setMaxWidth(view, value);
                    break;
            }
        }
    }
    public static void setupHeight(View view, KittenCondition height, ViewGroup.LayoutParams linearLayoutParams){
        if (height != null && height.value != null){
            Integer value = height.value;
            switch (height.condition){
                case equal:
                    linearLayoutParams.height = value;
                    break;
                case min:
                    setMinHeight(view, value);
                    break;
                case max:
                    setMaxHeight(view, value);
                    break;
            }
        }
    }
}
