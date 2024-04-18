package task

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import java.time.Duration

abstract class AbstractPageTest(
    protected var url: String
) {
    lateinit var driver: WebDriver

    fun WebDriver.openPage(url: String) {
        this[url]
        manage().timeouts().implicitlyWait(Duration.ofSeconds(5))
    }

    fun WebDriver.getTextFromElement(xpath: String): String = findElement(By.xpath(xpath)).text

    fun WebDriver.clickElement(xpath: String) = clickElement(By.xpath(xpath))

    fun WebDriver.clickElement(by: By) = findElement(by).click()

    private fun WebDriver.timeout(seconds: Duration): WebDriver.Timeouts = manage().timeouts().implicitlyWait(seconds)

    fun WebDriver.timeout(seconds: Long) = timeout(Duration.ofSeconds(seconds))

    fun WebDriver.findElements(xpath: String): List<WebElement> = findElements(By.xpath(xpath))

    fun WebDriver.sendKeys(
        xpath: By,
        key: String,
    ) = findElement(xpath).sendKeys(key)
}
