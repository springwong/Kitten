package com.spring.snapkitten.interfaces;

import com.spring.snapkitten.enums.SnapKittenAlignment;

/**
 * Created by spring on 26/2/2017.
 */
public interface SnapKittenChildMethods extends SnapKittenChild, SnapKittenBuild {
    SnapKittenChildMethods top(int value);
    SnapKittenChildMethods left(int value);
    SnapKittenChildMethods right(int value);
    SnapKittenChildMethods bottom(int value);
    SnapKittenChildMethods edge(int value);
    SnapKittenChildMethods align(SnapKittenAlignment align);
}
