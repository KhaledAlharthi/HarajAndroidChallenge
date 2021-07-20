package com.stevehechio.harajtask.view.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.stevehechio.harajtask.R
import com.stevehechio.harajtask.databinding.ActivityMainBinding
import com.stevehechio.harajtask.di.component.DaggerApiComponent
import com.stevehechio.harajtask.repository.data.model.Car
import com.stevehechio.harajtask.utils.gone
import com.stevehechio.harajtask.utils.visible
import com.stevehechio.harajtask.view.adapter.CarAdapter
import com.stevehechio.harajtask.viewmodel.CarViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var carAdapter: CarAdapter
    private lateinit var binding: ActivityMainBinding
    private val viewModel: CarViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViews()
    }

    private fun setUpViews() {
        DaggerApiComponent.create().inject(this)
        binding.carRecyclerView.apply {
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            adapter = carAdapter
        }
        carAdapter.setContext(this)
        observeOrderLiveData()
        carAdapter.setOnShowDetailsClickedListener(object : CarAdapter.OnShowDetailsClickListener{
            override fun onShowDetailClicked(car: Car) {
                startActivity(launchDetailsActivity(applicationContext,car))

            }

        })
    }

    fun launchDetailsActivity(context: Context, car: Car): Intent {
        val intent = Intent(context,CarDetailsActivity::class.java)
        intent.putExtra(CarDetailsActivity.ARG_CAR, car)
        return intent
    }
    private fun observeOrderLiveData() {
        observeInProgress()
        observeIsError()
        observeOrderList()
    }

    private fun observeIsError() {
        viewModel.isErrorLiveData.observe(this, {
                isError -> isError.let {
            binding.tvCarFetch.visibility = if (it) View.VISIBLE else View.GONE
        }
        })
    }


    private fun observeInProgress() {
        viewModel.inProgressLiveData.observe(this, {
                isLoading -> isLoading.let {
            if (it){
                binding.tvCarFetch.gone()
                binding.carRecyclerView.gone()
                binding.carFetchProgress.visible()
            }else {
                binding.carFetchProgress.gone()
            }
        }
        })
    }


    private fun observeOrderList() {
        viewModel.carListLiveData.observe(this, {
                allOrders -> allOrders.let {
            binding.carRecyclerView.visible()
            carAdapter.submitList(it)
        }
        })
    }
}