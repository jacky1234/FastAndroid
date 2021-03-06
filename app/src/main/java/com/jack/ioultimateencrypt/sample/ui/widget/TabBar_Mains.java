package com.jack.ioultimateencrypt.sample.ui.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.jack.ioultimateencrypt.sample.R;

/**
 * 2017/9/5.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public class TabBar_Mains extends LinearLayout {
    private String sName;
    private Drawable sIcon;
    private RadioButton mRadioButton;
    private int mCheckId;
    private ColorStateList mTextColor;

    public TabBar_Mains(Context context) {
        this(context, null);
    }

    public TabBar_Mains(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.tabbar_mains, this, true);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TabBar_Attr);
        sName = typedArray.getString(R.styleable.TabBar_Attr_name);
        sIcon = typedArray.getDrawable(R.styleable.TabBar_Attr_icon);
        mCheckId = typedArray.getResourceId(R.styleable.TabBar_Attr_checkId, 0);
        mTextColor = typedArray.getColorStateList(R.styleable.TabBar_Attr_textColor);
        assert mCheckId != 0;
        assert mTextColor != null;

        typedArray.recycle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mRadioButton = (RadioButton) findViewById(R.id.icon_tabbar);
        mRadioButton.setId(mCheckId);
        mRadioButton.setTextColor(mTextColor);
        if (TextUtils.isEmpty(sName)) ;
        else {
            setName(sName);
        }

        if (sIcon != null)
            setIcon(sIcon);
    }

    public void setName(String name) {
        mRadioButton.setText(name);
    }

    public void setIcon(Drawable icon) {
        mRadioButton.setCompoundDrawablesWithIntrinsicBounds(null, icon, null, null);
    }
}
