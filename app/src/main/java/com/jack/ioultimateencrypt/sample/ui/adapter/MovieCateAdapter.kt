package com.jack.ioultimateencrypt.sample.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jack.ioultimateencrypt.sample.R
import com.jack.ioultimateencrypt.sample.mvp.model.bean.MovieCataBean

/**
 * 2017/8/31.
 * github:[https://github.com/jacky1234]
 * @author  jackyang
 *
 */
class MovieCateAdapter(layout: Int, data: MutableList<MovieCataBean>) : BaseQuickAdapter<MovieCataBean, BaseViewHolder>(layout, data) {

    override fun convert(holder: BaseViewHolder?, item: MovieCataBean?) {
        holder?.setChecked(R.id.checkbox, item?.bChecked!!)
                ?.setText(R.id.checkbox, item?.desc)
                ?.addOnClickListener(R.id.checkbox)
    }
}