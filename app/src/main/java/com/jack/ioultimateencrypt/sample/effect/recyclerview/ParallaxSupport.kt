//package com.jack.ioultimateencrypt.sample.effect.recyclerview
//
//import android.content.Context
//import android.graphics.Canvas
//import android.graphics.Rect
//import android.support.annotation.LayoutRes
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.RelativeLayout
//
///**
// * 2017/9/13.
// * github:[https://github.com/jacky1234]
// * @author  jackyang
// * 视觉差效果的支持
// *
// */
//class ParallaxSupport {
//    companion object {
//        private var isParallaxHeader = false
//    }
//
//    /**
//     * allow resource layout id to be introduced
//     *
//     * @param mLayout res id
//     */
//    fun setParallaxHeader(@LayoutRes mLayout: Int) {
//        val h_layout = LayoutInflater.from(getContext()).inflate(mLayout, null)
//        setParallaxHeader(h_layout)
//    }
//
//    /**
//     * Set the parallax header of the recyclerview
//     *
//     * @param header the view
//     */
//    fun setParallaxHeader(header: View) {
//        mHeader = CustomRelativeWrapper(header.context)
//        mHeader.setLayoutParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT))
//        mHeader.addView(header, RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
//        isParallaxHeader = true
//    }
//
//
//
//}