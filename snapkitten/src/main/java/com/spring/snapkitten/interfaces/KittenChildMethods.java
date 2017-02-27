package com.spring.snapkitten.interfaces;

import com.spring.snapkitten.enums.KittenCompare;
import com.spring.snapkitten.enums.KittenCompare;
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
    KittenChildMethods compressResistance(int priority);
    KittenChildMethods width(Integer value, KittenCompare condition);
    KittenChildMethods height(Integer value, KittenCompare condition);
    KittenChildMethods size(Integer value, KittenCompare condition);
    KittenChildMethods condition(KittenInsertCondition condition);
    KittenChildMethods fillParent();
    KittenChildMethods priority(KittenPriority weight);
}
