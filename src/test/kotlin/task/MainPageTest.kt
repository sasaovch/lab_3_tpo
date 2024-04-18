package task

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.openqa.selenium.By

class MainPageTest : PageTestMultipleBrowsers("https://market.yandex.ru") {
    @ParameterizedTest
    @MethodSource("browserProvider")
    fun `main page grid`(browser: Browser) {
        browserSetup(browser)
        driver.run {
            val elements = findElements("//h2[contains(.,\'Рекомендованные товары\')]")
            assert(elements.isNotEmpty())
        }
    }

    @ParameterizedTest
    @MethodSource("browserProvider")
    fun `main page headers`(browser: Browser) {
        browserSetup(browser)
        driver.run {
            val elements = findElements("//span[contains(.,\'Любимая категория\')]")
            assert(elements.isNotEmpty())
        }
        driver.run {
            val elements = findElements("//span[contains(.,\'Дом\')]")
            assert(elements.isNotEmpty())
        }
        driver.run {
            val elements = findElements("//span[contains(.,\'Одежда\')]")
            assert(elements.isNotEmpty())
        }
    }

    @ParameterizedTest
    @MethodSource("browserProvider")
    fun `find in search line`(browser: Browser) {
        browserSetup(browser)
        driver.run {
            clickElement(By.id("header-search"))
            clickElement("//span[contains(.,\'Найти\')]")
            sendKeys(By.id("header-search"), "iphone")
            clickElement("//span[contains(.,\'Найти\')]")
        }
        driver.run {
            timeout(5)
            assertThat(
                getTextFromElement("//div[@id=\'/content/page/fancyPage/cms/1/SearchTitleWithBreadcrumbs-SearchTitleWithBreadcrumbs\']/div/div/div/h1"),
            ).isEqualTo("Iphone")
        }
    }
}
