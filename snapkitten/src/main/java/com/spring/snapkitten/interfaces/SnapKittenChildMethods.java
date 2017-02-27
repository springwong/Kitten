package com.spring.snapkitten.interfaces;

import com.spring.snapkitten.enums.KittenCompareEnum;
import com.spring.snapkitten.enums.KittenWeight;
import com.spring.snapkitten.enums.KittenAlignment;

/**
 * Created by spring on 26/2/2017.
 */
public interface SnapKittenChildMethods extends SnapKittenChild, SnapKittenBuild {
    SnapKittenChildMethods itemOffset(int value);
    SnapKittenChildMethods sideStartPadding(int value);
    SnapKittenChildMethods sideEndPadding(int value);
    SnapKittenChildMethods align(KittenAlignment align);
    SnapKittenChildMethods compressResistance(int priority);
    SnapKittenChildMethods width(Integer value, KittenCompareEnum condition);
    SnapKittenChildMethods height(Integer value, KittenCompareEnum condition);
    SnapKittenChildMethods size(Integer value, KittenCompareEnum condition);
    SnapKittenChildMethods condition(KittenInsertCondition condition);
    SnapKittenChildMethods fillParent();
    SnapKittenChildMethods weight(KittenWeight weight);
}
