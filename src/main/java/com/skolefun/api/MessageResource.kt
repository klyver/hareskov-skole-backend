package com.skolefun.api

import io.swagger.annotations.ApiModelProperty
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import java.util.*


@RestController
class MessageResource {


    data class CreateMessage(val groupId: String, val message: String)

    @RequestMapping("/api/messages", method = arrayOf(RequestMethod.GET))
    fun messages() = Mock.messages()

    @RequestMapping("/api/messages", method = arrayOf(RequestMethod.POST))
    fun messages(createMessage: CreateMessage):Unit {}





}
