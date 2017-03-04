package com.spring.snapkittenexample;

import android.databinding.ObservableField;
import android.util.Log;

import java.util.Observable;

/**
 * Created by spring on 3/3/2017.
 */

public class PlainModel extends Observable{
    KittenObservableField<String> string = new KittenObservableField<>();
    public void test(){
        string.addChangeCallback(new KittenObservableCallback<String>() {
            @Override
            public void onValueChange(android.databinding.Observable observable, String value) {
                Log.d("test", value);
            }
        });
        string.set("123 iwehuewh eih wio");

    }
}
