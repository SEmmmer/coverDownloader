import okhttp3.*
import okhttp3.HttpUrl.get
import org.openqa.selenium.*
import org.openqa.selenium.firefox.FirefoxDriver
import java.io.File
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.nio.file.Files

suspend fun main(args: Array<String>) {
    System.setProperty("webdriver.gecko.driver", "geckodriver")
//    println(localExam("aaa"))
    downloadCover("")
}

suspend fun coverList(uploader: String): List<String> {

    val driver = FirefoxDriver()
    val covers = mutableListOf<String>()
    try {
        driver.get("file:///Users/yangjinghua/Documents/coverDownloader/video.htm")
//        driver.get("https://www.youtube.com/channel/$uploader/videos")
        driver.executeScript("window.scrollBy(0,1000)")
        driver.findElements(By.id("thumbnail")).toList().forEach {
            covers.add(it.getAttribute("href"))
        }
    } finally {
        driver.quit()
        return covers
    }
}


fun localExam(videoName: String): Boolean {
    val bufferPath = "buffer/$videoName"
    return File(bufferPath).exists()
}


suspend fun downloadCover(videoName: String, videoTime: String) {
    val url = "https://img.youtube.com/vi/$videoName/maxresdefault.jpg"
    val data = URL(url).readBytes()
    File("buffer/$videoName.jpg").writeBytes(data)
    File("covers/$videoTime.jpg").writeBytes(data)
}


suspend fun checkUploader(videoName: String): Boolean {
    return true
}

fun videoList(pages: String): List<String> {
    return listOf(
        "aaaa",
        "nnnn"
    )
}