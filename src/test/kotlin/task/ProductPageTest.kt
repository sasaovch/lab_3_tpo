package task

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class ProductPageTest : PageTestMultipleBrowsers(
    "https://market.yandex.ru/product--elektricheskii-dukhovoi-shkaf-bosch-hsg636xs6/1779302232?hid=90580&show-uid=17134408860224069040116003&from=search&glfilter=7893318%3A14989774&glfilter=38474130%3A40006350&glfilter=38486550%3A40008350&text=%D0%94%D1%83%D1%85%D0%BE%D0%B2%D0%BA%D0%B0%20%D1%8D%D0%BB%D0%B5%D0%BA%D1%82%D1%80%D0%B8%D1%87%D0%B5%D1%81%D0%BA%D0%B0%D1%8F&rs=eJx9UEtoE1EUfS_GOB2lDgUxpFifxcW4S5t-xG_oRtGFrlxUZTKdTsjIJBkyidK4GT9REESRLq1UgujCRaxUpNY2KgUpXcyI7rvpTheKCi78zD0N_hbdHN6c887nTe-ndd3D8i5_PLgU1Pw5f8qf8RsiuO4_86f9meBi4PlPg6v-dHCBhOCGcqv-sKM7KknKlhCZsgHIVH6GVaW-ZDI5kOpPevztq_XXeETix3k1KoUfDc4WOGNf9ofYdAkns89DXJ4i9NoJMxPEsyiYU7Mhpj3gd-IzBqFghN5neB1C5kM9SufmaeTfpHP6JNQFpOX3UuMx3ByDOo-cMWAeyTXcv0I4eQj8iXm6eRhdDdy5RznNLux_A2YzMl_TzszLfaTmwG89QAlDOD_Cu2LkFSuUJjYRk66j8TG67qPlINpHsXkP-PjqNmD7HKkr1JJeRO95vHcn3o79ok6qUIhftmmVt4SuJ7OLXNrdb-ipkdTg0I-I3ClLEldEPCqiSizRNmpm9Ypd1noFU9lvkQv-t9gDsQcihxhJbDccXXNzRUezCkalrJ2zyjnNcPKaWzYLhmXDMgDLxrgsZEVJCEN3K7qtGXqpWHFNW0tpVtnMu5pr6iUjF264_UBWP97Jyin4pJZv24juWsYfm2PrVuFfUzCRlQdh6ojHRCw07fi_rGS6TrHgWmfNllX8fNemfqgl5b7WylVj19orxfuarN79Ntz6HVLL1bnGRnH5a1V9MX5E4b8AD20hAQ%2C%2C&sku=59836861&cpa=1&do-waremd5=aeugjzfChr3WHyG2qqjM8Q&cpc=Lj3q8wya2MvJV-dRcx8tP90QgJ7LQn1s2jbdhQeLzUO7N6mB0wWk9HCOmClG-rqnx7DPiWtoq7FmcFIp90SIwJ6RFkc1zTj3xp7-gCxSk7mTWh-aujPUEzgjwwmumfEf60v3FWyvIxcpujJR3NzOV7UR3A1rehSRAZVxZBvkAIYOHdRJGrcZcCK6dbdLo6g3vjUaM6BVkYqTSD2fi2GLbtLR2ofaUmIyVUq406H2tPnhOdN80S1dy78bKZqqxLiD-cZAAS5TrR1TBLVHmLhgMQ%2C%2C&cc=CjIxNzEzNDQwODg1NjM3LzhjZWZmNDcwMTNmODEwNGRlNzAwNTU4YjVkMTYwNjAwLzEvMRAcgH3m7QY%2C&uniqueId=892629"
) {
    @ParameterizedTest
    @MethodSource("browserProvider")
    fun `add items to bucket check counter`(browser: Browser) {
        browserSetup(browser)
        driver.run {
            clickElement("//*[@id=\"offerActions\"]/div/div[2]/button/span/div/span")
            Thread.sleep(500)
            clickElement("//div[@id=\'offerActions\']/div/div/div/div[2]/button/span")
            Thread.sleep(500)
            clickElement("//div[@id=\'offerActions\']/div/div/div/div[2]/button/span")
            Thread.sleep(500)
            assertThat(getTextFromElement("(//a[contains(@href, \'/my/cart\')])[2]")).isEqualTo("3")
        }
    }

    @ParameterizedTest
    @MethodSource("browserProvider")
    fun `add items to bucket go to bucket`(browser: Browser) {
        browserSetup(browser)
        driver.run {
            clickElement("//*[@id=\"offerActions\"]/div/div[2]/button/span/div/span")
            Thread.sleep(1000)
            clickElement("//div[@id=\'offerActions\']/div/div/div/div[2]/button/span")
            Thread.sleep(1000)
            clickElement("//div[@id=\'offerActions\']/div/div/div/div[2]/button/span")
            Thread.sleep(1000)
        }
        Thread.sleep(1000)
        driver.run {
            val bucketBadge = getTextFromElement("//div[@id=\'CART_ENTRY_POINT_ANCHOR\']/a/div/div[1]/span")
            assertThat(bucketBadge).isEqualTo("3")
            openPage("https://market.yandex.ru/my/cart")
            val productName =
                getTextFromElement("/html/body/div[1]/div/div[4]/div/div/div/div/main/div[2]/div/div[2]/div[1]/div/div/div/ul/li/div/div/div/div[2]/div[1]/div[1]/div/div/div/div/ul/div[1]/div/div/div/div/li/div/article/div[3]/div[1]/div/h3")
            assertThat(productName).isEqualTo("Электрический духовой шкаф Bosch HSG636XS6, серебристый")
        }
    }

    @ParameterizedTest
    @MethodSource("browserProvider")
    fun `check description`(browser: Browser) {
        browserSetup(browser)
        driver.openPage("https://market.yandex.ru/product--nuphy-air96-white-brown-switch/38094912?sku=102572492062&offerid=EqGM79EHiZULowtoj3PyFQ&cpc=b53Zny32cPo0QWG5xdaYLcipTPRMp4XF3J9AwSjFtzK1Y-no_g8OA5ewIVu0TfBkFuaudbtoLydFObo5ceA3tzlfLm02Mb4l7Co_RiRSTzKVGIzrMXRkKahmNwx_sylRfGPT5WJwTLNyyDDk_XwJqOD55TTkofcJXSk6Q7GsuorPH4sSvXx_xQiQUji28LWXQHJZugCuwu5NA4sBpehBv3-5EEDnnEiS&show-uid=17134389311625422315909014&uniqueId=920136&lr=2&")
        driver.run {
            clickElement("//div[@id=\'cardContent\']/div/div/div/div[3]/div[2]/div/div[9]/div/div/div/div[3]/span")
            when (browser) {
                Browser.CHROME -> assertThat(
                    getTextFromElement("//div[@id=\'cardContent\']/div/div/div[3]/div/div/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/h2"),
                ).isEqualTo("О товаре")

                Browser.FIREFOX -> assertThat(
                    getTextFromElement("//div[@id=\'cardContent\']/div/div/div/div[3]/div[2]/div/div[8]/div/div/div/div/div[2]/div/span"),
                ).isEqualTo("Читать далее")
            }
        }

        driver.run {
            val description = when (browser) {
                Browser.CHROME ->
                    getTextFromElement("//div[@id=\'cardContent\']/div/div/div[3]/div/div/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div/div/div/span")

                Browser.FIREFOX ->
                    getTextFromElement("//div[@id=\'cardContent\']/div/div/div/div[3]/div[2]/div/div[8]/div/div/div/div/span")
            }
            val expected = when (browser) {
                Browser.CHROME ->
                    "Механическая клавиатура Nuphy AIR96 White Brown Switch\n" +
                        "Nuphy Air96 - инновационная ультратонкая клавиатура с формата 96% с numpad. Эта клавиатура идёт с комплекте с самыми тонкими в мире PBT сферическими кейкапами, беспроводным соединением на частоте 2.4Ггц с низкой задержкой, а также возможностью подключить по bluetooth, и функций горячей замены переключателей. Air96 сочетает в себе мощный функционал в уменьшенном корпусе и подойдёт для использования как со стационарным компьютером, так и с любым другим устройством поддерживающим bluetooth подключение.\n" +
                        "Тактильные механические переключатели Gateron Low-profile 2.0 Brown в Air96 с коротким ходом великолепно подойдут всем пользователям, которые любят чувствовать тактильную отдачу без явного без кликающего звука.\n" +
                        "Комплектация:\n" +
                        "Клавиатура\n" +
                        "Кабель USB-C на USB-A (1.5м)\n" +
                        "Приёмник 2.4 ГГц\n" +
                        "Съёмник кейкапов/переключателей\n" +
                        "Стикеры\n" +
                        "7 дополнительных кейкапов\n" +
                        "6 дополнительных переключателей\n" +
                        "Инструкция"

                Browser.FIREFOX -> "Механическая клавиатура Nuphy AIR96 White Brown SwitchNuphy Air96 - инновационная ультратонкая клавиатура с формата 96% с numpad. Эта клавиатура идёт ..."
            }
            assertThat(description).isEqualTo(expected)
        }
    }

    @ParameterizedTest
    @MethodSource("browserProvider")
    fun `show images`(browser: Browser) {
        browserSetup(browser)
        driver.openPage("https://market.yandex.ru/product--kartina-po-nomeram-na-kholste-grustnyi-ustavshii-tolstyi-kot-mem-2214/1753750915?sku=101759889050&offerid=suGDBZHXW_UCVLJAPCKVMA&cpc=QeEDm-eG3DPqRgWzq5Aty3RkvQcNEBaknFfizkfOx5tPelqIDWoUtZVR_quJqvrHdMS4AmLASJlNdX8sKWnWOrXfmd08iiiM8Bn8rgJ4t7XpYCujpkKqzeJOwK9UmUCRo4J74kUxwMG9cpnBCFILrdWf3KVY3fkWJ0ILecjUNNLVNIQd-RCf97uOpVKpEzhoBHh_gaS67E0cGXY_SRYA2L4B8KvReGyQ7cFHxk36pu30jSSmmRYpc92uuwXoOMb7gEvMce1tjPrUp1jVT2GQ7e_7RAnvwv7K--kBiJLi5cI,&show-uid=17134673208627870111009006&uniqueId=4704018&lr=2&")
        driver.run {
            val img1 =
                findElements("//img[@src=\'https://avatars.mds.yandex.net/get-mpic/5221441/img_id4079126891707268213.jpeg/600x800\']")
            assert(img1.isNotEmpty())
        }

        driver.run {
            clickElement("//img[@alt=\'Слайд 2 с фото\']")
            timeout(2)
            val img2 =
                findElements("//img[@src=\'https://avatars.mds.yandex.net/get-mpic/5316009/img_id1477626659503103474.jpeg/600x800\']")
            assert(img2.isNotEmpty())
        }

        driver.run {
            clickElement("//img[@alt=\'Слайд 3 с фото\']")
            timeout(2)
            val img3 =
                findElements("//img[@src=\'https://avatars.mds.yandex.net/get-mpic/4120716/img_id1625473042288257384.jpeg/600x800\']")
            assert(img3.isNotEmpty())
        }
    }
}
