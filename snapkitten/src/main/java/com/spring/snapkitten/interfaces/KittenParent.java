package com.spring.snapkitten.interfaces;

import android.view.ViewGroup;

/**
 * Created by spring on 26/2/2017.
 */

public interface KittenParent extends KittenBuild {
    KittenParentMethods from(ViewGroup parent);
    KittenParentMethods from();
}
