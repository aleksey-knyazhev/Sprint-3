package ru.sber.oop

open class Room(val name: String, val size: Int) {
    constructor(name: String): this(name, 100)
    val monster: Monster = Goblin("soulAche", 42, name, "mom's girl friend's son")

    protected open val dangerLevel = 5

    fun description() = "Room: $name"
    open fun load() = monster.getSalutation()
}

class TownSquare(): Room("Town Square", 1000) {
    @java.lang.Override
    final override fun load() = "Hic sunt dracones"
    override val dangerLevel = super.dangerLevel - 3
}