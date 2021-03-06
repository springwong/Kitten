package com.spring.snapkitten.interfaces;

import android.view.View;

import com.spring.snapkitten.enums.KittenSign;

/**
 * Created by spring on 12/3/2017.
 */

public interface CubChildMethods extends CubChild, CubBuild {
    CubChildMethods alignParentTop();
    CubChildMethods alignParentLeft();
    CubChildMethods alignParentRight();
    CubChildMethods alignParentBottom();

    CubChildMethods alignLeft(View target);
    CubChildMethods alignTop(View target);
    CubChildMethods alignRight(View target);
    CubChildMethods alignBottom(View target);

    CubChildMethods above(View target);
    CubChildMethods below(View target);
    CubChildMethods leftOf(View target);
    CubChildMethods rightOf(View target);

    CubChildMethods center(boolean isCenter);
    CubChildMethods centerX(boolean isCenter);
    CubChildMethods centerY(boolean isCenter);

    CubChildMethods width(Integer value, KittenSign condition);
    CubChildMethods height(Integer value, KittenSign condition);
    CubChildMethods size(Integer value, KittenSign condition);

    CubChildMethods offset(int value);
    CubChildMethods leftOffset(int value);
    CubChildMethods rightOffset(int value);
    CubChildMethods topOffset(int value);
    CubChildMethods bottomOffset(int value);

}
