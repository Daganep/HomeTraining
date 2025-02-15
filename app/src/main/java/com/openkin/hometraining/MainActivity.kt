package com.openkin.hometraining

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.openkin.hometraining.databinding.ActivityMainBinding
import com.openkin.hometraining.ui.MainScreenFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_container, MainScreenFragment())
            .commit()
    }
}
