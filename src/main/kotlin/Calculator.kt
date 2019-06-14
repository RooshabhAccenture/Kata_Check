class Calculator {

    fun add(a: Int, b: Int): Int {
        return a + b
    }

}

fun main() {
    val calc = Calculator()
    val x = calc.add(5, 6)
    println("x = $x")

}