package com.jack.ioultimateencrypt.sample.ui.fragment

import android.support.v7.widget.GridLayoutManager
import com.jack.ioultimateencrypt.sample.R
import com.jack.ioultimateencrypt.sample.base.BaseFragment
import com.jack.ioultimateencrypt.sample.mvp.contract.CatalogCostract
import com.jack.ioultimateencrypt.sample.mvp.model.bean.CatalogBean
import com.jack.ioultimateencrypt.sample.mvp.present.CatalogPresent
import com.jack.ioultimateencrypt.sample.ui.adapter.CatalogAdapter
import kotlinx.android.synthetic.main.fragment_catelog.*
import java.util.*

/**
 * 2017/8/21.
 * github:[https://github.com/jacky1234]
 * @author  jackyang
 *
 */
class CatalogFragment : BaseFragment(), CatalogCostract.View {

    lateinit var present: CatalogPresent
    var mList = ArrayList<CatalogBean>()
    lateinit var mAdapter: CatalogAdapter

    override fun getLayoutResources(): Int {
        return R.layout.fragment_catelog
    }

    override fun initView() {
        present = CatalogPresent(context, this)
        present.start()

        recyclerView.layoutManager = context.let { GridLayoutManager(it,2) }
        mAdapter = CatalogAdapter(context, mList)
        recyclerView.adapter = mAdapter
    }

    override fun setCatalogs(lists: List<CatalogBean>) {
        mList.addAll(lists)
        mAdapter.notifyDataSetChanged()
    }

}