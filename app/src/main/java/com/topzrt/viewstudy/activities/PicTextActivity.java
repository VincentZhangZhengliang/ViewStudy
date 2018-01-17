package com.topzrt.viewstudy.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.topzrt.viewstudy.R;

public class PicTextActivity extends AppCompatActivity {

    private static final String TAG = "PicTextActivity";
    private RadioGroup mRg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_text);
//        final CheckView2 id_cv2 = findViewById(R.id.id_cv2);
//        findViewById(R.id.id_check).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                id_cv2.check();
//            }
//        });
//
//        findViewById(R.id.id_uncheck).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                id_cv2.uncheck();
//            }
//        });

        mRg = findViewById(R.id.id_rg);
        for (int i = 0; i < 5; i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText("rb" + i);
            radioButton.setTextColor(Color.BLUE);
            mRg.addView(radioButton);
        }
        mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.e(TAG, "onCheckedChanged: " + checkedId);
                clearTextColor(mRg);
                checkedId -= 1;
                RadioButton rb = (RadioButton) group.getChildAt(checkedId);
                rb.setTextColor(Color.RED);
            }
        });
    }

    private void clearTextColor(RadioGroup rg) {
        for (int i = 0; i < rg.getChildCount(); i++) {
            RadioButton rb = (RadioButton) rg.getChildAt(i);
            rb.setTextColor(Color.BLUE);
        }
    }


}
