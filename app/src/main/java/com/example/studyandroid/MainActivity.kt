package com.example.studyandroid

import android.os.Bundle
import com.example.studyandroid.databinding.ActivityMainBinding

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
                    R.id.fragmentContainer, // vị trí cần đặng fragment Nó tương ứng với: android:id="@+id/lessonFragmentContainer"
                    HomeFragment()
                ).commit()
        }

    }
}