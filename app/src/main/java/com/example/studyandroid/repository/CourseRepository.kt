package com.example.studyandroid.repository

import com.example.studyandroid.model.Course

object CourseRepository {

    val courseList = mutableListOf<Course>()
    var pendingSuccessMessage: String? = null

    fun getCourseById(id: Int): Course? {
        return courseList.find { it.id == id }
    }

    fun addCourse(course: Course) {
        courseList.add(0, course)
    }

    fun nextId(): Int {
        return (courseList.maxOfOrNull { it.id } ?: 0) + 1
    }
}