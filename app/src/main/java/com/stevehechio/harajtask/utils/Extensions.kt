package com.stevehechio.harajtask.utils

import android.view.View

/**
 * Created by stevehechio on 7/20/21
 */
fun View.gone(): View {
    if (visibility != View.GONE){
        visibility = View.GONE
    }
    return this
}
fun View.invisible(): View {
    if (visibility != View.INVISIBLE){
        visibility = View.INVISIBLE
    }
    return this
}

fun View.visible(): View {
    if (visibility != View.VISIBLE){
        visibility = View.VISIBLE
    }
    return this
}