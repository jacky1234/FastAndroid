package com.jack.ioultimateencrypt.sample.ui.adapter

import android.support.v4.content.ContextCompat
import android.widget.ImageView
import com.blankj.utilcode.util.SpanUtils
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jack.ioultimateencrypt.sample.R
import com.jack.ioultimateencrypt.sample.color
import com.jack.ioultimateencrypt.sample.mvp.model.bean.HotMovieBean

/**
 * 2017/8/28.
 * github:[https://github.com/jacky1234]
 * @author  jackyang
 *
 */
class HotMovieAdapter(layout: Int, data: MutableList<HotMovieBean.MsBean>?) : BaseQuickAdapter<HotMovieBean.MsBean, BaseViewHolder>(layout, data) {

    override fun convert(holder: BaseViewHolder, item: HotMovieBean.MsBean) {
        holder.setText(R.id.tv_movie_name, item.t)
                .setVisible(R.id.iv_3d, item.isIs3D)
                .setVisible(R.id.iv_imax, item.isIsIMAX)
                .setText(R.id.tv_score,
                        if (item.r > 0) {
                            SpanUtils()
                                    .append(item.r.toString()).setFontSize(16, true).setForegroundColor(mContext.color(R.color.dark_green))
                                    .append("分").setFontSize(12, true).setForegroundColor(mContext.color(R.color.dark_green))
                                    .create()
                        } else "")
                .setText(R.id.tv_want_see,
                        if (item.commonSpecial?.isEmpty() == false) {
                            SpanUtils().append(String.format("'%s", item.commonSpecial)).setForegroundColor(mContext.color(R.color.dark_green)).create()
                        } else {
                            SpanUtils()
                                    .append(String.format("%d", item.wantedCount)).setForegroundColor(ContextCompat.getColor(mContext, R.color.orange))
                                    .append("人想看").setForegroundColor(ContextCompat.getColor(mContext, R.color.gray))
                                    .append(if (item.movieType?.isEmpty() != false) "" else String.format("-%s", item.movieType)).setForegroundColor(ContextCompat.getColor(mContext, R.color.gray))
                                    .create()
                        })
                .setText(R.id.tv_actor, if (item.actors?.isEmpty() != false) "" else item.actors)
                .getView<ImageView>(R.id.iv_cover)
                .let {
                    Glide.with(mContext).load(item.img)
                            .centerCrop()
                            .placeholder(R.drawable.welcome)
                            .dontAnimate()
                            .into(it)
                }
    }
}