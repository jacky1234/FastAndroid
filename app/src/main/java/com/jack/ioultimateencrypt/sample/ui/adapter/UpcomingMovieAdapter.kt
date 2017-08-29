package com.jack.ioultimateencrypt.sample.ui.adapter

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jack.ioultimateencrypt.sample.R
import com.jack.ioultimateencrypt.sample.mvp.model.bean.UpcomingMovieBean

/**
 * 2017/8/28.
 * github:[https://github.com/jacky1234]
 * @author  jackyang
 *
 */
class UpcomingMovieAdapter(layout: Int, data: MutableList<UpcomingMovieBean.MoviecomingsBean>?) : BaseQuickAdapter<UpcomingMovieBean.MoviecomingsBean, BaseViewHolder>(layout, data) {

    override fun convert(holder: BaseViewHolder?, item: UpcomingMovieBean.MoviecomingsBean?) {
        holder?.setText(R.id.date_tv, String.format("%d月%d日", item?.rMonth, item?.rDay))
                ?.setText(R.id.tv_movie_name, item?.title)
                ?.setText(R.id.tv_want_see, String.format("%d人想看-%s", item?.wantedCount ?: 0, item?.type))
                ?.setText(R.id.tv_actor, String.format("%s/%s", item?.actor1, item?.actor2))

        holder?.getView<ImageView>(R.id.iv_cover).let {
            Glide.with(mContext).load(item?.image).placeholder(R.drawable.welcome).into(it)
        }

        val prePos = holder?.layoutPosition!! - 1
        if (prePos >= 0) {
            if (item?.rDay != getItem(prePos)?.rDay || item?.rMonth != getItem(prePos)?.rMonth) {
                holder?.setVisible(R.id.date_tv, true)
            } else {
                holder?.setVisible(R.id.date_tv, false)
            }
        } else {
            holder?.setVisible(R.id.date_tv, true)
        }

    }
}