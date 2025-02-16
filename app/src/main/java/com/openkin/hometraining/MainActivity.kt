package com.openkin.hometraining

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.openkin.hometraining.databinding.ActivityMainBinding
import com.openkin.hometraining.ui.home.HomeFragment
import com.openkin.hometraining.ui.record.RecordFragment
import com.openkin.hometraining.ui.search.SearchFragment
import com.openkin.hometraining.ui.settings.SettingsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_container, HomeFragment())
            .commit()
        binding.bottomNavigation.isVisible = false
        binding.bottomNavTrainings.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, HomeFragment())
                .commit()
        }
        binding.bottomNavSearch.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, SearchFragment())
                .commit()
        }
        binding.bottomNavRecord.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, RecordFragment())
                .commit()
        }
        binding.bottomNavSettings.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, SettingsFragment())
                .commit()
        }
    }
}
