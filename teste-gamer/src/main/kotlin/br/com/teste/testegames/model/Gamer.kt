package br.com.teste.testegames.model

import java.util.*
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

    val jogosBuscados = mutableListOf<Jogo?>()

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

    fun validarEmail(): String {
        val regex = Regex("^[A-Za-z0-9._%+-]+@[A-Zaz0-9.-]+\\.[A-Za-z]{2,6}$")
        if (regex.matches(email)) {
            return email
        } else {
            throw IllegalArgumentException("Email inválido")
        }
    }

    companion object {
        fun criarGamer(leitura: Scanner): Gamer {
            println("Digite o seu nome:")
            val nome = leitura.nextLine()
            println("Digite o seu e-mail:")
            val email = leitura.nextLine()
            println("Deseja completar o cadastro com usuario e data de nascimento? s/n")
            val opcao = leitura.nextLine()

            if (opcao.equals("s", true)) {
                println("Digite a data de nascimento:")
                val dtNasc = leitura.nextLine()
                println("Digite o seu usuario:")
                val usuario = leitura.nextLine()

                return Gamer(nome, email, dtNasc, usuario)
            }

            return Gamer(nome, email)
        }
    }

    init {
        if (nome.isNullOrBlank()) {
            throw IllegalArgumentException("Nome está em branco")
        }
        this.email = validarEmail()
    }
}
