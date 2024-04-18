package task

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.openqa.selenium.By

class PageWithItemsTest : PageTestMultipleBrowsers(
    "https://market.yandex.ru/catalog--zhenskie-tolstovki-i-svitshoty/54254055/list?hid=53545982&rs=eJwzmsUcwHiUkcEhzRZINtRbg9gvbICkwvvdIBEOkIhCJ0jEgQ1EJrSCxA90gcQfbAGJHOgDkQxSe8AmgGQTHoPV7wSr3Ao2zQAky7AZJMJwGcROsAeRDoEgew9ogdVEg2QfnAazt4PNNDEHifwH27seRC54BLa3ej9ItsECpNIcJN4QCta1FmymB8jMBCYQuYATRD5oAPvOGyT74B_YzRpg3zWDTFtQBbbrF5g8Dya3gWQXzATb-9EKxF4J9tEtEPngA0hXwxOwvWCXM0jsBcmagdUwgO0Ch9WD5WBXSYHZD0G2L6gHsx3B7JtgW5ysTzFymJgaG5mapKZcYuTimPZ919k1m750qDl9YuSS5uLgYBRQkGBUYBRgk-JMSU1LLM0piTdUYNBg4DIES3JLcCowCwhISScnFpcm5sQnJxbllxan5sQbx2eWpOYWx5fkFyj8PB-nsXFqKpceWAsHVItEUmJxZjJCR0FOYmYeWP2av9IaF38mcRlD1YsqsAHVy2JVX5yaWJScEW-k8PyUjsbVlgQuS7AmIagmZXR3FaUWF-TnFWeWpSK07nrHqfH2ZboAIwDOZ9os"
) {
    @ParameterizedTest
    @MethodSource("browserProvider")
    fun `set parameters for filter`(browser: Browser) {
        browserSetup(browser)
        driver.run {
            clickElement("//div[@id=\'/content/page/fancyPage/cms/4/SearchFilters-SearchFilters\']/div/div/div/div/div/div/fieldset/div/div/div/div/span/div/div/input")
            sendKeys(
                By.xpath(
                    "//div[@id=\'/content/page/fancyPage/cms/4/SearchFilters-SearchFilters\']/div/div/div/div/div/div/fieldset/div/div/div/div/span/div/div/input",
                ),
                "500",
            )
            sendKeys(
                By.xpath(
                    "//div[@id=\'/content/page/fancyPage/cms/4/SearchFilters-SearchFilters\']/div/div/div/div/div/div/fieldset/div/div/div/div[2]/span/div/div/input",
                ),
                "1000",
            )
        }
    }

    @ParameterizedTest
    @MethodSource("browserProvider")
    fun `sort products`(browser: Browser) {
        browserSetup(browser)
        driver.run {
            clickElement("//div[@id=\'/content/page/fancyPage/cms/4/SearchControls-SearchControls/sort\']/div/noindex/div/button[3]")
        }
    }
}
