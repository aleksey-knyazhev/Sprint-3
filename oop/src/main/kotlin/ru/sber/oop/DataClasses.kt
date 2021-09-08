package ru.sber.oop

// Первый вариант: перенести свойство city в сигнатуру класса.
// Я проверил, user1.equals(user3) при этом возвращает false.
// Но не вполне уверен, что именно в этом заключалось задание, слишком просто.
//data class User(val name: String, val age: Long, var city: String) {
//}

data class User(val name: String, val age: Long) {
    lateinit var city: String

    @Override
    override fun equals(other: Any?): Boolean {
        if (other is User)
            return this.name == other.name && this.age == other.age && this.city == other.city
        return false
    }
}

fun main() {
    val user1 = User("Alex", 13)
    val user2 = user1.copy("Not Alex")
    user1.city = "Omsk"
    val user3 = user1.copy()
    user3.city = "Tomsk"

    println("user1.equals(user3): ${user1.equals(user3)}")
}