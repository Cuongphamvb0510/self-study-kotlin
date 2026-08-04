package com.example.studyandroid.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.studyandroid.R
import com.example.studyandroid.activity.AddCourseActivity
import com.example.studyandroid.activity.CourseDetailActivity
import com.example.studyandroid.adapter.CourseAdapter
import com.example.studyandroid.databinding.FragmentHomeBinding
import com.example.studyandroid.listener.OnCourseClickListener
import com.example.studyandroid.model.Course
import com.example.studyandroid.repository.CourseRepository

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding
        get() = _binding!!

    private val courseList = CourseRepository.courseList
    private var courseAdapter: CourseAdapter? = null

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

        binding.btnAddCourse.setOnClickListener {
            val intent = Intent(
                requireContext(),
                AddCourseActivity::class.java
            )

            startActivity(intent)
        }



        return binding.root
    }


    private fun loadData() {

        courseList.add(
            Course(
                id = 1,
                image = R.mipmap.ic_launcher,
                title = "Android Kotlin",
                price = "Miễn phí",
                rating = 4.5f,
                totalReview = 320,
                lesson = 20,
                duration = "5h 19m"
            )
        )

        courseList.add(
            Course(
                id = 2,
                image = R.mipmap.ic_launcher,
                title = "Java Core",
                price = "299.000đ",
                rating = 4.8f,
                totalReview = 120,
                lesson = 18,
                duration = "4h 30m"
            )
        )

        courseList.add(
            Course(
                id = 3,
                image = R.mipmap.ic_launcher,
                title = "Flutter",
                price = "499.000đ",
                rating = 4.9f,
                totalReview = 200,
                lesson = 25,
                duration = "8h 15m"
            )
        )

        courseList.add(
            Course(
                id = 4,
                image = R.mipmap.ic_launcher,
                title = "React Native",
                price = "699.000đ",
                rating = 4.7f,
                totalReview = 520,
                lesson = 32,
                duration = "10h 20m"
            )
        )
        courseList.add(
            Course(
                id = 5,
                image = R.mipmap.ic_launcher,
                title = "React Native",
                price = "699.000đ",
                rating = 4.7f,
                totalReview = 520,
                lesson = 32,
                duration = "10h 20m"
            )
        )
        courseList.add(
            Course(
                id = 6,
                image = R.mipmap.ic_launcher,
                title = "React Native",
                price = "699.000đ",
                rating = 4.7f,
                totalReview = 520,
                lesson = 32,
                duration = "10h 20m"
            )
        )
        courseList.add(
            Course(
                id = 7,
                image = R.mipmap.ic_launcher,
                title = "React Native",
                price = "699.000đ",
                rating = 4.7f,
                totalReview = 520,
                lesson = 32,
                duration = "10h 20m"
            )
        )
        courseList.add(
            Course(
                id = 8,
                image = R.mipmap.ic_launcher,
                title = "React Native",
                price = "699.000đ",
                rating = 4.7f,
                totalReview = 520,
                lesson = 32,
                duration = "10h 20m"
            )
        )
        courseList.add(
            Course(
                id = 9,
                image = R.mipmap.ic_launcher,
                title = "React Native",
                price = "699.000đ",
                rating = 4.7f,
                totalReview = 520,
                lesson = 32,
                duration = "10h 20m"
            )
        )
        courseList.add(
            Course(
                id = 10,
                image = R.mipmap.ic_launcher,
                title = "React Native",
                price = "699.000đ",
                rating = 4.7f,
                totalReview = 520,
                lesson = 32,
                duration = "10h 20m"
            )
        )
        courseList.add(
            Course(
                id = 11,
                image = R.mipmap.ic_launcher,
                title = "React Native",
                price = "699.000đ",
                rating = 4.7f,
                totalReview = 520,
                lesson = 32,
                duration = "10h 20m"
            )
        )

    }

    private fun setupRecyclerView() {
        courseAdapter = CourseAdapter(courseList, object : OnCourseClickListener {
            override fun onCourseClick(course: Course) {
                val intent = Intent(requireContext(), CourseDetailActivity::class.java)
                intent.putExtra("courseId", course.id)
                startActivity(intent)
            }
        })
        binding.rvCourse.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvCourse.adapter = courseAdapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadData()

        setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()
//        courseAdapter?.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        courseAdapter = null
        _binding = null
    }

//    override fun onCourseClick(course: Course) {
//        val intent = Intent(requireContext(), CourseDetailActivity::class.java)
//        intent.putExtra("courseId", course.id)
//        startActivity(intent)
//    }


}