package com.example.studyandroid

import android.os.Bundle
import com.example.studyandroid.databinding.ActivityLessonDetailBinding

class LessonDetailActivity : BaseActivity() {
    private lateinit var binding: ActivityLessonDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLessonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        applySystemBarInsets(binding.root)

        //nhận lessonId truyền sang từ HomeFragment truyền sang
        val lessonId = intent.getIntExtra(
            "LESSON_ID",
            -1
        )

        val fragment = LessonFragment.newInstance(lessonId)

        //nhúng fragment vào trong activity
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.lessonFragmentContainer,
                fragment
            )
            .commit()
    }
}