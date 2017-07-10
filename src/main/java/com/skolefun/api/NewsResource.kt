package com.skolefun.api

import com.skolefun.config.jwt.JwtTokenUserData
import io.swagger.annotations.ApiModelProperty
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.util.Random

enum class NewsItemType {
    ARTICLE, COMMERCIAL
}

data class NewsItem(@ApiModelProperty(required = true) val title: String,
                    val mainText: String,
                    val feedText: String,
                    val picture: String,
                    val mainPicture: String,
                    val mainPictureText: String,
                    val headline: String,
                    val author: String,
                    val newsItemType: NewsItemType)

data class CommercialItem(val dialogTitle: String,
                          val dialogPicture: String,
                          val dialogText: String,
                          val dialogDetailTitle: String,
                          val dialogDetailPicture: String,
                          val dialogDetailText: String,
                          val dialogDetailExtraPictures: List<String>,
                          val newsItem: NewsItem)

@RestController
class NewsResource {

    @RequestMapping("/api/news", method = arrayOf(RequestMethod.GET))
    fun news(jwtTokenUserData: JwtTokenUserData) = MutableList(15, { getRandomNewsItem()})

    @RequestMapping("/api/commercial", method = arrayOf(RequestMethod.GET))
    fun commercial(jwtTokenUserData: JwtTokenUserData) = MutableList(15, { getCommercial()})

}

fun getRandomNewsItem() =
        NewsItem(
                randomText(4, 1),
                randomText(400, 4),
                randomText(150, 0),
                randomPicture(),
                randomPicture(),
                randomText(250, 0),
                randomText(6, 2),
                randomText(8, 2),
                NewsItemType.ARTICLE)

fun getRandomNewsItemCommercial() =
        NewsItem(
                randomText(4, 1),
                randomText(400, 4),
                randomText(150, 0),
                randomPicture(),
                randomPicture(),
                randomText(250, 0),
                randomText(6, 2),
                "SPONSORERET",
                NewsItemType.COMMERCIAL)

fun getCommercial() =
        CommercialItem(
                randomText(3, 1),
                randomPicture(),
                randomText(40, 1),
                randomText(5, 1),
                randomPicture(),
                randomPicture(),
                MutableList(5, { randomPicture()}),
                getRandomNewsItemCommercial())


fun randomText(maxNumberOfWords: Int, minNumberOfWords: Int): String {
    val sb = StringBuilder("")
    sb.append(randomWord())
    for (i in 0..(Random().nextInt(maxNumberOfWords - minNumberOfWords) + minNumberOfWords)) {
        sb.append(" " + randomWord())
    }
    return sb.toString()
}

fun randomPicture() =
    listOf(
            "http://hareskovskole.mrburns.webhot.dk/billeder/7C_small.JPG",
            "http://hareskovskole.mrburns.webhot.dk/billeder/Haandbold2015_small.jpg",
            "http://hareskovskole.mrburns.webhot.dk/haresk4.jpg"
    )[Random().nextInt(3)]

private fun randomWord() : String {
    val words = listOf("fox", "two", "mercurial", "master", "event", "todo", "version", "ok", "run", "android", "monitor", "Ole")
    return words[Random().nextInt(words.size)]
}
