package com.spring.snapkitten.interfaces;

import com.spring.snapkitten.enums.KittenSign;
import com.spring.snapkitten.enums.KittenPriority;
import com.spring.snapkitten.enums.KittenAlignment;

/**
 * Created by spring on 26/2/2017.
 */
public interface KittenChildMethods extends KittenChild, KittenBuild {
    KittenChildMethods itemOffset(int value);
    KittenChildMethods sideStartPadding(int value);
    KittenChildMethods sideEndPadding(int value);
    KittenChildMethods align(KittenAlignment align);
    KittenChildMethods width(Integer value, KittenSign condition);
    KittenChildMethods height(Integer value, KittenSign condition);
    KittenChildMethods size(Integer value, KittenSign condition);
    KittenChildMethods condition(KittenInsertCondition condition);
    KittenChildMethods fillParent();
    KittenChildMethods priority(KittenPriority priority);
    KittenChildMethods weight(int weight);
}
