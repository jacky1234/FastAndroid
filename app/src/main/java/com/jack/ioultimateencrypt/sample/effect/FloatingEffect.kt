package com.jack.ioultimateencrypt.sample.effect

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.jack.ioultimateencrypt.sample.R
import com.jack.ioultimateencrypt.sample.effect.floating.BeerFloating
import com.jack.ioultimateencrypt.sample.effect.floating.StarFloating
import com.jack.ioultimateencrypt.sample.effect.floating.TranslateFloatingTransition
import com.ufreedom.floatingview.Floating
import com.ufreedom.floatingview.FloatingBuilder
import com.ufreedom.floatingview.effect.ScaleFloatingTransition
import com.ufreedom.floatingview.spring.ReboundListener
import com.ufreedom.floatingview.spring.SpringHelper

/**
 * 2017/9/10.
 * github:[https://github.com/jacky1234]
 * @author  jackyang
 *
 */
class FloatingEffect(private var activity: Activity) {

    private val mEffects = ArrayList<Effect>()
    private var mFloating: Floating = Floating(activity)

    fun get(position: Int): Effect {
        return mEffects[position % mEffects.size]
    }

    init {
        mEffects.clear()
        mEffects.add(
                Effect(R.drawable.paper_airplane, View.OnClickListener { v ->
                    val imageView = ImageView(activity)
                    imageView.layoutParams = android.view.ViewGroup.LayoutParams(v.measuredWidth, v.measuredHeight)
                    imageView.setImageResource(R.drawable.paper_airplane)

                    val floatingElement = FloatingBuilder()
                            .anchorView(v)
                            .targetView(imageView)
                            .floatingTransition(TranslateFloatingTransition(-300f, 1500))
                            .build()
                    mFloating.startFloating(floatingElement)
                }))

        mEffects.add(
                Effect(R.drawable.command_line, View.OnClickListener { v ->
                    val textView = TextView(activity)
                    textView.text = "collected"

                    val floatingElement = FloatingBuilder()
                            .anchorView(v)
                            .targetView(textView)
                            .offsetY(-v.measuredHeight)
                            .floatingTransition(ScaleFloatingTransition())
                            .build()
                    mFloating.startFloating(floatingElement)
                })
        )

        mEffects.add(
                Effect(R.drawable.like, View.OnClickListener { v ->
                    val floatingElement = FloatingBuilder()
                            .anchorView(v)
                            .targetView(R.layout.view_like)
                            .floatingTransition(TranslateFloatingTransition())
                            .build()
                    mFloating.startFloating(floatingElement)
                })
        )

        mEffects.add(
                Effect(R.drawable.star, View.OnClickListener { v ->
                    val imageView = ImageView(activity)
                    imageView.layoutParams = ViewGroup.LayoutParams(v.measuredWidth, v.measuredHeight)
                    imageView.setImageResource(R.drawable.star_floating)

                    val floatingElement = FloatingBuilder()
                            .anchorView(v)
                            .targetView(imageView)
                            .floatingTransition(StarFloating())
                            .build()

                    SpringHelper.createWithBouncinessAndSpeed(0f, 1f, 10.0, 15.0).reboundListener(object : ReboundListener {
                        override fun onReboundUpdate(currentValue: Double) {
                            v.scaleX = currentValue.toFloat()
                            v.scaleY = currentValue.toFloat()
                        }

                        override fun onReboundEnd() {
                            mFloating.startFloating(floatingElement)
                        }
                    }).start()
                })
        )

        mEffects.add(
                Effect(R.drawable.beer, View.OnClickListener { v ->
                    val imageView = ImageView(activity)
                    imageView.layoutParams = ViewGroup.LayoutParams(v.measuredWidth, v.measuredHeight)
                    imageView.setImageResource(R.drawable.beer)

                    val floatingElement = FloatingBuilder()
                            .anchorView(v)
                            .targetView(imageView)
                            .floatingTransition(BeerFloating())
                            .build()
                    mFloating.startFloating(floatingElement)
                })
        )
    }

    class Effect(var drawableId: Int, var onClickListener: View.OnClickListener?)
}