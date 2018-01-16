package com.topzrt.viewstudy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.topzrt.viewstudy.activities.CanvasActivity;
import com.topzrt.viewstudy.activities.PicTextActivity;

public class Day1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day1);

        findViewById(R.id.id_basic_shape).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(Day1Activity.this, BasicShapeActivity.class));
            }

        });

        findViewById(R.id.id_canvas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Day1Activity.this, CanvasActivity.class));
            }
        });

        findViewById(R.id.id_pic_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Day1Activity.this, PicTextActivity.class));
            }
        });


    }
}
