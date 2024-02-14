package br.com.teste.testegames.model

data class InfoJogo(val info: InfoApiShark) {
    override fun toString(): String {
        return info.toString()
    }
}