package ru.sber.oop

open class Room(val name: String, val size: Int) {
    constructor(name: String): this(name, 100)

    protected open val dangerLevel = 5

    fun description() = "Room: $name"
    open fun load() = "Nothing much to see here..."
}

class TownSquare(): Room("Town Square", 1000) {
    @java.lang.Override
    final override fun load() = "Hic sunt dracones"
    override val dangerLevel = super.dangerLevel - 3
}