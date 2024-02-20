import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

fun String.tranformarEmIdade(): Int {
    val formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    var idade = 0
    val dtNasc = LocalDate.parse(this, formatador)
    val hoje = LocalDate.now()
    idade = Period.between(dtNasc, hoje).years

    return idade
}