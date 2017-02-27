package com.spring.snapkitten.interfaces;

import com.spring.snapkitten.enums.KittenAlignment;
import com.spring.snapkitten.enums.KittenOrientation;

/**
 * Created by spring on 26/2/2017.
 */
public interface KittenParentMethods extends KittenChild, KittenBuild {
    KittenParentMethods defaultAlignment(KittenAlignment alignment);
    KittenParentMethods startPadding(int value);
    KittenParentMethods endPadding(int value);
    KittenParentMethods itemDefaultOffset(int value);
    KittenParentMethods itemDefaultSideStartPadding(int value);
    KittenParentMethods itemDefaultSideEndPadding(int value);
    KittenParentMethods isAlignDirectionEnd(boolean isAlign);
    KittenParentMethods orientation(KittenOrientation orientation);
    KittenParentMethods weightMode(boolean isOn);
}
