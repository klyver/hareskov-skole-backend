package com.skolefun.api

import io.swagger.annotations.ApiModelProperty
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class PaymentResource {

    enum class TransactionType {
        SEND, RECEIVED
    }
    class Payment(@ApiModelProperty(required = true) var toUsername: String,
                  @ApiModelProperty(required = true) var transactionType: TransactionType,
                  @ApiModelProperty(required = true) var amount: Int)

    @RequestMapping("/api/payments", method = arrayOf(RequestMethod.GET))
    fun payments() = listOf(
            Payment("ABCVærksted", TransactionType.RECEIVED, 500),
            Payment("Restaurant QZ", TransactionType.SEND, 20),
            Payment("Leje af mal", TransactionType.SEND, 10),
            Payment("Kasino", TransactionType.RECEIVED, 32),
            Payment("Snack", TransactionType.RECEIVED, 11),
            Payment("ABCVærksted", TransactionType.RECEIVED, 500),
            Payment("Drink", TransactionType.RECEIVED, 4),
            Payment("something", TransactionType.RECEIVED, 10),
            Payment("something something", TransactionType.RECEIVED, 10)
    )
}
