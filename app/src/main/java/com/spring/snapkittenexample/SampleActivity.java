package com.spring.snapkittenexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.spring.snapkitten.SnapKitten;

public class SampleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tvA = new TextView(this);
        TextView tvB = new TextView(this);
        TextView btnA = new TextView(this);

        ViewGroup frameLayout = new FrameLayout(this);
        setContentView(frameLayout, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        new SnapKitten(this).from(frameLayout)
                .add(tvA)
                .add(btnA)
                .add(tvB)
                .build();

        tvA.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
        tvA.setText(" ewjfewj ijoiwphwi ehvwehvhwei vhweiih ewfiew hiwehfiewhfiuwehfui wehfiwh euifhwei iwehfieuwhfiwe hfi uwehfuiwe hfiwe hfiw");
        tvB.setText("fks eoj ioewiowe owevh iweivwe vwjvowej ovjewoi vjweojv owjiwe jojwei vwj owojviow jvoew jviowejvoiwe j");

        btnA.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright));
        btnA.setText("Button");
    }
}
