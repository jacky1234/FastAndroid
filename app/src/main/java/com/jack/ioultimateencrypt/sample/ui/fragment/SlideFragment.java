package com.jack.ioultimateencrypt.sample.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 2017/8/15.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

/**
 * trt to extends from
 * {@link com.cleveroad.slidingtutorial.PageSupportFragment}
 */
public class SlideFragment extends Fragment {
    private final static String KEY = "SlideFragment_key";


    public static SlideFragment newIntance(String tag) {
        SlideFragment slideFragment = new SlideFragment();
        final Bundle bundle = new Bundle();
        bundle.putString(KEY, tag);
        slideFragment.setArguments(bundle);
        return slideFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView tv = new TextView(getContext());
        tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
        tv.setGravity(Gravity.CENTER);
        tv.setText(getArguments().getString(KEY));
        return tv;
    }


}
