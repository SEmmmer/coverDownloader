import com.google.gson.Gson
import netscape.javascript.JSObject
import org.jsoup.Jsoup
import org.openqa.selenium.*
import org.openqa.selenium.firefox.FirefoxDriver
import java.io.File
import java.net.URL

suspend fun main(args: Array<String>) {
//    val uploader = args[0]
    System.setProperty("webdriver.gecko.driver", "geckodriver")
    timeAndCheck("", "")
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


suspend fun timeAndCheck(videoName: String, uploader: String): String {
//    val url = "https://www.youtube.com/watch?v=$videoName"
    val url = "https://www.youtube.com/watch?v=-wNSFmqhQsU"
    val doc = Jsoup.connect(url).get()
    var i = 1
    doc.select("body script").forEach {
        if (it.toString().contains("window[\"ytInitialData\"]")) {
            val app = it.html()
                .toString()
                .split("window[\"ytInitialPlayerResponse\"] = ")[1]
                .split("if (window.ytcsi)")[0]
                .replace(";", "")
            val json = Gson().toJson(app)
            println(json)
            println(i++)
        }
    }


    val uploadTime = ""
    val startTimeStamp = ""
    val findUploader = ""
//    File("website.html").writeText(doc.toString())
    return uploader
}