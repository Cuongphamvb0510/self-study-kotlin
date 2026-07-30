import com.example.studyandroid.model.Course

object CourseRepository {

    val courseList = mutableListOf<Course>()

    fun getCourseById(id: Int): Course? {
        return courseList.find { it.id == id }
    }
}