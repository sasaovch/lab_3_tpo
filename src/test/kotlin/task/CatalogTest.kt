package task

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.openqa.selenium.By

class CatalogTest : PageTestMultipleBrowsers("https://market.yandex.ru") {
    @ParameterizedTest
    @MethodSource("browserProvider")
    fun `open catalog`(browser: Browser) {
        browserSetup(browser)
        driver.run {
            clickElement("//span[contains(.,\'Каталог\')]")
            Thread.sleep(1000)
            assertThat(
                getTextFromElement("//a[contains(text(),\'Женщинам\')]"),
            ).isEqualTo("Женщинам")
        }
    }

    @ParameterizedTest
    @MethodSource("browserProvider")
    fun `open additional categories`(browser: Browser) {
        browserSetup(browser)
        driver.run {
            clickElement(By.id("hamburger"))
            clickElement("//li[6]/span/span")
            assertThat(getTextFromElement("//a[contains(text(),\'Юбки\')]")).isEqualTo("Юбки")
        }
    }
}
