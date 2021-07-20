package com.stevehechio.harajtask.repository.data.api

import com.stevehechio.harajtask.repository.data.model.Car
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by stevehechio on 7/20/21
 */
interface CarApi {
    @GET("car")
    fun getCars(): Single<List<Car>>
}