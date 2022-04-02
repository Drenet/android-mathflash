package com.awesomegroupllc.flash.model

enum class Difficulty {
    EASY,  // 2 Answers - Right and Wrong (one of the other 3 ops, randomized)
    MEDIUM,  // 4 Answers - Right and 3 Wrongs (two of the other 3 ops, randomly, plus the same op with one of the numbers +1)
    HARD // Type in the answer yourself
}