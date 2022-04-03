package com.awesomegroupllc.flash.model

import kotlin.random.Random

enum class Operand {
    ADDITION, SUBTRACTION,
//    ADD_SUBTRACT,
    MULTIPLICATION, DIVISION;
//    MULTIPLY_DIVIDE,
//    ALL

    fun List<Operand>.random() =
        Random(System.currentTimeMillis()).nextInt(0, this.size - 1)
}

fun random() = Operand.values().random()

fun randomNot(operand: Operand, allowDivision: Boolean = true) =
    when(operand){
        Operand.ADDITION -> arrayListOf(Operand.SUBTRACTION, Operand.MULTIPLICATION).apply { if(allowDivision) add(
            Operand.DIVISION
        ) }.shuffled().random()
        Operand.SUBTRACTION -> arrayListOf(Operand.ADDITION, Operand.MULTIPLICATION).apply { if(allowDivision) add(
            Operand.DIVISION
        ) }.shuffled().random()
        Operand.MULTIPLICATION -> arrayListOf(Operand.ADDITION, Operand.MULTIPLICATION).apply { if(allowDivision) add(
            Operand.DIVISION
        ) }.shuffled().random()
        Operand.DIVISION -> listOf(Operand.ADDITION, Operand.SUBTRACTION, Operand.MULTIPLICATION).shuffled().random()
    }