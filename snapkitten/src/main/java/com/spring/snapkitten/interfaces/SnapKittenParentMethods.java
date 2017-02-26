package com.spring.snapkitten.interfaces;

import com.spring.snapkitten.enums.SnapKittenAlignment;
import com.spring.snapkitten.enums.SnapKittenOrientation;

/**
 * Created by spring on 26/2/2017.
 */
public interface SnapKittenParentMethods extends SnapKittenChild, SnapKittenBuild {
    SnapKittenParentMethods defaultAlignment(SnapKittenAlignment alignment);
    SnapKittenParentMethods startPadding(int value);
    SnapKittenParentMethods endPadding(int value);
    SnapKittenParentMethods itemDefaultOffset(int value);
    SnapKittenParentMethods itemDefaultSideStartPadding(int value);
    SnapKittenParentMethods itemDefaultSideEndPadding(int value);
    SnapKittenParentMethods isAlignDirectionEnd(boolean isAlign);
    SnapKittenParentMethods orientation(SnapKittenOrientation orientation);
}
