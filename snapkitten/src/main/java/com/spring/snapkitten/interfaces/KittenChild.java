package com.spring.snapkitten.interfaces;

import android.view.View;

import java.util.List;

/**
 * Created by spring on 26/2/2017.
 */
public interface KittenChild extends KittenBuild {
    KittenChildMethods add(View view);
    KittenChildMethods with(View view);
    KittenChildMethods addChilds(View... views);
    KittenChildMethods addChilds(List<View> views);
}
