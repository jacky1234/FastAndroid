package com.jack.ioultimateencrypt.sample.ui.adapter

import android.support.v4.content.ContextCompat
import android.widget.ImageView
import com.blankj.utilcode.util.SpanUtils
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jack.ioultimateencrypt.sample.R
import com.jack.ioultimateencrypt.sample.effect.FloatingEffect
import com.jack.ioultimateencrypt.sample.mvp.model.bean.UpcomingMovieBean
import com.jack.ioultimateencrypt.sample.ui.widget.LineProgress

/**
 * 2017/8/28.
 * github:[https://github.com/jacky1234]
 * @author  jackyang
 *
 */
class UpcomingMovieAdapter(layout: Int, data: MutableList<UpcomingMovieBean.MoviecomingsBean>?) : BaseQuickAdapter<UpcomingMovieBean.MoviecomingsBean, BaseViewHolder>(layout, data) {
    lateinit var mFloatingEffect: FloatingEffect
    fun inject(mFloatingEffect: FloatingEffect) {
        this.mFloatingEffect = mFloatingEffect
    }

    override fun convert(holder: BaseViewHolder, item: UpcomingMovieBean.MoviecomingsBean) {
        val effect = mFloatingEffect.get(holder.layoutPosition - headerLayoutCount)

        holder.setText(R.id.date_tv, String.format("%d月%d日", item.rMonth, item.rDay))
                .setText(R.id.tv_movie_name, item.title)
                .setText(R.id.tv_want_see, SpanUtils()
                        .append(String.format("%d", item.wantedCount)).setForegroundColor(ContextCompat.getColor(mContext, R.color.orange))
                        .append("人想看").setForegroundColor(ContextCompat.getColor(mContext, R.color.gray))
                        .append(if (item.type == null) "" else String.format("-%s", item.type)).setForegroundColor(ContextCompat.getColor(mContext, R.color.gray))
                        .create())
                .setText(R.id.tv_actor, String.format("%s/%s", item.actor1, item.actor2))
                .setVisible(R.id.iv_play, !item.videos?.isEmpty()!!)
                .setImageResource(R.id.iv_like, effect.drawableId)
                .addOnClickListener(R.id.iv_like)
                .getView<ImageView>(R.id.iv_cover)
                .let {
                    Glide.with(mContext).load(item.image)
                            .centerCrop()
                            .placeholder(R.drawable.welcome)
                            .dontAnimate()
                            .into(it)
//                    Picasso.with(activity).load(item?.image).placeholder(R.drawable.welcome).into(it)
                }

        holder.getView<LineProgress>(R.id.lineProgress).let {
            it.setTotal(1000).smoothTo(if (item.wantedCount < 1000) item.wantedCount else 1000)
        }


        val prePos = holder.layoutPosition!! - headerLayoutCount - 1 //减去头部layout子view的count
        if (prePos >= 0) {
            if (item.rDay != getItem(prePos)?.rDay || item.rMonth != getItem(prePos)?.rMonth) {
                holder.setVisible(R.id.date_tv, true)
            } else {
                holder.setVisible(R.id.date_tv, false)
            }
        } else {
            holder.setVisible(R.id.date_tv, true)
        }
    }
}