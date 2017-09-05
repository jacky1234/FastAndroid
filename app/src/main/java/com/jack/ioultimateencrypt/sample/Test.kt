package com.jack.ioultimateencrypt.sample

import android.content.Context
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import java.util.ArrayList

/**
 * 2017/8/29.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

class Test {
    fun test(context: Context) {
        val recyclerView = RecyclerView(context)

        if (recyclerView.layoutManager is LinearLayoutManager) {
            (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
        }

        Handler().postDelayed({ }, 200)

        val list = ArrayList<String>()
        list.add("1")


        val a = 100
        val s = a.toString()
    }
}
