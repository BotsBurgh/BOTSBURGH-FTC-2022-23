package org.firstinspires.ftc.teamcode.api.math

import kotlin.math.abs
import kotlin.math.pow

/**
 * A vector of two numbers.
 */
data class Vec2(var x: Double, var y: Double) {
    /**
     * Also known as `splat`, this initializes a Vec2 with both [x] and [y] as the given [n].
     */
    constructor(n: Double) : this(n, n)

    companion object {
        /**
         * All zeroes.
         */
        val ZERO: Vec2 = Vec2(0.0, 0.0)

        /**
         * All ones.
         */
        val ONE: Vec2 = Vec2(1.0, 1.0)
    }

    // Vec2 by Vec2
    operator fun plus(rhs: Vec2) = Vec2(this.x + rhs.x, this.y + rhs.y)
    operator fun minus(rhs: Vec2) = Vec2(this.x - rhs.x, this.y - rhs.y)
    operator fun times(rhs: Vec2) = Vec2(this.x * rhs.x, this.y * rhs.y)
    operator fun div(rhs: Vec2) = Vec2(this.x / rhs.x, this.y / rhs.y)
    operator fun rem(rhs: Vec2) = Vec2(this.x % rhs.x, this.y % rhs.y)

    // Vec2 by Double
    operator fun plus(rhs: Double) = Vec2(this.x + rhs, this.y + rhs)
    operator fun minus(rhs: Double) = Vec2(this.x - rhs, this.y - rhs)
    operator fun times(rhs: Double) = Vec2(this.x * rhs, this.y * rhs)
    operator fun div(rhs: Double) = Vec2(this.x / rhs, this.y / rhs)
    operator fun rem(rhs: Double) = Vec2(this.x % rhs, this.y % rhs)

    // Vec2 by Vec2
    operator fun plusAssign(rhs: Vec2) {
        this.x += rhs.x; this.y += rhs.y
    }

    operator fun minusAssign(rhs: Vec2) {
        this.x -= rhs.x; this.y -= rhs.y
    }

    operator fun timesAssign(rhs: Vec2) {
        this.x *= rhs.x; this.y *= rhs.y
    }

    operator fun divAssign(rhs: Vec2) {
        this.x /= rhs.x; this.y /= rhs.y
    }

    operator fun remAssign(rhs: Vec2) {
        this.x %= rhs.x; this.y %= rhs.y
    }

    // Vec2 by Double
    operator fun plusAssign(rhs: Double) {
        this.x += rhs; this.y += rhs
    }

    operator fun minusAssign(rhs: Double) {
        this.x -= rhs; this.y -= rhs
    }

    operator fun timesAssign(rhs: Double) {
        this.x *= rhs; this.y *= rhs
    }

    operator fun divAssign(rhs: Double) {
        this.x /= rhs; this.y /= rhs
    }

    operator fun remAssign(rhs: Double) {
        this.x %= rhs; this.y %= rhs
    }

    operator fun unaryPlus() = this // No change
    operator fun unaryMinus() = Vec2(-this.x, -this.y)

    /**
     * Returns a vector containing the absolute value of [x] and [y].
     */
    fun abs() = Vec2(abs(this.x), abs(this.y))

    /**
     * Returns a vector where x and y are raised to the power of [n].
     */
    fun pow(n: Int) = Vec2(this.x.pow(n), this.y.pow(n))

    /**
     * Returns a vector where x and y are raised to the power of [n].
     */
    fun pow(n: Double) = Vec2(this.x.pow(n), this.y.pow(n))
}
