package br.com.teste.testegames.main

import br.com.teste.testegames.model.Gamer

fun main() {
    val gamer1 = Gamer("Joaquim", "joaquim@gmail.com")
    println(gamer1)

    val gamer2 = Gamer("Thiago", "Thiago@gmail.com", "08/02/2002", "Riku")
    println(gamer2)

    gamer1.let {
        it.dataNasc = "11/11/2014"
        it.usuario = "NickPraQ"
    }.also {
        println(gamer1)
    }
}