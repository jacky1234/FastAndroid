package com.jack.ioultimateencrypt.sample.utils

import io.reactivex.functions.BiPredicate
import java.util.*

/**
 * 2017/8/24.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

object ContentUtils {
    @Throws(Exception::class)
    fun <T> filter(totalList: List<T>, nonFilterList: List<T>, predicate: BiPredicate<T, T>): List<T> {
        val copy = ArrayList(totalList)
        for (i in totalList.indices) {
            for (j in nonFilterList.indices) {
                if (predicate.test(totalList[i], nonFilterList[j])) {
                }
            }
        }

        return copy
    }
}
