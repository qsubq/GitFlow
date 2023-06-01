package com.example.module5

import java.time.LocalDateTime

fun main() {

    val user = User(0, "Name", 18, Type.Full)
    println("Текущее время: ${user.startTime}")
    Thread.sleep(1000)
    println("Текущее время после 1 секунды: ${user.startTime}")

    val listOfUsers = mutableListOf(user).apply {
        add(User(1, "Name2", 20, Type.Full))
        add(User(2, "Name3", 27, Type.Demo))
        add(User(3, "Name4", 25, Type.Demo))
    }

    val fullAccessUser = listOfUsers.filter { it.type == Type.Full }
    println(fullAccessUser)

    val namesOfUsers = listOfUsers.map { it.name }
    println("Первый элемент: ${namesOfUsers.first()}, Последний элемент: ${namesOfUsers.last()}")


    doAction(Action.Login(user))
}

fun updateCash() {

}

enum class Type {
    Demo,
    Full
}

data class User(
    val id: Int,
    val name: String,
    val age: Int,
    val type: Type
) {
    val startTime: LocalDateTime by lazy {
        LocalDateTime.now()
    }
}

private fun User.isAdult(user: User): Boolean {
    return if (user.age >= 18) {
        println("Пользователь взрослый: $user")
        true
    } else {
        println("Пользователь не взрослый: $user")
        false
    }
}

val authCallback = object : AuthCallback {
    override fun authSuccess() {
        println("Авторизация успешна")
    }

    override fun authFailed() {
        println("Авторизация неудачна")
    }
}

interface AuthCallback {
    fun authSuccess()
    fun authFailed()
}

private inline fun auth(user: User, updateCash: () -> Unit) {
    if (user.isAdult(user)) {
        authCallback.authSuccess()
        updateCash()
    } else {
        authCallback.authFailed()
    }
}

sealed class Action {
    object Registration : Action()
    class Login(val user: User) : Action()
    object Logout : Action()
}

fun doAction(action: Action) {
    when (action) {
        is Action.Registration -> {
            println("Registration started")
        }

        is Action.Login -> {
            auth(action.user) {
                println("Login started")
            }
        }

        is Action.Logout -> {
            println("Logout started")
        }
    }
}
