package com.topzrt.viewstudy;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.w3c.dom.Text;

public class TestActivity extends AppCompatActivity {

    private Taiji mTaiji;
    private float degree = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mTaiji = (Taiji) findViewById(R.id.taiji);
        final TestView testView = (TestView) findViewById(R.id.testview);

        Button start = (Button) findViewById(R.id.button);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHandler.sendEmptyMessageDelayed(0, 20);
//                testView.start();

            }
        });


    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            mTaiji.setDegree(degree += 5);
            this.sendEmptyMessageDelayed(0, 80);

        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }
}
