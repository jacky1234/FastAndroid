package com.jack.ioultimateencrypt.sample

import android.content.Context
import android.widget.Toast
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

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

fun Context.showToast(msg:String){
    Toast.makeText(applicationContext,msg,Toast.LENGTH_SHORT).show()
}