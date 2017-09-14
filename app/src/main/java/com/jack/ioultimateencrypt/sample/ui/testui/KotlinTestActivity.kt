package com.jack.ioultimateencrypt.sample.ui.testui

import android.view.View
import android.widget.Spinner
import com.jack.ioultimateencrypt.sample.base.BaseTestActivity
import com.jack.ioultimateencrypt.sample.R
import com.jack.test.logger.Log

/**
 * 2017/8/14.
 * github:[https://github.com/jacky1234]
 * @author  jackyang
 * kotlin 测试类，支持与java混编
 * https://mp.weixin.qq.com/s?__biz=MzA4MTA1NjM5NQ==&mid=2247483684&idx=1&sn=e0ed1445aad9037b8156fda8269d9b7a&chksm=9f9b86c0a8ec0fd6b526538cf79d7310d24489b67e85c2b9a9b9f750330744b5edb39d00215c&mpshare=1&scene=23&srcid=0814r9M20D6tLBZzA7q6GB6p%23rd
 */

class KotlinTestActivity : BaseTestActivity() {

    override fun description() {
        Log.d(TAG, "it is a kotlin test")
    }

    override fun getArrayId(): Int {
        return R.array.kotlin
    }

    override fun onSpinnerItemClick(sp: Spinner?, view: View?, position: Int) {
        when (position) {
            0 -> {

            }
            else -> {

            }
        }
    }

}
