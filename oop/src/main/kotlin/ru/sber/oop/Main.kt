package ru.sber.oop

fun main() {
    val user1 = User("Alex", 13)
    val user2 = user1.copy("Not Alex")
    user1.city = "Omsk"
    val user3 = user1.copy()
    user3.city = "Tomsk"

    println("user1.equals(user3): ${user1.equals(user3)}")
}