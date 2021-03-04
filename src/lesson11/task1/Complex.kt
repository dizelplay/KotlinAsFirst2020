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
    var s2 = s.replace("i", "")
    if (s.indexOf("-") == 0)
        s2 = s2.removeRange(0, 1)
    val numberList: MutableList<String> = s2.split("+", "-") as MutableList<String>
    if (s.lastIndexOf("-") != -1 && s.lastIndexOf("-") != 0)
        numberList[1] = numberList[1].padStart(numberList[1].length + 1, '-')
    val matchResult = Regex("""-(\d)[+-](\d)""").find(s)
    if (matchResult != null)
        numberList[0] = numberList[0].padStart(numberList[0].length + 1, '-')
    return Complex(numberList[0].toDouble(), numberList[1].toDouble())

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
            "$im$re i"
        else
            "$im+$re i"
    }

    override fun hashCode(): Int {
        var result = re.hashCode()
        result = 31 * result + im.hashCode()
        return result
    }
}
