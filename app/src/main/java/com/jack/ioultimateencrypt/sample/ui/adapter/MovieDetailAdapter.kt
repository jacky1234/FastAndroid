package com.jack.ioultimateencrypt.sample.ui.adapter

import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import com.blankj.utilcode.util.SpanUtils
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jack.ioultimateencrypt.sample.R
import com.jack.ioultimateencrypt.sample.mvp.model.bean.MovieDetailBean
import com.jack.ioultimateencrypt.sample.mvp.model.bean.MovieDetailBean.MovieDetailType

/**
 * 2017/9/23.
 * github:[https://github.com/jacky1234]
 * @author  jackyang
 *
 */
class MovieDetailAdapter(data: MutableList<MovieDetailType>?) : BaseMultiItemQuickAdapter<MovieDetailType, BaseViewHolder>(data) {
    lateinit var mMovieDetailBean: MovieDetailBean  //request error ,需要用Adapter设置errorView

    override fun setNewData(data: MutableList<MovieDetailType>?) {
        if (mMovieDetailBean != null) {
            super.setNewData(data)
        } else throw IllegalStateException("you have not set mMovieDetailBean")
    }

    init {
//        addItemType(MovieDetailBean.MovieDetailType.TYPE_STORY, R.layout.view_mv_detail_header)
        addItemType(MovieDetailBean.MovieDetailType.TYPE_STORY, R.layout.view_mv_detail_story)
        addItemType(MovieDetailBean.MovieDetailType.TYPE_STAFF, R.layout.view_mv_detail_story)
        addItemType(MovieDetailBean.MovieDetailType.TYPE_RANK, R.layout.view_mv_detail_story)

    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun convert(holder: BaseViewHolder, item: MovieDetailType) {
        when (holder.itemViewType) {
            MovieDetailType.TYPE_STORY -> {
                holder.addOnClickListener(R.id.tv_story)
                        .getView<TextView>(R.id.tv_story).let {
                    if (!TextUtils.isEmpty(mMovieDetailBean.content)) {
                        it.text = SpanUtils()
                                .append("剧情：").setForegroundColor(ContextCompat.getColor(mContext, R.color.orange))
                                .append(mMovieDetailBean.content!!).setForegroundColor(R.color.black)
                                .create()
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                            val mExpanded = mMovieDetailBean!!.isExpanded
                            it.maxLines = if (mExpanded) Int.MAX_VALUE else 2
                            it.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0,
                                    if (mExpanded) R.drawable.icon_arrow_64_gray_top else R.drawable.icon_arrow_64_gray_bottom)
                        }
                    } else it.visibility = View.GONE
                }
            }
            MovieDetailType.TYPE_STAFF -> {
                holder.setVisible(R.id.tv_story, false)
            }
            MovieDetailType.TYPE_RANK -> {
                holder.setVisible(R.id.tv_story, false)
            }
        }
    }
}