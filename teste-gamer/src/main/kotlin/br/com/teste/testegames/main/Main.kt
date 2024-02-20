package br.com.teste.testegames.main

import br.com.teste.testegames.model.Gamer
import br.com.teste.testegames.model.InfoJogo
import br.com.teste.testegames.model.Jogo
import br.com.teste.testegames.service.ConsumoApi
import com.google.gson.Gson
import java.util.*

fun main() {
    val leitura = Scanner(System.`in`)
    val gamer = Gamer.criarGamer(leitura)
    println("Cadastro concluido")
    println(gamer)

    do {
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
                gamer.jogosBuscados.add(jogo)
                println(jogo)
            } else {
                gamer.jogosBuscados.add(jogo)
                println(jogo)
            }
        }
        println("Deseja buscar um novo jogo? s/n")
        val resposta = leitura.nextLine()

    } while (resposta.equals("s", true))
    println("Jogos bsucados:")
    println(gamer.jogosBuscados)

    println("Jogos ordenados por titulo:")
    gamer.jogosBuscados.sortBy {
        it?.titulo
    }
    gamer.jogosBuscados.forEach {
        println("Titulo: " + it?.titulo)
    }

    val jogosFlitrados = gamer.jogosBuscados.filter {
        it?.titulo?.contains("batman", true)?: false
    }
    println("Jogos filtrados")
    println(jogosFlitrados)

    println("Deseja excluir algum jogo da lista? s/n")
    val opcao = leitura.nextLine()

    if (opcao.equals("s", true)) {
        println("Informe a posição do jogo:")
        val posicao = leitura.nextInt() - 1
        gamer.jogosBuscados.removeAt(posicao)
    }

    println("Finalizado com sucesso.")
}