package com.jack.ioultimateencrypt.sample.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.cleveroad.slidingtutorial.IndicatorOptions;
import com.cleveroad.slidingtutorial.OnTutorialPageChangeListener;
import com.cleveroad.slidingtutorial.Renderer;
import com.cleveroad.slidingtutorial.TutorialOptions;
import com.cleveroad.slidingtutorial.TutorialPageProvider;
import com.cleveroad.slidingtutorial.TutorialSupportFragment;
import com.jack.ioultimateencrypt.sample.MainActivity;
import com.jack.ioultimateencrypt.sample.R;
import com.safframework.log.L;

/**
 * 2017/8/15.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public class WelcomeFragment extends TutorialSupportFragment implements OnTutorialPageChangeListener {
    private static final int TOTAL_PAGES = 6;

    private int[] pagesColors;

    private OnSelectPageListener pageListener;

    public interface OnSelectPageListener {
        void selectColor(int color);
    }

    private final int[] colorRes = {android.R.color.holo_orange_dark
            , android.R.color.holo_green_dark
            , android.R.color.holo_blue_dark
            , android.R.color.holo_red_dark
            , android.R.color.holo_purple
            , android.R.color.darker_gray
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        addOnTutorialPageChangeListener(this);
        pageListener = (OnSelectPageListener) getActivity();

        pagesColors = new int[]{
                ContextCompat.getColor(getContext(), android.R.color.holo_orange_dark),
                ContextCompat.getColor(getContext(), android.R.color.holo_green_dark),
                ContextCompat.getColor(getContext(), android.R.color.holo_blue_dark),
                ContextCompat.getColor(getContext(), android.R.color.holo_red_dark),
                ContextCompat.getColor(getContext(), android.R.color.holo_purple),
                ContextCompat.getColor(getContext(), android.R.color.darker_gray)};
        L.init(this.getClass());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        removeOnTutorialPageChangeListener(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_welcome;
    }

    @Override
    public void onPageChanged(int position) {
        L.d("onPageChanged,position:" + position);
        pageListener.selectColor(colorRes[position]);
    }

    /**
     * tutorialItems = new TransformItem[]{
     * TransformItem.create(R.id.ivFirstImage, Direction.RIGHT_TO_LEFT, 0.2f),
     * TransformItem.create(R.id.ivSecondImage, Direction.LEFT_TO_RIGHT, 0.06f),
     * TransformItem.create(R.id.ivThirdImage, Direction.RIGHT_TO_LEFT, 0.08f),
     * TransformItem.create(R.id.ivFourthImage, Direction.LEFT_TO_RIGHT, 0.1f),
     * TransformItem.create(R.id.ivFifthImage, Direction.LEFT_TO_RIGHT, 0.03f),
     * TransformItem.create(R.id.ivSixthImage, Direction.LEFT_TO_RIGHT, 0.09f),
     * TransformItem.create(R.id.ivSeventhImage, Direction.LEFT_TO_RIGHT, 0.14f)
     * };
     *
     * @return
     */
    @Override
    protected TutorialOptions provideTutorialOptions() {
        return newTutorialOptionsBuilder(getContext())
                .setUseInfiniteScroll(true)
                .setUseAutoRemoveTutorialFragment(false)
                .setPagesColors(pagesColors)
                .setPagesCount(TOTAL_PAGES)
                .setTutorialPageProvider(tutorialPageProvider)
                .setOnSkipClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(), MainActivity.class));
                        getActivity().finish();
                    }
                })
                .setIndicatorOptions(IndicatorOptions.newBuilder(getContext())
                        .setElementSizeRes(R.dimen.indicator_size)
                        .setElementSpacingRes(R.dimen.indicator_spacing)
                        .setElementColorRes(android.R.color.darker_gray)
                        .setSelectedElementColor(Color.LTGRAY)
                        .setRenderer(Renderer.Factory.newSquareRenderer())
                        .build())
                .build();
    }

    @Override
    protected int getButtonSkipResId() {
        return R.id.tv_skip;
    }

    final TutorialPageProvider tutorialPageProvider = new TutorialPageProvider<Fragment>() {

        @NonNull
        @Override
        public Fragment providePage(int position) {
            return SlideFragment.newIntance(position + "");
        }
    };

}
