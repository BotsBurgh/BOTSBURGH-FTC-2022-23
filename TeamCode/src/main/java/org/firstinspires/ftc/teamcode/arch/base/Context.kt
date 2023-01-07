package org.firstinspires.ftc.teamcode.arch.base

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode

/**
 * A [Context] used to access [Plugin]s and the teleop.
 */
data class Context(val teleop: LinearOpMode)

/**
 * A function that takes a [Context] and returns nothing.
 */
typealias CtxFunc = (ctx: Context) -> Unit
