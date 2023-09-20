package com.rxmobileteam.lecture2_3.fraction

import kotlin.math.abs

class Fraction private constructor(
    val numerator: Int,
    val denominator: Int,
) : Comparable<Fraction> {
    // TODO: Implement the decimal value of the fraction
    val decimal: Double = (numerator/denominator).toDouble()

    init {
        // TODO: Check validity of numerator and denominator (throw an exception if invalid)
        if(denominator == 0)
            throw ArithmeticException("denominator = 0 is not compatible")
    }

    //region unary operators
    // TODO: "+fraction" operator
    operator fun unaryPlus(): Fraction {
        return of(+numerator, +denominator)
    }

    // TODO: "-fraction" operator
    operator fun unaryMinus(): Fraction {
        return of(-numerator, -denominator)
    }
    //endregion

    //region plus operators
    // TODO: "fraction+fraction" operator
    operator fun plus(other: Fraction): Fraction {
        // Implement addition of fractions
        val commonDenominator = this.denominator * other.denominator
        val newNumerator = (this.numerator * other.denominator) + (other.numerator * this.denominator)
        return of(newNumerator, commonDenominator)
    }

    // TODO: "fraction+number" operator
    operator fun plus(other: Number): Fraction {
        val commonDenominator = this.denominator
        val newNumerator = (this.numerator) + (other.toInt() * this.denominator)
        return of(newNumerator, commonDenominator)
    }
    //endregion

    //region times operators
    // TODO: "fraction*fraction" operator
    operator fun times(other: Fraction): Fraction {
        val commonDenominator = this.denominator * other.denominator
        val newNumerator = this.numerator * other.numerator
        return of(newNumerator, commonDenominator)
    }

    // TODO: "fraction*number" operator
    operator fun times(number: Number): Fraction {
        val newNumerator = this.numerator * number.toInt()
        return of(newNumerator, this.denominator)
    }
    //endregion

    // TODO: Compare two fractions
    override fun compareTo(other: Fraction): Int {
        val thisNumerator = this.numerator * other.denominator
        val otherNumerator = other.numerator * this.denominator

        return thisNumerator.compareTo(otherNumerator)
    }

    //region toString, hashCode, equals, copy
    // TODO: Format the fraction as a string (e.g. "1/2")
    override fun toString(): String {
        return "${this.numerator}/${this.denominator}"
    }

    // TODO: Implement hashCode
    override fun hashCode(): Int {
        var result = numerator
        result = 31 * result + denominator
        return result
    }

    // TODO: Implement equals
    override fun equals(other: Any?): Boolean {
        if (this === other) return true // Reference equality
        if (other !is Fraction) return false // Check if it's not a Fraction instance

        // Compare properties for equality
        return numerator == other.numerator && denominator == other.denominator
    }

    // TODO: Implement copy
    fun copy(
        numerator: Int = this.numerator,
        denominator: Int = this.denominator
    ): Fraction {
        return of(numerator, denominator)
    }
    //endregion

    companion object {
        @JvmStatic
        fun ofDouble(decimal: Double): Fraction {
            // TODO: Returns a fraction from a decimal number
            val epsilon = 1.0E-6 // A small value to compare with

            var numerator = decimal.toLong()
            var denominator = 1L
            var fractionValue = numerator.toDouble()

            while (abs(fractionValue - decimal) > epsilon) {
                denominator *= 10
                numerator = (decimal * denominator).toLong()
                fractionValue = numerator.toDouble() / denominator.toDouble()
            }
            return Fraction(numerator.toInt(), denominator.toInt())
//            val gcd = findGCD(numerator.toInt(), denominator.toInt())
//            val simplifiedNumerator = numerator / gcd
//            val simplifiedDenominator = denominator / gcd
//
//            return Fraction(simplifiedNumerator.toInt(), simplifiedDenominator.toInt())
        }

        @JvmStatic
        fun ofInt(number: Int): Fraction {
            // TODO: Returns a fraction from an integer number
            return Fraction(number, 1) // Change this
        }

        private fun findGCD(a: Int, b: Int): Int {
            return if (b == 0) a else findGCD(b, a % b)
        }

        @JvmStatic
        fun of(numerator: Int, denominator: Int): Fraction {
            // TODO: Check validity of numerator and denominator
            // TODO: Simplify fraction using the greatest common divisor
            // TODO: Finally, return the fraction with the correct values
            if(denominator == 0)
                throw ArithmeticException("denominator = 0 is not compatible")
            val gcd = findGCD(numerator, denominator)
            val simplifiedNumerator = numerator / gcd
            val simplifiedDenominator = denominator / gcd

            return Fraction(simplifiedNumerator, simplifiedDenominator) // Change this
        }
    }
}

// TODO: return a Fraction representing "this/denominator"
infix fun Int.over(denominator: Int): Fraction = Fraction.of(this, denominator)

//region get extensions
// TODO: get the numerator, eg. "val (numerator) = Fraction.of(1, 2)"
operator fun Fraction.component1(): Int = numerator

// TODO: get the denominator, eg. "val (_, denominator) = Fraction.of(1, 2)"
operator fun Fraction.component2(): Int = denominator

