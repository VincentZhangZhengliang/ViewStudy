package com.topzrt.viewstudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.topzrt.viewstudy.view.PieView2;

import java.util.ArrayList;

public class BasicShapeActivity extends AppCompatActivity {

    private PieView2 pieview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_shape);
        pieview = findViewById(R.id.id_pieview);
        initData();
    }

    private void initData() {

        ArrayList<PieData> data = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            PieData pieData = new PieData("pie" + i, 100 * i);
            data.add(pieData);
        }
        pieview.setData(data);

    }
}
