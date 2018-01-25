package com.topzrt.viewstudy.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.topzrt.viewstudy.R;
import com.topzrt.viewstudy.view2.CusView01;

public class BezierActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezier);
        CusView01 cusView01 = findViewById(R.id.id_cv01);
        new Thread(cusView01).start();
    }

}
