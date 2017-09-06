package com.topzrt.viewstudy;


import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class MineFragment extends Fragment {

    private View mView;

    private int progress = 0;
    private int max = 1000;

    private int currX;
    private int currY;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_mine, container, false);
            final MagicCircle id_mc = (MagicCircle) mView.findViewById(R.id.id_mc);
            Button id_btn = (Button) mView.findViewById(R.id.id_btn);

            final ProgressView progressView = (ProgressView) mView.findViewById(R.id.combineview);
            final VProgressView vProgressView = (VProgressView) mView.findViewById(R.id.vprogressview);
            vProgressView.setMax(max);


            id_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    progress = 0;
                    vProgressView.setProgress(progress);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (true) {
                                vProgressView.setProgress(progress++);
                                if (progress > max) break;
                                SystemClock.sleep(10);
                            }
                        }
                    }).start();

                }

            });


        }
        // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) mView.getParent();
        if (parent != null) {
            parent.removeView(mView);
        }
        return mView;
    }

}
