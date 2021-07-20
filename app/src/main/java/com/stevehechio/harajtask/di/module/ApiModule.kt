package com.stevehechio.harajtask.di.module

import com.stevehechio.harajtask.repository.data.api.CarApi
import com.stevehechio.harajtask.repository.data.api.NetworkService
import com.stevehechio.harajtask.repository.data.model.Car
import com.stevehechio.harajtask.view.adapter.CarAdapter
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by stevehechio on 7/20/21
 */

@Module
class ApiModule {

    @Provides
    fun provideCarApi(): CarApi {
        return Retrofit.Builder()
            .baseUrl(NetworkService.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(CarApi::class.java)
    }


    @Provides
    fun provideNetworkService(): NetworkService {
        return NetworkService()
    }

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    fun provideCarList(): List<Car> {
        return emptyList()
    }

    @Provides
    fun provideCarAdapter(item: List<Car>?): CarAdapter {
        return CarAdapter(item)
    }

}