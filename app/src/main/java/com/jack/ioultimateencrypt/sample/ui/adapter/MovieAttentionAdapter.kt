package com.jack.ioultimateencrypt.sample.ui.adapter

import android.support.v4.content.ContextCompat
import android.widget.ImageView
import com.blankj.utilcode.util.SpanUtils
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jack.ioultimateencrypt.sample.R
import com.jack.ioultimateencrypt.sample.mvp.model.bean.UpcomingMovieBean

/**
 * 2017/8/31.
 * github:[https://github.com/jacky1234]
 * @author  jackyang
 * 最受关注
 */
class MovieAttentionAdapter(layout: Int, data: MutableList<UpcomingMovieBean.AttentionBean>) : BaseQuickAdapter<UpcomingMovieBean.AttentionBean, BaseViewHolder>(layout, data) {

    override fun convert(holder: BaseViewHolder, item: UpcomingMovieBean.AttentionBean) {
        holder.setText(R.id.tv_date, String.format("%s月%s日"
                , if (item.rMonth / 10 == 0) "0" + item.rMonth else item.rMonth.toString()
                , if (item.rDay / 10 == 0) "0" + item.rDay else item.rDay.toString()))
                .setText(R.id.tv_movie_name, item.title)
                .setText(R.id.tv_want_see, SpanUtils()
                        .append(String.format("%d", item.wantedCount)).setForegroundColor(ContextCompat.getColor(mContext, R.color.orange))
                        .append(String.format("人想看", item.type)).setForegroundColor(ContextCompat.getColor(mContext, R.color.gray))
                        .create())
                .getView<ImageView>(R.id.iv_cover).let { Glide.with(mContext).load(item.image).placeholder(R.drawable.welcome).into(it) }
    }
}