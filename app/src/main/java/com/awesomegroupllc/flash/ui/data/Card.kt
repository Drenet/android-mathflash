package com.awesomegroupllc.flash.ui.data

import com.awesomegroupllc.flash.model.Operand

data class Card(
    val value1: Int,
    val value2: Int,
    val operand: Operand,
    val correctAnswer: Int,
    val availableAnswers: List<Int> = emptyList()
)