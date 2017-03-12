package com.spring.snapkittenexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.spring.kittenbinder.annotation.BindContext;
import com.spring.kittenbinder.annotation.BindTextView;
import com.spring.kittenbinder.binding.KittenBind;
import com.spring.snapkitten.core.Cub;
import com.spring.snapkitten.core.Kitten;
import com.spring.snapkitten.enums.KittenCompare;
import com.spring.snapkitten.enums.KittenPriority;
import com.spring.snapkitten.enums.KittenAlignment;
import com.spring.snapkitten.enums.KittenOrientation;

import org.w3c.dom.Text;

public class SampleActivity extends AppCompatActivity {

    private static String TAG = "Kitten";
    @BindContext
    TextView textViewA;
    @BindContext
    TextView textViewB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        KittenBind.bind(this);
        updateUI();
        ScrollView mainView = new ScrollView(this);
        setContentView(mainView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));


        TextView textViewC = new TextView(getApplicationContext(), null, R.style.textStyleExample);
        textViewC.setText("123 123 12 12 ewweio gowiehioehiowehgiowehowieh");
//        textViewC.setTextAppearance();
//        textViewC.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
//        textViewC.setTextAppearance(R.style.textStyleExample);

        TextView tv1 = new TextView(this);
        tv1.setText("wefjwe jwej iowvoiwe");
        TextView tv2 = new TextView(this);
        tv2.setText("weofjew ojvew");

        ImageView iv1 = new ImageView(this);
        iv1.setImageResource(android.R.drawable.btn_default);
        iv1.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageView iv2 = new ImageView(this);
        iv2.setImageResource(android.R.drawable.btn_default);
        iv2.setScaleType(ImageView.ScaleType.FIT_XY);
        View temp =
        Cub.create().from()
                .add(iv1).size(40, KittenCompare.equal).offset(10)
                .add(iv2).size(20, KittenCompare.equal).alignRight(iv1).alignParentBottom().rightOffset(-10)
                .build();
        temp.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));

        View child = alignParentCard();
        Kitten.create(KittenOrientation.vertical)
                .from(mainView).isAlignDirectionEnd(true)
                .add(temp).align(KittenAlignment.start)
                .add(weightExample())
                .add(textViewA)
                .add(textViewB)
                .add(textViewC)
                .add(child)
                .add(alignLeftItems())
                .build();

        PlainModel model = new PlainModel();
        model.test();
    }

    private void updateUI(){
//        textViewA.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
//        textViewB.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));

        textViewA.setText("123");
//        textViewA.setText(
//                "Lorem ipsum is a pseudo-Latin text used in web design, typography, layout, and printing in place of English to emphasise design elements over content. It's also called placeholder (or filler) text. It's a convenient tool for mock-ups. It helps to outline the visual elements of a document or presentation, eg typography, font, or layout. Lorem ipsum is mostly a part of a Latin text by the classical author and philosopher Cicero. Its words and letters have been changed by addition or removal, so to deliberately render its content nonsensical; it's not genuine, correct, or comprehensible Latin anymore. While lorem ipsum's still resembles classical Latin, it actually has no meaning whatsoever. As Cicero's text doesn't contain the letters K, W, or Z, alien to latin, these, and others are often inserted randomly to mimic the typographic appearence of European languages, as are digraphs not to be found in the original. In a professional context it often happens that private or corporate clients corder a publication to be made and presented with the actual content still not being ready. Think of a news blog that's filled with content hourly on the day of going live. However, reviewers tend to be distracted by comprehensible content, say, a random text copied from a newspaper or the internet. The are likely to focus on the text, disregarding the layout and its elements. Besides, random text risks to be unintendedly humorous or offensive, an unacceptable risk in corporate environments. Lorem ipsum and its many variants have been employed since the early 1960ies, and quite likely since the sixteenth century.");
        textViewB.setText(
                "Most of its text is made up from sections 1.10.32–3 of Cicero's De finibus bonorum et malorum (On the Boundaries of Goods and Evils; finibus may also be translated as purposes). Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit is the first known version (\"Neither is there anyone who loves grief itself since it is grief and thus wants to obtain it\"). It was found by Richard McClintock, a philologist, director of publications at Hampden-Sydney College in Virginia; he searched for citings of consectetur in classical Latin literature, a term of remarkably low frequency in that literary corpus. Sed ut perspiciatis, unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam eaque ipsa, quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt, explicabo. Nemo enim ipsam voluptatem, quia voluptas sit, aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos, qui ratione voluptatem sequi nesciunt, neque porro quisquam est, qui dolorem ipsum, quia dolor sit amet, consectetur, adipisci[ng] velit, sed quia non numquam [do] eius modi tempora inci[di]dunt, ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit, qui in ea voluptate velit esse, quam nihil molestiae consequatur, vel illum, qui dolorem eum fugiat, quo voluptas nulla pariatur?");
    }

    private View alignParentCard(){
        TextView textView = new TextView(this);
        ImageView ivProfile = new ImageView(this);
//        ivProfile.setImageResource(R.drawable.kitten);
        textView.setText("Most text editors like MS Word or Lotus Notes generate random lorem text when needed, either as pre-installed module or plug-in to be added. Word selection or sequence don't necessarily match the original, which is intended to add variety. Presentation software like Keynote or Pages use it as a samples for screenplay layout. Content management software as Joomla, Drupal, Mambo, PHP-Nuke, WordPress, or Movable Type offer Lorem Ipsum plug-ins with the same functionality."
        );
        TextView textView1 = new TextView(this);
        textView1.setText("Most text editors like MS Word or Lotus Notes generate random lorem text when needed, either as pre-installed module or plug-in to be added. Word selection or sequence don't necessarily match the original, which is intended to add variety. Presentation software like Keynote or Pages use it as a samples for screenplay layout. Content management software as Joomla, Drupal, Mambo, PHP-Nuke, WordPress, or Movable Type offer Lorem Ipsum plug-ins with the same functionality."
        );

        return Kitten.create(KittenOrientation.horizontal)
                .from()
                .defaultAlignment(KittenAlignment.start)
                .itemDefaultSideStartPadding(10)
                .itemDefaultSideEndPadding(10)
                .isAlignDirectionEnd(true)
                .startPadding(15)
                .endPadding(15)
                .add(ivProfile).size(40, KittenCompare.equal).priority(KittenPriority.high)
                .add(textView)//.height(240, KittenCompare.max)
//                .add(textView1)
                .build();
    }

    private View alignLeftItems(){
        TextView textView = new TextView(this);
        textView.setText("test 123");
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
        return Kitten.create(KittenOrientation.horizontal)
                .from()
                .defaultAlignment(KittenAlignment.center)
                .add(imageView).size(40, KittenCompare.equal).priority(KittenPriority.high)
                .add(textView).itemOffset(40)
                .build();
    }

    private View weightExample(){
        TextView viewA = new TextView(this);
        View viewB = new View(this);
        View viewC = new View(this);
        viewA.setText("1233r 23r gergre");
        viewA.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));
        viewB.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
        viewC.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));

        viewA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(SampleActivity.this, AlignBottomExample.class);
                startActivity(intent);
            }
        });
        return Kitten.create(KittenOrientation.horizontal).from()
                .weightMode(true).itemDefaultOffset(10).startPadding(20).endPadding(20)
                .add(viewA).weight(2)
                .add(viewB).weight(2)
                .add(viewC).weight(1)
                .build();
    }
}
