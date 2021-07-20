package com.stevehechio.harajtask.repository.data.api

import com.stevehechio.harajtask.di.component.DaggerApiComponent
import com.stevehechio.harajtask.repository.data.model.Car
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by stevehechio on 7/20/21
 */
class NetworkService {
    @Inject
    lateinit var carApi: CarApi

    init {
        DaggerApiComponent.create().inject(this)
    }
    fun  getCars(): Single<List<Car>>{
        return carApi.getCars()
    }


    companion object {
        val BASE_URL = "https://60d6e2fd307c300017a5f556.mockapi.io/hechio/api/vi/"
    }
}