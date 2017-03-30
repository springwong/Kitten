package com.spring.snapkittenexample;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.spring.kittendecorator.KittenBind;
import com.spring.snapkitten.core.Kitten;
import com.spring.snapkitten.enums.KittenAlignment;
import com.spring.snapkitten.enums.KittenOrientation;
import com.spring.snapkitten.enums.KittenPriority;

import kittenbinder.BindContext;

/**
 * A placeholder fragment containing a simple view.
 */
public class AlignBottomExampleFragment extends Fragment {

    @BindContext
    ScrollView sv;
    @BindContext
    TextView btn;

    @BindContext
    TextView lbl;
    @BindContext
    TextView btnB;

    public AlignBottomExampleFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        KittenBind.bind(this, getActivity());
        btn.setText("12 e12e 1");
        sv.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));
        btn.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
        btn.setGravity(Gravity.CENTER);
        View mainView = Kitten.create(KittenOrientation.vertical).from(getContext())
                .isAlignDirectionEnd(true).defaultAlignment(KittenAlignment.parent)
        .add(sv).fillParent()
                .add(btn)
                .build();

        lbl.setText("jojvweo voiwev");
        btnB.setText("End Button");

        btnB.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
        Kitten.create(KittenOrientation.vertical).from(sv)
                .add(lbl).align(KittenAlignment.center)
        .add(btnB).align(KittenAlignment.end).itemOffset(20).sideEndPadding(50)
                .build();
        return mainView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
