package com.stevehechio.harajtask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stevehechio.harajtask.di.component.DaggerApiComponent
import com.stevehechio.harajtask.repository.data.api.NetworkService
import com.stevehechio.harajtask.repository.data.model.Car
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by stevehechio on 7/20/21
 */
class CarViewModel: ViewModel() {
    @Inject
    lateinit var networkService: NetworkService

    @Inject
    lateinit var compositeDisposable: CompositeDisposable

    init {
        DaggerApiComponent.create().inject(this)
        getAllOrders()
    }

    private fun getAllOrders() {
        compositeDisposable.add(
            networkService.getCars()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { it }
                .subscribeWith(createCarObserver())
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    private fun createCarObserver(): DisposableSingleObserver<in List<Car>>{
        return object : DisposableSingleObserver<List<Car>>(){
            override fun onSuccess(t: List<Car>) {
                inProgress.value = true
                isError.value = false
                carList.value = t
                inProgress.value = false
            }

            override fun onError(e: Throwable) {
                inProgress.value = true
                isError.value = true
                Timber.e("Fetch order error ${e.message}")
                inProgress.value = false
            }

        }
    }


    private val carList by lazy { MutableLiveData<List<Car>>() }
    val carListLiveData: LiveData<List<Car>>
        get() = carList

    private val inProgress by lazy { MutableLiveData<Boolean>() }
    val inProgressLiveData: LiveData<Boolean>
        get() = inProgress

    private val isError by lazy { MutableLiveData<Boolean>() }
    val isErrorLiveData: LiveData<Boolean>
        get() = isError
}