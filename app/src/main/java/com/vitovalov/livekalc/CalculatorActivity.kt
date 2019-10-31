package com.vitovalov.livekalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.vitovalov.livekalc.databinding.ActivityCalculatorBinding

class CalculatorActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(CalculatorViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityCalculatorBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_calculator)

        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
    }
}
