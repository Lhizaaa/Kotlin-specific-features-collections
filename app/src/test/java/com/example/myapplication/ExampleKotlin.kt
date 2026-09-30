package com.example.myapplication //Luqman Hakim Ar-Razi (24523222)

import org.junit.Test

enum class CourseStatus {
    ACTIVE, COMPLETED, DROPPED
}

data class Course(
    val code: String,
    val name: String,
    val status: CourseStatus
) {
    companion object {
        const val PREFIX = "PAB"
    }
}

// Latihan 1: Extension Function untuk menampilkan info mata kuliah
fun Course.displayInfo(): String = "$code - $name - $status"

// Latihan 2: Fungsi describe menggunakan when
fun describe(status: CourseStatus): String = when (status) {
    CourseStatus.ACTIVE -> "Currently studying"
    CourseStatus.COMPLETED -> "Completed studies"
    CourseStatus.DROPPED -> "Course dropped"
}

// Latihan 5: Object AppConfig
object AppConfig {
    const val MAX_COURSES = 5
}

// Latihan 5: Extension Function untuk menambah Course ke List
fun MutableList<Course>.addCourse(course: Course): Boolean {
    if (this.size < AppConfig.MAX_COURSES && course.code.startsWith(Course.PREFIX)) {
        this.add(course)
        return true
    }
    return false
}

// UNIT TEST MENJALANKAN LATIHAN 1 - 5

class ExampleKotlin {

    @Test
    fun latihan1_ProgramMataKuliahMahasiswa() {
        println("LATIHAN 1: PROGRAM MATA KULIAH MAHASISWA")
        // 3. Masukkan minimal 3 mata kuliah
        val courses = mutableListOf(
            Course("PAB101", "Mobile App Development", CourseStatus.ACTIVE),
            Course("PAB102", "Artificial Intelligence", CourseStatus.COMPLETED),
            Course("PAB103", "Data Science", CourseStatus.ACTIVE)
        )

        // 4. Tambah 1 mata kuliah, hapus 1 mata kuliah
        courses.add(Course("PAB104", "Machine Learning", CourseStatus.ACTIVE))
        courses.removeAt(1) // Menghapus PAB102

        // Cetak semua mata kuliah tersisa menggunakan displayInfo()
        println("Daftar Mata Kuliah Tersisa:")
        for (course in courses) {
            println(course.displayInfo()) // 5. Extension function
        }

        // 6. Destructuring
        val (code, name, status) = courses[0]
        println("\nHasil Destructuring (Mata Kuliah Pertama):")
        println("Kode: $code | Nama: $name | Status: $status")
    }

    @Test
    fun latihan2_DeskripsiStatus() {
        println("\nLATIHAN 2: DESKRIPSI STATUS")
        val courses = listOf(
            Course("PAB101", "Mobile App Development", CourseStatus.ACTIVE),
            Course("PAB102", "Artificial Intelligence", CourseStatus.COMPLETED),
            Course("PAB103", "Data Science", CourseStatus.DROPPED)
        )

        for (course in courses) {
            println("${course.name}: ${describe(course.status)}")
        }
    }

    @Test
    fun latihan3_TagKeahlianUnik() {
        println("\nLATIHAN 3: TAG KEAHLIAN UNIK")
        val skills = mutableSetOf("Kotlin", "Java")

        skills.add("Python")
        skills.add("Kotlin") // Duplikat, otomatis diabaikan

        println("Ukuran Set: ${skills.size}")
        println("Apakah 'Swift' ada di Set? ${"Swift" in skills}")
        println("Apakah 'Python' ada di Set? ${"Python" in skills}")

        // Penjelasan:
        // Ukuran Set tidak bertambah saat "Kotlin" ditambahkan karena Set hanya menyimpan
        // nilai yang unik. Nilai duplikat secara otomatis diabaikan.
    }

    @Test
    fun latihan4_DaftarNilai() {
        println("\nLATIHAN 4: DAFTAR NILAI")
        val scores = mutableMapOf(
            101 to 85,
            102 to 90,
            103 to 78
        )

        // Perbarui nilai NIM 101
        scores[101] = 95

        // Hapus NIM 103
        scores.remove(103)

        // Cetak entri dengan destructuring for ((nim, score) in scores)
        println("Daftar Nilai Mahasiswa")
        for ((nim, score) in scores) {
            println("NIM: $nim - Nilai: $score")
        }

        // Cetak nilai untuk NIM yang tidak ada di Map
        println("Nilai untuk NIM 999: ${scores[999]}")
        // Catatan: Mengakses key yang tidak ada menghasilkan null
    }

    @Test
    fun latihan5_KonfigurasiAplikasi() {
        println("\nLATIHAN 5: KONFIGURASI APLIKASI")
        val courses = mutableListOf(
            Course("PAB101", "Mobile App", CourseStatus.ACTIVE),
            Course("PAB102", "AI", CourseStatus.COMPLETED),
            Course("PAB103", "Data Science", CourseStatus.ACTIVE),
            Course("PAB104", "Machine Learning", CourseStatus.ACTIVE)
        )

        // 1. Tambah mata kuliah valid (Jumlah < 5 dan prefix "PAB")
        val course1 = Course("PAB105", "Cloud Computing", CourseStatus.ACTIVE)
        println("Tambah PAB105: ${courses.addCourse(course1)}") // true

        // 2. Tambah saat kuota sudah mencapai MAX_COURSES (5)
        val course2 = Course("PAB106", "Cyber Security", CourseStatus.ACTIVE)
        println("Tambah PAB106 (kuota penuh): ${courses.addCourse(course2)}") // false

        // 3. Tambah dengan prefix salah (bukan "PAB")
        val coursesList2 = mutableListOf<Course>()
        val course3 = Course("TIF101", "Web Dev", CourseStatus.ACTIVE)
        println("Tambah TIF101 (prefix salah): ${coursesList2.addCourse(course3)}") // false
    }
}