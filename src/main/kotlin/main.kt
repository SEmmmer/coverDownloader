import org.openqa.selenium.*
import org.openqa.selenium.firefox.FirefoxDriver
import java.io.File
import java.net.URL

suspend fun main(args: Array<String>) {
    System.setProperty("webdriver.gecko.driver", "geckodriver")
    println(checkUploader(""))
}

suspend fun coverList(uploader: String): List<String> {

    val driver = FirefoxDriver()
    val covers = mutableListOf<String>()
    try {
        driver.get("file:///Users/yangjinghua/Documents/coverDownloader/video.htm")
//        driver.get("https://www.youtube.com/channel/$uploader/videos")
        driver.executeScript("window.scrollBy(0,1000)")
        driver.findElements(By.id("thumbnail")).toList().forEach {
            covers.add(
                it
                    .getAttribute("href")
                    .replace("https://www.youtube.com/watch?v=", "")
            )
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
//    val url = "https://www.youtube.com/watch?v=$videoName"
    val url = "https://www.youtube.com/watch?v=-wNSFmqhQsU"
    val website = URL(url).readText()
    File("website.txt").writeText(website)
    return true
}
