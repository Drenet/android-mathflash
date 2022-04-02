package com.awesomegroupllc.flash.ui.mvi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.awesomegroupllc.flash.model.Difficulty
import com.awesomegroupllc.flash.model.Operand
import com.awesomegroupllc.flash.model.randomNot
import com.awesomegroupllc.flash.ui.data.Card
import com.awesomegroupllc.flash.util.FlashPrefs
import kotlin.random.Random

const val CARD_STACK_REGEN_SIZE = 4
const val CARD_STACK_NOMINAL_SIZE = 6

class GameViewModel : ViewModel() {

    // TODO: List of 6
    val cardLiveData = MutableLiveData<Card>()

    val cardStack = arrayListOf<Card>()

    val incorrectStack = arrayListOf<Card>()

    var currentOperand = FlashPrefs.operand

    val randGen = Random(System.currentTimeMillis())

    init {
        cardLiveData.value = generateCard(Operand.ADDITION)
        generateCards()
    }

    fun changeOperand(operand: Operand){
        FlashPrefs.operand = operand
        currentOperand = FlashPrefs.operand
        cardStack.clear()
        incorrectStack.clear()
        generateCards()
        cardLiveData.value = cardStack.removeAt(0)
    }

    fun submitAnswer(card: Card, answer: Int) {
        if(answer == card.correctAnswer){
            // Update Streak
            FlashPrefs.streak = FlashPrefs.streak + 1
        } else {
            // Reset Streak
            FlashPrefs.streak = 0
            // Add card to bad stack
            incorrectStack.add(card)
        }

        // Move to next question
        cardLiveData.value = cardStack.removeAt(0)

        // If our stack is < 3, generate new cards. Take from the invalid stack LAST if present
        if(cardStack.size <= CARD_STACK_REGEN_SIZE){
            generateCards()
        }
    }

    fun generateCards(){
        for (i in cardStack.size..CARD_STACK_NOMINAL_SIZE){
            cardStack.add(generateCard(currentOperand))
        }
        if(incorrectStack.size >= 1){
            cardStack.add(incorrectStack.removeAt(0))
        }
    }

    fun generateCard(operand: Operand) : Card {
        // Randomize some stuff:
        val level = FlashPrefs.level
        val difficulty = FlashPrefs.difficulty

        var val1 = randGen.nextInt(level.minRandNumber, level.maxRandNumber)
        var val2 = randGen.nextInt(level.minRandNumber, level.maxRandNumber)

        // We cannot divide by 0, so ensure we aren't attempting that
        if (operand == Operand.DIVISION && val1 == 0) {
            val1 = 1
        }
        if (operand == Operand.DIVISION && val2 == 0) {
            val2 = 1
        }

        val answers = arrayListOf<Int>()
        val correctAnswer = doGoodMath(operand, val1, val2)
        if (difficulty == Difficulty.EASY || difficulty == Difficulty.MEDIUM) {
            // The correct answer
            answers.add(
                correctAnswer
            )
            // At least two incorrect answers.
            answers.add(
                doBadMath(operand, val1, val2, correctAnswer)
            )
            var modifier = 3
            while(answers.size < 3){
                with(doBadMath(operand, val1 + modifier++, val2, correctAnswer)){
                    if(!answers.contains(this)){
                        answers.add(this)
                    }
                }
            }
        }
        if (difficulty == Difficulty.MEDIUM) {
            answers.add(
                doBadMath(operand, val1 - 1, val2, correctAnswer)
            )
            answers.add(
                doBadMath(operand, val1, val2 * 2, correctAnswer)
            )
            answers.add(
                doBadMath(operand, val1, 0, correctAnswer)
            )
        }

        return Card(val1, val2, operand, correctAnswer, answers.distinct().shuffled())
    }

    /**
     * Will always return an INCORRECT answer
     */
    private fun doBadMath(operand: Operand, val1: Int, val2: Int, correctAnswer: Int): Int {
        val badOp = randomNot(operand, (val1 != 0 && val2 != 0))
        val badAnswer = doGoodMath(badOp, val1, val2)
        return if (badAnswer != correctAnswer)
            badAnswer
        else
            correctAnswer * val1 + val2 - val1
    }

    private fun doGoodMath(operand: Operand, val1: Int, val2: Int): Int =
        when (operand) {
            Operand.ADDITION -> val1 + val2
            Operand.SUBTRACTION -> val1 - val2
            Operand.DIVISION -> val1 / val2
            Operand.MULTIPLICATION -> val1 * val2
        }

}