package com.jetbrains.handson.mpp.sentiaandroidcodechallenge.ui.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.jetbrains.handson.mpp.sentiaandroidcodechallenge.R
import com.jetbrains.handson.mpp.sentiaandroidcodechallenge.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        )
    }
}