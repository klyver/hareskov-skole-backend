package com.skolefun.api

import io.swagger.annotations.ApiModelProperty
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import java.util.*


@RestController
class MessageResource {

    class Message(@ApiModelProperty(required = true) var messageText: String,
                  @ApiModelProperty(required = true) var senderName: String,
                  @ApiModelProperty(required = true) var date: LocalDateTime)

    @RequestMapping("/api/messages", method = arrayOf(RequestMethod.GET))
    fun messages() = MutableList(15, { getRandomMessage()})


    fun getRandomMessage(): Message = Message(randomText(40, 5), randomWord(), randomDate())

    fun randBetween(start: Int, end: Int) = start + Math.round(Math.random() * (end - start)).toInt()

    fun randomDate(): java.time.LocalDateTime {
        val year = randBetween(1900, 2010)
        val month = randBetween(1, 12)
        val day = randBetween(1, 27)
        val hour = randBetween(0, 23)
        val min = randBetween(0, 59)
        return LocalDateTime.of(year, month, day, hour, min)
    }

    private fun randomWord() : String {
        val words = listOf("fox", "two", "mercurial", "master", "event", "todo", "version", "ok", "run", "android", "monitor", "Ole")
        return words[Random().nextInt(words.size)]
    }

    fun randomText(maxNumberOfWords: Int, minNumberOfWords: Int): String {
        val sb = StringBuilder("")
        sb.append(randomWord())
        for (i in 0..(Random().nextInt(maxNumberOfWords - minNumberOfWords) + minNumberOfWords)) {
            sb.append(" " + randomWord())
        }
        return sb.toString()
    }


}
