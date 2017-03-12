package com.spring.snapkitten.interfaces;

import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by spring on 12/3/2017.
 */

public interface CubParent extends CubBuild {
    CubParentMethods from(ViewGroup parent);
    CubParentMethods from();
    CubParentMethods from(RelativeLayout parent);
}
