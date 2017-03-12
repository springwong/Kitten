package com.spring.snapkitten.subclass;

import android.view.View;

import com.spring.snapkitten.enums.CubAction;

/**
 * Created by spring on 12/3/2017.
 */

public class CubRelativeAction {
    public View target;
    public CubAction action = CubAction.none;

    public CubRelativeAction(View target, CubAction action) {
        this.target = target;
        this.action = action;
    }
}
