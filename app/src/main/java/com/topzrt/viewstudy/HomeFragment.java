package com.topzrt.viewstudy;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class HomeFragment extends Fragment implements View.OnClickListener {

    private View mView;
    private CheckView mCheckView;
    private Button mCheck;
    private Button mUncheck;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_home, container, false);
        }
        // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) mView.getParent();
        if (parent != null) {
            parent.removeView(mView);
        }
        initView(mView);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initListener();
    }

    private void initListener() {

        mCheck.setOnClickListener(this);
        mUncheck.setOnClickListener(this);

    }

    private void initView(View view) {

        mCheckView = view.findViewById(R.id.check_view);
        mCheckView.setAnimDuration(500);
        mCheck = view.findViewById(R.id.check);
        mUncheck = view.findViewById(R.id.uncheck);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.check:
                mCheckView.check();
                startActivity(new Intent(getActivity(), Day1Activity.class));
                break;

            case R.id.uncheck:
                mCheckView.unCheck();
                break;
        }
    }
}
