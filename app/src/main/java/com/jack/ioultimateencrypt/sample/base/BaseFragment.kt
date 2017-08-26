package com.jack.ioultimateencrypt.sample.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * 2017/8/21.
 * github:[https://github.com/jacky1234]
 * @author  jackyang
 *
 */
abstract class BaseFragment : Fragment() {
    var isFirst: Boolean = true
    var rootView: View? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null) {
            rootView = inflater?.inflate(getLayoutResources(), container, false)
        }
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        if (rootView == null) {
            return
        }
        //可见，并且没有加载过
        if (isFirst && isVisibleToUser) {
            isFirst = false
            onLazyLoad()
            return
        }
    }

    open protected fun onLazyLoad() {

    }


    abstract fun getLayoutResources(): Int

    abstract fun initView()
}