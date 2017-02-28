package com.spring.snapkitten.annotation;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by spring on 28/2/2017.
 */

public class KittenBind {
    public static void bind(Activity activity){
        bind(activity, activity.getApplicationContext());
    }
    public static void bind(Fragment fragment){
        bind(fragment, fragment.getActivity());
    }
    public static void bind(View view){
        bind(view, view.getContext());
    }
    public static void bind(android.support.v4.app.Fragment fragment){
        bind(fragment, fragment.getActivity());
    }
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
