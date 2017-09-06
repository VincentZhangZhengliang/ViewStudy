package com.topzrt.viewstudy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Vincent on 2017/8/11.
 */

public class CombineView extends FrameLayout implements View.OnClickListener {

    private Context mContext;
    private CheckBox mCheckBox;
    private TextView mTextView;
    private String mText;
    private boolean mChecked;


    public CombineView(@NonNull Context context) {
        super(context);
        mContext = context;
        init();
    }

    public CombineView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mText = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "text");
        mChecked = attrs.getAttributeBooleanValue("http://schemas.android.com/apk/res-auto", "checked", false);
        init();
    }

    public CombineView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }


    private void init() {
        View view = View.inflate(getContext(), R.layout.combine_layout, this);
        mTextView = (TextView) view.findViewById(R.id.textView);
        mCheckBox = (CheckBox) view.findViewById(R.id.checkBox);
        mTextView.setText(mText);
        mCheckBox.setChecked(mChecked);
        this.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        mCheckBox.setChecked(!mCheckBox.isChecked());
    }

    public void setText(String text) {
        mTextView.setText(text);
    }

    public boolean isChecked() {
        return mCheckBox.isChecked();
    }

    public void setChecked(boolean checked) {
        mCheckBox.setChecked(checked);
    }

}
