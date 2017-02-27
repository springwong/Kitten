package com.spring.snapkitten.interfaces;

import com.spring.snapkitten.enums.KittenCompareEnum;
import com.spring.snapkitten.enums.KittenWeight;
import com.spring.snapkitten.enums.KittenAlignment;

/**
 * Created by spring on 26/2/2017.
 */
public interface KittenChildMethods extends KittenChild, KittenBuild {
    KittenChildMethods itemOffset(int value);
    KittenChildMethods sideStartPadding(int value);
    KittenChildMethods sideEndPadding(int value);
    KittenChildMethods align(KittenAlignment align);
    KittenChildMethods compressResistance(int priority);
    KittenChildMethods width(Integer value, KittenCompareEnum condition);
    KittenChildMethods height(Integer value, KittenCompareEnum condition);
    KittenChildMethods size(Integer value, KittenCompareEnum condition);
    KittenChildMethods condition(KittenInsertCondition condition);
    KittenChildMethods fillParent();
    KittenChildMethods weight(KittenWeight weight);
}
