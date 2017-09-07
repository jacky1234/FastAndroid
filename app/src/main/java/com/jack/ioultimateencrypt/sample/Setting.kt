package com.jack.ioultimateencrypt.sample

import com.blankj.utilcode.util.Utils
import com.jack.ioultimateencrypt.sample.utils.SpUtils

/**
 * 2017/9/7.
 * github:[https://github.com/jacky1234]
 * @author  jackyang
 *
 */
class Setting {

    companion object {

        @JvmStatic
        var isInTestMode: Boolean? = null   //?说明可为null，相当于包装类型

        @JvmStatic
        fun updateSetting() {
            isInTestMode = SpUtils.getInstance(Utils.getApp()).isInTestMode()
        }
    }
}