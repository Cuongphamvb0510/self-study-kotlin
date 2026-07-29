package com.example.studyandroid

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.studyandroid.databinding.FragmentHomeBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(
            inflater,
            container,
            false
        )

        // Click Bài 1
        binding.btnLesson1.setOnClickListener {
            val intent = Intent(
                requireContext(),
                LessonDetailActivity::class.java
            )

            intent.putExtra("LESSON_ID", 1)

            startActivity(intent)
        }

        // Click Bài 2
        binding.btnLesson2.setOnClickListener {
            val intent = Intent(
                requireContext(),
                LessonDetailActivity::class.java
            )

            intent.putExtra("LESSON_ID", 2)

            startActivity(intent)
        }

        // Click Bài 3
        binding.btnLesson3.setOnClickListener {
            val intent = Intent(
                requireContext(),
                LessonDetailActivity::class.java
            )

            intent.putExtra("LESSON_ID", 3)

            startActivity(intent)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }


}