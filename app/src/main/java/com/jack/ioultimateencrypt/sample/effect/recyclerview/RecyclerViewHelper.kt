package com.jack.ioultimateencrypt.sample.effect.recyclerview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.os.Build
import android.support.annotation.FloatRange
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import android.widget.RelativeLayout

/**
 * 2017/9/14.
 * github:[https://github.com/jacky1234]
 * @author  jackyang
 *
 */
class RecyclerViewHelper private constructor(var mRecyclerView: RecyclerView) {
    private var mOnScrollListener: RecyclerView.OnScrollListener
    private var mHeader: CustomRelativeWrapper? = null        //视觉差
    private var mScrollMultiplier = 0.5f                      //视觉差因子
    private var mParallaxScroll: OnParallaxScroll? = null     //视觉差回调
    private var mTotalYScrolled: Int = 0                      //垂直方向的滚动距离
    private var isParallaxHeader = false                  //是否支持视觉差效果


    init {
        mOnScrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (mHeader != null) {
                    mTotalYScrolled += dy
                    if (isParallaxHeader)
                        translateHeader(mTotalYScrolled.toFloat())
                }
            }
        }

        mRecyclerView.removeOnScrollListener(mOnScrollListener)
        mRecyclerView.addOnScrollListener(mOnScrollListener)
    }

    /**
     * Translates the adapter in Y
     *
     * @param of offset in px
     */
    fun translateHeader(of: Float) {
        val ofCalculated = of * mScrollMultiplier
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB && of < mHeader!!.height) {
            mHeader!!.translationY = ofCalculated
        } else if (of < mHeader!!.height) {
            val anim = TranslateAnimation(0f, 0f, ofCalculated, ofCalculated)
            anim.fillAfter = true
            anim.duration = 0
            mHeader!!.startAnimation(anim)
        }
        /**
         * 实验结果：不设置也能给人视觉差效果
         * 因为 ofCalculated<=of, 而在滑动过程中，RecyclerView下方子View层级比mHeader高，导致视觉差效果，但这时候 mScrollMultiplier = 0.5f 才能使上方和下方但视觉差效果一致
         */
        mHeader!!.setClipY(Math.round(ofCalculated))
        if (mParallaxScroll != null) {
            val holder = mRecyclerView.findViewHolderForAdapterPosition(0)
            val left: Float
            if (holder != null) {
                left = Math.min(1f, ofCalculated / (mHeader!!.height * mScrollMultiplier))
            } else {
                left = 1f
            }
            mParallaxScroll!!.onParallaxScroll(left, of, mHeader!!)
        }
    }

    class Builder @JvmOverloads constructor(recyclerView: RecyclerView) {
        private val mRecyclerView: RecyclerView = recyclerView
        private var mHeader: CustomRelativeWrapper? = null
        private val mContext = recyclerView.context
        private var mParallaxScroll: OnParallaxScroll? = null
        private var mScrollMultiplier = 0.5f                      //视觉差因子

        fun setParallaxHeader(@LayoutRes mLayout: Int): Builder {
            return setParallaxHeader(LayoutInflater.from(mContext).inflate(mLayout, null))
        }

        fun setParallaxHeader(header: View): Builder {
            if (header !is CustomRelativeWrapper) {
                mHeader = CustomRelativeWrapper(header.context)
                mHeader!!.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                mHeader!!.addView(header, RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
            } else mHeader = header

            mHeader!!.IsParallaxHeader(true)
            return this
        }

        /**
         * 视差因子，影响HeaderView向下移动的快慢
         */
        fun setScrollMultiplier(@FloatRange(from = 0.0, to = 1.0) mScrollMultiplier: Float): Builder {
            this.mScrollMultiplier = mScrollMultiplier
            return this
        }

        /**
         * should invoke after {@setParallaxHeader}
         */
        fun registerOnParallaxHeadSetListener(onParallaxHeadSetListener: OnParallaxHeadSetListener): Builder {
            onParallaxHeadSetListener.onParallaxHeadSetListener(mHeader!!)
            return this
        }

        fun registerParallaxScrollListener(mParallaxScroll: OnParallaxScroll): Builder {
            this.mParallaxScroll = mParallaxScroll
            return this
        }

        fun create(): RecyclerViewHelper {
            val res = RecyclerViewHelper(mRecyclerView)
            res.mHeader = mHeader
            res.isParallaxHeader = mHeader != null
            res.mScrollMultiplier = mScrollMultiplier
            res.mParallaxScroll = mParallaxScroll

            return res
        }
    }

    /**
     * Custom layout for the Parallax Header.
     */
    class CustomRelativeWrapper(context: Context) : RelativeLayout(context) {
        private var mOffset: Int = 0
        private var isParallaxHeader = false

        fun IsParallaxHeader(isParallaxHeader: Boolean) {
            this.isParallaxHeader = isParallaxHeader
        }

        override fun dispatchDraw(canvas: Canvas) {
            if (isParallaxHeader)
                canvas.clipRect(Rect(left, top + mOffset, right, bottom - mOffset))
            super.dispatchDraw(canvas)
        }

        fun setClipY(offset: Int) {
            mOffset = offset
            invalidate()
        }
    }

    interface OnParallaxScroll {
        fun onParallaxScroll(percentage: Float, offset: Float, parallax: View)
    }

    interface OnParallaxHeadSetListener {
        /**
         * 返回 CustomRelativeWrapper 包装的Header
         */
        fun onParallaxHeadSetListener(head: CustomRelativeWrapper)
    }
}