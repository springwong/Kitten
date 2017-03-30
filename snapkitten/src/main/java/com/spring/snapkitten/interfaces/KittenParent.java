package com.spring.snapkitten.interfaces;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by spring on 26/2/2017.
 */

public interface KittenParent extends KittenBuild {
    KittenParentMethods from(ViewGroup parent);
    KittenParentMethods from(Context context);
    KittenParentMethods from(LinearLayout parent);
}
