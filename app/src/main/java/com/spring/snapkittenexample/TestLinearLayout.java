package com.spring.snapkittenexample;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.spring.snapkitten.core.Kitten;
import com.spring.snapkitten.enums.KittenOrientation;

import org.w3c.dom.Text;

/**
 * Created by spring on 31/3/2017.
 */

public class TestLinearLayout extends LinearLayout {
    public TestLinearLayout(Context context) {
        super(context);
        initUI();
    }

    public TestLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initUI();
    }

    public TestLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUI();
    }
    private void initUI(){
        TextView tv = new TextView(getContext());
        tv.setText("123 hlelo weklfwl ");
        TextView tv2 = new TextView(getContext());
        tv2.setText("123 hlelo weklfwl ");
        Kitten.create(KittenOrientation.vertical).from(this)
                .add(tv)
                .add(tv2).sideStartPadding(20)
                .build();
    }
}
