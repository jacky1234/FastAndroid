package com.jack.ioultimateencrypt.sample

import android.content.Context
import com.jack.ioultimateencrypt.sample.utils.SpUtils
import com.jackyang.android.aop.annotation.Async

/**
 * 2017/9/7.
 * github:[https://github.com/jacky1234]
 * @author  jackyang
 *
 */
class Setting {

    companion object {
        lateinit var appContext: Context
        lateinit var sp: SpUtils

        @JvmField
        var isInTestMode = true   //?说明可为null，相当于包装类型

        @JvmField
        var isDayTimeMode = true  //日间还是夜间模式，true or null->日间

        @Async
        @JvmStatic
        fun updateSetting() {
            isInTestMode = SpUtils.getInstance(appContext).isInTestMode()
        }


        @Async
        @JvmStatic
        fun init(appContext: Context) {
            this.appContext = appContext
            this.sp = SpUtils.getInstance(Companion.appContext)

            if (sp.isInited() == null) {        //? ****
                sp.setIsInited(true)

                //存入默认值
                sp.setIsInTestMode(true)
                sp.setIsInDaytime(true)
            }

            updateSetting()
        }
    }
}