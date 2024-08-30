package com.kaliostech.loginpage

import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.os.Bundle
import com.kaliostech.loginpage.databinding.ActivityDashBoardBinding

class DashBoard : AppCompatActivity() {

    private lateinit var binding: ActivityDashBoardBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }
}