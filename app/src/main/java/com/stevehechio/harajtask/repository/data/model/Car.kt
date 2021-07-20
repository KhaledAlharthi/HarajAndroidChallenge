package com.stevehechio.harajtask.repository.data.model

import java.io.Serializable

/**
 * Created by stevehechio on 7/20/21
 */

data class Car(val title: String, val username: String, val thumbURL: String,
               val commentCount:Int, val city: String, val date: Long, val body: String): Serializable