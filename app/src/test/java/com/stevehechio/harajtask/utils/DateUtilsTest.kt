package com.stevehechio.harajtask.utils

import org.junit.Test

import org.junit.Assert.*
import java.util.*

/**
 * Created by stevehechio on 7/20/21
 */
class DateUtilsTest {

    @Test
    fun getDate() {
        val date = 1619708137000L
        val dateString: String? = DateUtils.getDate(date)
        assertEquals("2021/04/29 05:55 PM",dateString)
    }

    @Test
    fun timeSpent() {
        val date = Date().time - 1619708137L
        val dateString: String? = DateUtils.timeSpent(date)
        assertEquals("51 years",dateString)
    }
}