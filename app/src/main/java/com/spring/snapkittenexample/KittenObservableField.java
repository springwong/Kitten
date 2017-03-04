package com.spring.snapkittenexample;

import android.databinding.Observable;
import android.databinding.ObservableField;

/**
 * Created by spring on 3/3/2017.
 */

interface KittenObservableCallback<T>{
    void onValueChange(Observable observable, T value);
}
public class KittenObservableField<T> extends ObservableField<T> {
    public void addChangeCallback(final KittenObservableCallback<T> callback){
        this.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                if(callback!=null){
                    callback.onValueChange(observable, KittenObservableField.this.get());
                }
            }
        });
    }
}
