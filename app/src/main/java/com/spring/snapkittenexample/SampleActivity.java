package com.spring.snapkittenexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.spring.snapkitten.SnapKitten;
import com.spring.snapkitten.enums.SnapKittenAlignment;
import com.spring.snapkitten.enums.SnapKittenOrientation;

public class SampleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView tvH = new TextView(this);
        tvH.setText(" fwefew eveoi jvwioejv ejveiweooei vreiov weoivjw eovwev owe");
        TextView tvJ = new TextView(this);
        tvJ.setText("ew jew we vwejv");
        ImageView ivH = new ImageView(this);
        ivH.setImageResource(R.drawable.kitten);
        ivH.setAdjustViewBounds(true);
        ivH.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
        View group = new SnapKitten(this, SnapKittenOrientation.horizontal)           .isAlignDirectionEnd(true)
                .add(tvH)  .compressResistance(1000)
                .add(ivH)                .compressResistance(0)
                .add(tvJ)            .compressResistance(500)
                .build();

        TextView tvA = new TextView(this);
        TextView tvB = new TextView(this);
        TextView btnA = new TextView(this);

        ViewGroup frameLayout = new FrameLayout(this);
        setContentView(frameLayout, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        new SnapKitten(this, SnapKittenOrientation.vertical)
                .from(frameLayout).isAlignDirectionEnd(true)
                .defaultAlignment(SnapKittenAlignment.start)
                .add(tvA)
                .add(group)
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
