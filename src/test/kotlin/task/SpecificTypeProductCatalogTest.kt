package task

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class SpecificTypeProductCatalogTest : PageTestMultipleBrowsers(
    "https://market.yandex.ru/catalog--zhenskaia-odezhda/54252047?hid=53543855&glfilter=14805991%3A14805993"
) {
    @ParameterizedTest
    @MethodSource("browserProvider")
    fun `main page headers`(browser: Browser) {
        browserSetup(browser)
        driver.run {
            clickElement("//div[2]/a/div/div")
            clickElement("//div[@id=\'/content/page/fancyPage/cms/1/SearchTitleWithBreadcrumbs-SearchTitleWithBreadcrumbs\']/div/div/div/h1")
            assertThat(
                getTextFromElement("//div[@id=\'/content/page/fancyPage/cms/1/SearchTitleWithBreadcrumbs-SearchTitleWithBreadcrumbs\']/div/div/div/h1"),
            )
                .isEqualTo("Женское нижнее белье")
        }
    }
}
