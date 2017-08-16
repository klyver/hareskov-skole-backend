package com.skolefun.api

import io.swagger.annotations.ApiModelProperty
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class PaymentResource {

    class CreatePayment(val toGroupId: String, val amount: Int)

    @RequestMapping("/api/payments", method = arrayOf(RequestMethod.GET))
    fun payments() = Mock.payments()

    @RequestMapping("/api/payments", method = arrayOf(RequestMethod.POST))
    fun createPayment(createPayment: CreatePayment): Unit {}

}
