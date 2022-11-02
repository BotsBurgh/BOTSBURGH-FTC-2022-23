package org.firstinspires.ftc.teamcode.api.plugins

import org.firstinspires.ftc.teamcode.api.arch.Context
import org.firstinspires.ftc.teamcode.api.arch.Plugin

private val linear_slides_store = LinearSlides()

val Context.linear_slides
    get() = linear_slides_store

/**
 * Plugin for controlling the 2 linear slides on the robot.
 */
class LinearSlides: Plugin() {
}
