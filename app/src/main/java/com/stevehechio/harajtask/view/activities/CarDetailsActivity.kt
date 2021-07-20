package com.stevehechio.harajtask.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.stevehechio.harajtask.databinding.ActivityCarDetailsBinding
import com.stevehechio.harajtask.repository.data.model.Car
import com.stevehechio.harajtask.utils.DateUtils.getDate
import java.text.SimpleDateFormat
import java.util.*


class CarDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCarDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViews()

    }


    private fun setUpViews() {
        val car = intent.getSerializableExtra(ARG_CAR) as? Car
        if (car != null) {
            Glide.with(this).load(car.thumbURL).centerCrop().into(binding.ivCar)
            binding.tvUsername.text = car.username
            binding.tvLocation.text = car.city
            binding.tvName.text = car.title
            binding.tvDate.text = getDate(car.date)
            binding.tvBody.text = car.body

        }


    }


    companion object {
        const val ARG_CAR: String = "CarDetailsActivity.Car"
    }

    fun onShare(view: View) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, binding.tvName.text)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)

    }
}