package com.example.myapplication

enum class CourseStatus { ACTIVE, COMPLETED }

data class Course(
    val code: String,
    val name: String,
    val status: CourseStatus
)

fun Course.displayInfo(): String = "$code - $name - $status"

fun main() {
    val courses = mutableListOf(
        Course("PAB101", "Mobile App Development", CourseStatus.ACTIVE),
        Course("PAB102", "Kotlin Basics", CourseStatus.COMPLETED),
        Course("PAB103", "Android UI", CourseStatus.ACTIVE)
    )

    courses.add(Course("PAB104", "Data Storage", CourseStatus.ACTIVE))
    courses.removeAt(1)

    for (course in courses) {
        println(course.displayInfo())
    }

    val course = courses.first()
    val (code, name, status) = course
    println("Destructuring: $code - $name - $status")
}