package com.jack.ioultimateencrypt.sample

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.widget.Toast
import com.jackyang.android.support.utils.JsonUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.regex.Pattern

/**
 * 2017/8/21.
 * github:[https://github.com/jacky1234]
 * @author  jackyang
 *
 */
fun <T> Observable<T>.applySchedulers(): Observable<T> {
    return subscribeOn(Schedulers.io()).
            unsubscribeOn(Schedulers.io()).
            observeOn(AndroidSchedulers.mainThread())
}

fun <T : Activity> Activity.startActivity(clazz: Class<T>) {
    startActivity(Intent(this, clazz))
}

fun Context.showToast(msg: String) {
    Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
}

fun Context.showToast(resId: Int) {
    Toast.makeText(applicationContext, applicationContext.getString(resId), Toast.LENGTH_SHORT).show()
}

fun Context.color(resId: Int): Int {
    return ContextCompat.getColor(this, resId)
}

fun Pattern.testArray(array: Array<String>) {
    for (s in array) {
        println(s + "->" + matcher(s).matches())
    }
}

fun Any.toJson() = JsonUtils.toJSON(this)

public inline fun <T> Iterable<T>.firstOrNull(predicate: (T) -> Boolean): T? {
    for (element in this) if (predicate(element)) return element
    return null
}