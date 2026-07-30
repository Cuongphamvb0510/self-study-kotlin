package com.example.studyandroid.activity

import android.os.Bundle
import com.example.studyandroid.R
import com.example.studyandroid.databinding.ActivityMainBinding
import com.example.studyandroid.fragment.HomeFragment

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        applySystemBarInsets(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragmentContainer,
                    HomeFragment()
                ).commit()
        }

    }
}