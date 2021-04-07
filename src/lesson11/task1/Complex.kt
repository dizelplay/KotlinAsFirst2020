@file:Suppress("UNUSED_PARAMETER")

package lesson11.task1

/**
 * Класс "комплексное число".
 *
 * Общая сложность задания -- лёгкая, общая ценность в баллах -- 8.
 * Объект класса -- комплексное число вида x+yi.
 * Про принципы работы с комплексными числами см. статью Википедии "Комплексное число".
 *
 * Аргументы конструктора -- вещественная и мнимая часть числа.
 */


fun Complex(s: String): Complex {



    val im = Regex("""[+|-]?[0-9.]*i""").find(s)?.value?.dropLast(1)
    val re = Regex("""^[+|-]?[0-9.]+(?![i])""").find(s)?.value

    return Complex(re?.toDouble() ?: 0.0, im?.toDouble() ?: 0.0)


}

class Complex(val re: Double, val im: Double) {


    /**
     * Конструктор из вещественного числа
     */
    constructor(x: Double) : this(x, 0.0)

    /**
     * Конструктор из строки вида x+yi
     */


    /**
     * Сложение.
     */
    operator fun plus(other: Complex): Complex = Complex(re + other.re, im + other.im)

    /**
     * Смена знака (у обеих частей числа)
     */
    operator fun unaryMinus(): Complex = Complex(-re, -im)

    /**
     * Вычитание
     */
    operator fun minus(other: Complex): Complex = Complex(re - other.re, im - other.im)

    /**
     * Умножение
     */
    operator fun times(other: Complex): Complex = Complex(re * other.re - im * other.im, im * other.re + re * other.im)

    /**
     * Деление
     */
    operator fun div(other: Complex): Complex = Complex(
        (re * other.re + im * other.im) / (other.im * other.im + other.re * other.re),
        (im * other.re - re * other.im) / (other.im * other.im + other.re * other.re)
    )

    /**
     * Сравнение на равенство
     */
    override fun equals(other: Any?): Boolean =
        other is Complex && im == other.im && re == other.re

    /**
     * Преобразование в строку
     */
    override fun toString(): String {
        return if (re < 0)
            "$im${re}i"
        else
            "$im+${re}i"
    }

    override fun hashCode(): Int {
        var result = re.hashCode()
        result = 31 * result + im.hashCode()
        return result
    }
}
