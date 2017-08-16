package com.skolefun.api

import java.time.LocalDateTime
import java.util.*

object Mock {

    // lists
    fun messages() = MutableList(15, { message()})

    fun news() = MutableList(15, { newsItem()})

    fun commercials() = MutableList(15, { commercial()})

    fun payments() = listOf(
            Payment("1", "3", "ABCVærksted", TransactionType.RECEIVED, 500),
            Payment("2", "4", "Restaurant QZ", TransactionType.SEND, 20),
            Payment("3", "5", "Leje af mal", TransactionType.SEND, 10),
            Payment("4", "6", "Kasino", TransactionType.RECEIVED, 32),
            Payment("5", "7", "Snack", TransactionType.RECEIVED, 11),
            Payment("6", "3", "ABCVærksted", TransactionType.RECEIVED, 500),
            Payment("7", "8", "Drink", TransactionType.RECEIVED, 4),
            Payment("8", "9", "something", TransactionType.RECEIVED, 10),
            Payment("9", "10", "something something", TransactionType.RECEIVED, 10)
    )

    // objects
    fun groups() = listOf(
            Environment("2", true, true, "Kantine", randomPicture(), true, messages(), EnvironmentType.GROUP, environmentTypeGroup(), null),
            Environment("3",true, true, "ABCVærksted", randomPicture(), true, messages(), EnvironmentType.GROUP, environmentTypeGroup(), null),
            Environment("4",false, true, "3C", randomPicture(), true, messages(), EnvironmentType.GROUP, environmentTypeGroup(), null)
    )

    fun environmentTypeGroup() = EnvironmentTypeGroup("På dette værksted kan du prøve det sjove og nye...", "Aulaen", randomPicture(), "Ulf", "30227049")

    fun user() = com.skolefun.api.User(
            "1",
            "Lille Ib",
            UserType.STUDENT,
            Environment(
                    "1",
                    false,
                    true,
                    "Hareskov skole",
                    randomPicture(),
                    false,
                    messages(),
                    EnvironmentType.INSTITUTION,
                    null,
                    EnvironmentTypeInstitution(
                            groups(),
                            InstitutionType.SCHOOL,
                            300,
                            "#e60000",
                            "#b30000",
                            "#66ff66",
                            payments(),
                            commercials(),
                            news()

                    )
            ),
            listOf()
    )

    fun message(): Message = Message(
            UUID.randomUUID().toString(), randomText(40, 5), randomWord(), randomDate()
    )

    fun newsItem() = NewsItem(
            UUID.randomUUID().toString(),
            randomText(4, 1),
            randomText(400, 4),
            randomText(150, 0),
            randomPicture(),
            randomPicture(),
            randomText(250, 0),
            randomText(6, 2),
            randomText(8, 2),
            NewsItemType.ARTICLE)

    fun newsItemCommercial() =
            NewsItem(
                    UUID.randomUUID().toString(),
                    randomText(4, 1),
                    randomText(400, 4),
                    randomText(150, 0),
                    randomPicture(),
                    randomPicture(),
                    randomText(250, 0),
                    randomText(6, 2),
                    "SPONSORERET",
                    NewsItemType.COMMERCIAL)

    fun commercial() =
            CommercialItem(
                    UUID.randomUUID().toString(),
                    randomText(3, 1),
                    randomPicture(),
                    randomText(40, 1),
                    randomText(5, 1),
                    randomPicture(),
                    randomPicture(),
                    MutableList(5, { randomPicture()}),
                    newsItemCommercial())


    // Random Helpers
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

    fun randomPicture() =
            listOf(
                    "http://hareskovskole.mrburns.webhot.dk/billeder/7C_small.JPG",
                    "http://hareskovskole.mrburns.webhot.dk/billeder/Haandbold2015_small.jpg",
                    "http://hareskovskole.mrburns.webhot.dk/haresk4.jpg"
            )[Random().nextInt(3)]


}

