fun main(args: Array<String>) {
    println("Hello world")
    println(videoList("s"))
}

fun localExam(videoName: String): Boolean {

    return true
}

suspend fun downloadCover(videoName: String): Unit {

}

suspend fun checkUploader(videoName: String): Boolean {
    return true
}

fun videoList(pages: String): Map<Int, String> {
    return mapOf(
        1 to "aaa",
        2 to "bbb"
    )
}