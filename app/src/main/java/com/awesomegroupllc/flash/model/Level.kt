package com.awesomegroupllc.flash.model

enum class Level(val levelName: String, val minRandNumber: Int, val maxRandNumber: Int) {
    ONE("1", 1, 10),
    TWO("2", 0, 30),
    THREE("3", 5, 50),
    FOUR("4", 10, 100),
    FIVE("5", 25, 500)
}