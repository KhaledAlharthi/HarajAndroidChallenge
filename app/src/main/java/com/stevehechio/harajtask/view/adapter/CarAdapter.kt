package com.stevehechio.harajtask.view.adapter

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stevehechio.harajtask.databinding.ItemCarBinding
import com.stevehechio.harajtask.repository.data.model.Car
import com.stevehechio.harajtask.utils.DateUtils.timeSpent
import java.util.*
import kotlin.math.ceil

/**
 * Created by stevehechio on 7/20/21
 */
class CarAdapter(private val carList: List<Car>?) : ListAdapter<Car,CarAdapter.CarViewHolder>(CarDiffUtil()){
    private var mContext: Context? = null
    var onShowDetailsClickListener: OnShowDetailsClickListener? = null

    fun setContext(mContext: Context?){
        this.mContext = mContext
    }

    fun setOnShowDetailsClickedListener(onShowDetailsClickListener: OnShowDetailsClickListener?){
        this.onShowDetailsClickListener = onShowDetailsClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        return CarViewHolder(ItemCarBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        ))
    }
    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        getItem(position).let { holder.bindViews(it) }
    }


    inner class CarViewHolder(private val binding: ItemCarBinding) : RecyclerView.ViewHolder(binding.root){
        val timerHandler = Handler(Looper.getMainLooper())
        var intervalTimeHandler: Long = 1000

        init {
            val shapeAppearanceModel = binding.ivCar.shapeAppearanceModel.toBuilder()
                .setAllCornerSizes(12f)
                .build()
            binding.ivCar.shapeAppearanceModel = shapeAppearanceModel

        }

        fun bindViews(car: Car){
            mContext?.let { Glide.with(it).load(car.thumbURL).centerCrop().into(binding.ivCar) }
            binding.tvUsername.text = car.username
            binding.tvLocation.text = car.city
            binding.tvName.text = car.title

            val timerRunnable: Runnable = object : Runnable {
                override fun run() {
                    timerHandler.postDelayed(this, intervalTimeHandler)
                    val timePassed: Long = Date().time - car.date
                    binding.tvDate.text = "Since ${timeSpent(timePassed)}"
                }
            }
            timerHandler.postDelayed(timerRunnable, intervalTimeHandler)
            binding.root.setOnClickListener{
                onShowDetailsClickListener?.onShowDetailClicked(car)
            }
        }


    }

interface OnShowDetailsClickListener {
    fun onShowDetailClicked(car: Car)
}
}

class CarDiffUtil: DiffUtil.ItemCallback<Car>(){
    override fun areItemsTheSame(oldItem: Car, newItem: Car): Boolean {
        return oldItem.username == newItem.username
    }

    override fun areContentsTheSame(oldItem: Car, newItem: Car): Boolean {
        return newItem == oldItem
    }

}