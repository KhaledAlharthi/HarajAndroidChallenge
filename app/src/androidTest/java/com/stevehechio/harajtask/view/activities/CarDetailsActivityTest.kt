package com.stevehechio.harajtask.view.activities

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by stevehechio on 7/20/21
 */

@RunWith(AndroidJUnit4::class)
class CarDetailsActivityTest {

    @get:Rule
    val activityScenario = activityScenarioRule<CarDetailsActivity>()

    @Test
    fun appLaunchesSuccessfully(){
        ActivityScenario.launch(CarDetailsActivity::class.java)
    }
}