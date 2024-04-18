package task

import org.junit.jupiter.api.AfterEach
import org.openqa.selenium.Dimension
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import java.util.stream.Stream

abstract class PageTestMultipleBrowsers(
    url: String
) : AbstractPageTest(url) {
    companion object {
        private val browsers = Browser.entries.toTypedArray()

        @JvmStatic
        fun browserProvider(): Stream<Browser> {
            return Stream.of(*browsers)
        }
    }

    protected fun browserSetup(browser: Browser) {
        driver =
            when (browser) {
                Browser.CHROME -> ChromeDriver()
                Browser.FIREFOX -> FirefoxDriver()
            }
        driver.run {
            manage().window().size = Dimension(1728, 1079)
            this.get(url)
            timeout(5)
        }
    }

    @AfterEach
    fun tearDown() {
        driver.quit()
    }
}

enum class Browser {
    CHROME,
    FIREFOX,
}
