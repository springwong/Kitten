package com.spring.snapkittenexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.spring.snapkitten.SnapKitten;
import com.spring.snapkitten.enums.KittenCompareEnum;
import com.spring.snapkitten.enums.SnapKittenAlignment;
import com.spring.snapkitten.enums.SnapKittenOrientation;

public class SampleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView tvH = new TextView(this);
        tvH.setText(" fwe webh hg g gy y ygy ge");
        tvH.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        TextView tvJ = new TextView(this);
        tvJ.setText("ew jew we vwejv");
        ImageView ivH = new ImageView(this);
        ivH.setImageResource(R.drawable.kitten);
        ivH.setAdjustViewBounds(true);
        ivH.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
        View group = new SnapKitten(this, SnapKittenOrientation.horizontal)
                .isAlignDirectionEnd(true) .defaultAlignment(SnapKittenAlignment.center)
                .add(tvH).align(SnapKittenAlignment.parent).left(70).fillParent().compressResistance(50)
                .add(ivH).width(40, KittenCompareEnum.max)    .left(50).height(40, KittenCompareEnum.equal)
                .add(tvJ).left(50)
                .build()
                ;
        group.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));

        TextView tvA = new TextView(this);
        TextView tvB = new TextView(this);
        TextView btnA = new TextView(this);

        ViewGroup frameLayout = new FrameLayout(this);
        setContentView(frameLayout, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        new SnapKitten(this, SnapKittenOrientation.vertical)
                .from(frameLayout)
                //.isAlignDirectionEnd(true)
                .defaultAlignment(SnapKittenAlignment.start)
                .add(tvA)
                .add(group).align(SnapKittenAlignment.parent)
                .add(btnA).align(SnapKittenAlignment.end).right(200)
                .add(tvB).left(100).right(50)
                .build();

        tvA.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
        tvA.setText(" ewjfewj ijoiwphwi ehvwehvhwei vhweiih ewfiew hiwehfiewhfiuwehfui wehfiwh euifhwei iwehfieuwhfiwe hfi uwehfuiwe hfiwe hfiw");
        tvB.setText("fks eoj ioewiowe owevh iweivwe vwjvowej ovjewoi vjweojv owjiwe jojwei vwj owojviow jvoew jviowejvoiwe j");

        btnA.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright));
        btnA.setText("Button");
    }
}
