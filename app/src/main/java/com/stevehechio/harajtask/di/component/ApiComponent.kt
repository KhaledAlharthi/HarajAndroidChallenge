package com.stevehechio.harajtask.di.component

import com.stevehechio.harajtask.di.module.ApiModule
import com.stevehechio.harajtask.repository.data.api.NetworkService
import com.stevehechio.harajtask.view.activities.CarDetailsActivity
import com.stevehechio.harajtask.view.activities.MainActivity
import com.stevehechio.harajtask.viewmodel.CarViewModel
import dagger.Component

/**
 * Created by stevehechio on 7/20/21
 */

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(networkService: NetworkService)
    fun inject(mainActivity: MainActivity)

    fun inject(carViewModel: CarViewModel)
}