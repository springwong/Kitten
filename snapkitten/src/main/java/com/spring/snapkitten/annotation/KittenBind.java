package com.spring.snapkitten.annotation;

import android.content.Context;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by spring on 28/2/2017.
 */

public class KittenBind {
    public static void bind(Object object, Context context){
        Field[] fields = object.getClass().getDeclaredFields();
        for(Field field : fields){
            BindContext bindContext = field.getAnnotation(BindContext.class);
            if(bindContext != null){
                Class tClass = field.getType();
                if (View.class.isAssignableFrom(tClass)){
                    try{
                        Constructor constructor = tClass.getConstructor(Context.class);
                        field.set(object, constructor.newInstance(context));
                    }catch (NoSuchMethodException ex){
                        ex.printStackTrace();
                    }catch (InvocationTargetException ex){
                        ex.printStackTrace();
                    }catch (IllegalAccessException ex){
                        ex.printStackTrace();
                    }catch (InstantiationException ex){
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

}
