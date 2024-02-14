package br.com.teste.testegames.model

data class Jogo(
    val titulo: String,
    val capa: String,
    var descricao: String?
) {
    override fun toString(): String {
        return "Jogo{titulo=$titulo, capa=$capa, descricao=$descricao}"
    }
}