package com.spring.snapkitten.interfaces;

import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by spring on 26/2/2017.
 */

public interface KittenParent extends KittenBuild {
    KittenParentMethods from(ViewGroup parent);
    KittenParentMethods from();
    KittenParentMethods from(LinearLayout parent);
}
