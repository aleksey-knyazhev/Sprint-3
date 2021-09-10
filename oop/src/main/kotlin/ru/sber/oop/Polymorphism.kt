package ru.sber.oop

import kotlin.random.Random

interface Fightable {
    val powerType: String
    var healthPoints: Int
    val damageRoll: Int
        get() = Random.nextInt()



    fun attack(opponent: Fightable): Int
}

class Player(override val powerType: String,
             override var healthPoints: Int,
             val name: String,
             val isBlessed: Boolean): Fightable {

    override fun attack(opponent: Fightable): Int {
        val calculatedDamageRoll = damageRoll
        if (isBlessed)
            calculatedDamageRoll * 2
        opponent.healthPoints -= calculatedDamageRoll // attack уменьшает здоровье оппоненту
        return calculatedDamageRoll                   // Результат функции attack - количество урона
    }
}

open class Monster(override val powerType: String,
                   override var healthPoints: Int,
                   val name: String,
                   val description: String): Fightable {

    override fun attack(opponent: Fightable): Int {
        opponent.healthPoints -= damageRoll // attack уменьшает здоровье оппоненту
        return damageRoll                   // Результат функции attack - количество урона
    }
}

class Goblin(powerType: String,
             healthPoints: Int,
             name: String,
             description: String):
    Monster(powerType,
            healthPoints,
            name,
            description) {
    override val damageRoll: Int
        get() = super.damageRoll / 2
}

fun Monster.getSalutation() = "I need your clothes, your boots and your motorcycle"


