package com.topzrt.viewstudy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.topzrt.viewstudy.activities.*;
import com.topzrt.viewstudy.activities.PathTestActivity;

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

        findViewById(R.id.id_path_basic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Day1Activity.this, PathTestActivity.class));
            }
        });

        findViewById(R.id.id_bezier).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Day1Activity.this, BezierActivity.class));
            }
        });
    }
}
