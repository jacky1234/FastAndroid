package com.jack.ioultimateencrypt.sample.ui.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jack.ioultimateencrypt.sample.R
import com.jack.ioultimateencrypt.sample.mvp.model.bean.CatalogBean

/**
 * 2017/8/21.
 * github:[https://github.com/jacky1234]
 * @author  jackyang
 *
 */
class CatalogAdapter(context: Context, list: MutableList<CatalogBean>) : RecyclerView.Adapter<CatalogAdapter.CatalogHolder>() {

    var context: Context? = null
    var list: MutableList<CatalogBean>? = null
    var inflater: LayoutInflater? = null

    init {
        this.context = context
        this.list = list
        this.inflater = LayoutInflater.from(context)
    }


    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: CatalogAdapter.CatalogHolder?, position: Int) {
        var bean = list?.get(position)
        holder?.tv_title?.setText(bean?.simple)

        holder?.itemView?.setOnClickListener { _ ->
            context?.startActivity(Intent(context, Class.forName(bean?.path)))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CatalogAdapter.CatalogHolder? {
        return CatalogHolder(context!!, inflater!!.inflate(R.layout.item_catalog, parent, false))
    }

    class CatalogHolder(context: Context, itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var tv_title: TextView? = null
        var tv_desc: TextView? = null

        init {
            tv_title = itemView?.findViewById(R.id.tv_title) as TextView
            tv_desc = itemView.findViewById(R.id.tv_desc) as TextView
        }
    }
}