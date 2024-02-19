package br.com.teste.testegames.model

import kotlin.random.Random

data class Gamer(
    var nome: String,
    var email: String,
) {
    var dataNasc: String? = null
    var usuario: String? = null
        set(value) {
            field = value
            if (idInternal.isNullOrBlank()) {
                criarIdInterno()
            }
        }
    var idInternal: String? = null
        private set

    constructor(nome: String, email: String, dataNasc: String, usuario: String):
            this(nome, email) {
                this.dataNasc = dataNasc
                this.usuario = usuario
                criarIdInterno()
            }

    override fun toString(): String {
        return "Gamer(nome='$nome', email='$email', dataNasc=$dataNasc, usuario=$usuario, idInternal=$idInternal)"
    }

    fun criarIdInterno() {
        val num = Random.nextInt(10000)
        val tag = String.format("%04d", num)

        this.idInternal = "$usuario#$tag"
    }
}
