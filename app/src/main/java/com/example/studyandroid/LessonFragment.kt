package com.example.studyandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.studyandroid.databinding.FragmentLessonBinding


class LessonFragment : Fragment() {
    private var _binding: FragmentLessonBinding? = null
    private val binding
        get() = _binding!!

    private var lessonId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Nhận lessonId được truyền vào Fragment
        lessonId = arguments?.getInt(ARG_LESSON_ID) ?: -1
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLessonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        when (lessonId) {
            1 -> {
                binding.tvTitle.text = "Bài 1"
                binding.tvContent.text = "Nội dung của bài 1"
            }

            2 -> {
                binding.tvTitle.text = "Bài 2"
                binding.tvContent.text = "Nội dung của bài 2"
            }

            3 -> {
                binding.tvTitle.text = "Bài 3"
                binding.tvContent.text = "Nội dung của bài 3"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    companion object {

        private const val ARG_LESSON_ID = "LESSON_ID"

        fun newInstance(lessonId: Int): LessonFragment {

            val fragment = LessonFragment()

            val bundle = Bundle()
            bundle.putInt(ARG_LESSON_ID, lessonId)

            fragment.arguments = bundle

            return fragment
        }
    }


}