// TODO: get the decimal, index must be 0 or 1
// TODO: eg. "val numerator = Fraction.of(1, 2)[0]"
// TODO: eg. "val denominator = Fraction.of(1, 2)[1]"
// TODO: eg. "val denominator = Fraction.of(1, 2)[2]" should throw an exception
operator fun Fraction.get(index: Int): Int {
    return when (index) {
        0 -> numerator
        1 -> denominator
        else -> throw IllegalArgumentException("Index must be 0 or 1")
    }
}
//endregion

//region to number extensions
// TODO: round to the nearest integer
fun Fraction.toInt(): Int {
    return numerator / denominator
}

// TODO: round to the nearest long
fun Fraction.toLong(): Long = this.toInt().toLong()

// TODO: return the decimal value as a float
fun Fraction.toFloat(): Float {
    return numerator.toFloat() / denominator.toFloat()
}

// TODO: return the decimal value as a double
fun Fraction.toDouble(): Double = numerator.toDouble() / denominator.toDouble()
//endregion

fun main() {
    // Creation
    println("1/2: ${Fraction.of(1, 2)}")
    println("2/3: ${Fraction.of(2, 3)}")
    println("0.7: ${Fraction.ofDouble(0.7)}")
    println("8: ${Fraction.ofInt(8)}")
    println("2/4: ${2 over 4}")

    // Unary operators
    println("+2/4: ${+Fraction.of(2, 4)}")
    println("-2/6: ${-Fraction.of(2, 6)}")

    // Plus operators
    println("1/2 + 2/3: ${Fraction.of(1, 2) + Fraction.of(2, 3)}")
    println("1/2 + 1: ${Fraction.of(1, 2) + 1}")

    // Times operators
    println("1/2 * 2/3: ${Fraction.of(1, 2) * Fraction.of(2, 3)}")
    println("1/2 * 2: ${Fraction.of(1, 2) * 2}")

    // compareTo
    println("3/2 > 2/2: ${Fraction.of(3, 2) > Fraction.of(2, 2)}")
    println("1/2 <= 2/4: ${Fraction.of(1, 2) <= Fraction.of(2, 4)}")
    println("4/6 >= 2/3: ${Fraction.of(4, 6) >= Fraction.of(2, 3)}")

    // hashCode
    println("hashCode 1/2 == 2/4: ${Fraction.of(1, 2).hashCode() == Fraction.of(2, 4).hashCode()}")
    println("hashCode 1/2 == 1/2: ${Fraction.of(1, 2).hashCode() == Fraction.of(1, 2).hashCode()}")
    println("hashCode 1/3 == 3/5: ${Fraction.of(1, 3).hashCode() == Fraction.of(3, 5).hashCode()}")

    // equals
    println("1/2 == 2/4: ${Fraction.of(1, 2) == Fraction.of(2, 4)}")
    println("1/2 == 1/2: ${Fraction.of(1, 2) == Fraction.of(1, 2)}")
    println("1/3 == 3/5: ${Fraction.of(1, 3) == Fraction.of(3, 5)}")

    // Copy
    println("Copy 1/2: ${Fraction.of(1, 2).copy()}")
    println("Copy 1/2 with numerator 2: ${Fraction.of(1, 2).copy(numerator = 2)}")
    println("Copy 1/2 with denominator 3: ${Fraction.of(1, 2).copy(denominator = 3)}")
    println("Copy 1/2 with numerator 2 and denominator 3: ${Fraction.of(1, 2).copy(numerator = 2, denominator = 3)}")

    // Component1, Component2 operators
    val (numerator, denominator) = Fraction.of(1, 2)
    println("Component1, Component2 of 1/2: $numerator, $denominator")
    val (numerator2, _) = Fraction.of(10, 30)
    println("Component1 of 10/30: $numerator2")
    val (_, denominator2) = Fraction.of(10, 79)
    println("Component2 of 10/79: $denominator2")

    // Get operator
    println("Get 0 of 1/2: ${Fraction.of(1, 2)[0]}")
    println("Get 1 of 1/2: ${Fraction.of(1, 2)[1]}")
    println("Get 2 of 1/2: ${runCatching { Fraction.of(1, 2)[2] }}") // Should print "Failure(...)"

    // toInt, toLong, toFloat, toDouble
    println("toInt 1/2: ${Fraction.of(1, 2).toInt()}")
    println("toLong 1/2: ${Fraction.of(1, 2).toLong()}")
    println("toFloat 1/2: ${Fraction.of(1, 2).toFloat()}")
    println("toDouble 1/2: ${Fraction.of(1, 2).toDouble()}")

    // Range
    // Because we implemented Comparable<Fraction>, we can use Fraction in ranges
    val range = Fraction.of(1, 2)..Fraction.of(2, 3)
    println("1/2 in range 1/2..2/3: ${Fraction.of(1, 2) in range}") // "in" operator is contains
    println("2/3 in range 1/2..2/3: ${Fraction.of(2, 3) in range}")
    println("7/12 in range 1/2..2/3: ${Fraction.of(7, 12) in range}")
}