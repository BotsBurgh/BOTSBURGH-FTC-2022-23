package org.firstinspires.ftc.teamcode.api.utils.math

import kotlin.math.abs
import kotlin.math.pow

/**
 * A vector of two numbers.
 */
data class Vec2(var x: Double, var y: Double) {
    companion object {
        /**
         * All zeroes.
         */
        val ZERO: Vec2 = Vec2(0.0, 0.0)

        /**
         * All ones.
         */
        val ONE: Vec2 = Vec2(1.0, 1.0)

        /**
         * All negative ones.
         */
        val NEG_ONE: Vec2 = Vec2(-1.0, -1.0)

        /**
         * A unit vector pointing along the positive x axis.
         */
        val UNIT_X: Vec2 = Vec2(1.0, 0.0)

        /**
         * A unit vector pointing along the positive y axis.
         */
        val UNIT_Y: Vec2 = Vec2(0.0, 1.0)

        /**
         * A unit vector pointing along the negative x axis.
         */
        val UNIT_NEG_X: Vec2 = Vec2(-1.0, 0.0)

        /**
         * A unit vector pointing along the negative y axis.
         */
        val UNIT_NEG_Y: Vec2 = Vec2(0.0, 1.0)

        /**
         * This initializes a [Vec2] where [x] and [y] are both [n].
         *
         * (This is shorthand for `Vec2(n, n)`.)
         */
        fun splat(n: Double) = Vec2(n, n)
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

    /**
     * Returns the slope of a vector with `y / x`.
     */
    fun slope() = this.y / this.x
}
