import org.openqa.selenium.*
import org.openqa.selenium.firefox.FirefoxDriver


fun main(args: Array<String>) {
    System.setProperty("webdriver.gecko.driver", "geckodriver")
    val driver = FirefoxDriver()
    try {
        driver.get("https://www.youtube.com/channel/UCdn5BQ06XqgXoAxIhbqw5Rg/videos")
        driver.executeScript("window.scrollBy(0,1000)")
        driver.findElements(By.id("thumbnail")).toList().forEach {
            println(it.getAttribute("href"))
        }

    } finally {
        driver.quit()
    }

}

fun localExam(videoName: String): Boolean {

    return true
}

suspend fun downloadCover(videoName: String): Unit {

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