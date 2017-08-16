package com.skolefun.api

import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime

open class Environment(
        val id: String,
        val allowPayments: Boolean,
        val allowMessages: Boolean,
        val name: String,
        val logo: String,
        val shownInOverview: Boolean,
        val messages: List<Message>,

        val type: EnvironmentType,
        val typeGroup: EnvironmentTypeGroup?,
        val typeInstitution: EnvironmentTypeInstitution?
)

class EnvironmentTypeGroup(
        val description: String,
        val location: String,
        val descriptionPicture: String,
        val contactName: String,
        val contactDetails: String
        )

class EnvironmentTypeInstitution(
        val groups: List<Environment>,
        val institutionType: InstitutionType,
        val accountBalance: Int,
        val primaryColor: String,
        val primaryColorDark: String,
        val accentColor: String,

        val payments: List<Payment>,
        val commercials: List<CommercialItem>,
        val news: List<NewsItem>
)

class User(
        val id: String,
        val username: String,
        val type: UserType,
        val primaryEnvironment: Environment,
        val secondaryEnvironments: List<Environment>
)

enum class EnvironmentType {
    GROUP, INSTITUTION
}

enum class InstitutionType {
    SCHOOL, CLUB
}

enum class UserType {
    STUDENT, PARENT, TEACHER
}

class Message(
        @ApiModelProperty(required = true) var id: String,
        @ApiModelProperty(required = true) var messageText: String,
        @ApiModelProperty(required = true) var senderName: String,
        @ApiModelProperty(required = true) var date: LocalDateTime
)

data class NewsItem(
        @ApiModelProperty(required = true) val id: String,
        @ApiModelProperty(required = true) val title: String,
        val mainText: String,
        val feedText: String,
        val picture: String,
        val mainPicture: String,
        val mainPictureText: String,
        val headline: String,
        val author: String,
        val newsItemType: NewsItemType
)


data class CommercialItem(
        val id: String,
        val dialogTitle: String,
        val dialogPicture: String,
        val dialogText: String,
        val dialogDetailTitle: String,
        val dialogDetailPicture: String,
        val dialogDetailText: String,
        val dialogDetailExtraPictures: List<String>,
        val newsItem: NewsItem
)

enum class TransactionType {
    SEND, RECEIVED
}

class Payment(
        @ApiModelProperty(required = true) val id: String,
        @ApiModelProperty(required = true) val groupId: String,
        @ApiModelProperty(required = true) var groupName: String,
        @ApiModelProperty(required = true) var transactionType: TransactionType,
        @ApiModelProperty(required = true) var amount: Int
)



