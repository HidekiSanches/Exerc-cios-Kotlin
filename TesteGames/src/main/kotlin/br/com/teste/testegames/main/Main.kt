package br.com.teste.testegames.main

import br.com.teste.testegames.model.InfoJogo
import br.com.teste.testegames.model.Jogo
import br.com.teste.testegames.service.ConsumoApi
import com.google.gson.Gson
import java.util.*

fun main() {
    val leitura = Scanner(System.`in`)
    println("Digite um código de jogo para buscar:")
    val busca: String = leitura.nextLine()

    val buscaJogo = ConsumoApi()
    var jogo: Jogo? = null
    val resultado = runCatching {
        val gson = Gson()
        val infoJogo = gson.fromJson(buscaJogo.buscarJogo(busca), InfoJogo::class.java)

        jogo = Jogo(
            infoJogo.info.title,
            infoJogo.info.thumb,
            null
        )
    }

    resultado.onFailure {
        println("Jogo não encontrado. Teste outro id.")
    }

    resultado.onSuccess {
        println("Deseja inserir uma descrição personalizada? s/n")
        val opcao = leitura.nextLine()

        if (opcao.equals("s", true)) {
            println("Insira a descrição:")

            val descricao = leitura.nextLine()
            jogo?.descricao = descricao
            println(jogo)
            println("Finalizado com sucesso.")
        } else {
            println(jogo)
            println("Finalizado com sucesso.")
        }
    }
}