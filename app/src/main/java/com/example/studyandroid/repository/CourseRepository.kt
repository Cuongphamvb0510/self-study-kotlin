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

    fun updateCourse(course: Course) {
        val index = courseList.indexOfFirst { it.id == course.id }
        if (index != -1) {
            courseList[index] = course
        }
    }

    fun deleteCourse(courseId: Int) {
        courseList.removeAll { it.id == courseId }
    }
}