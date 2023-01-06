package org.firstinspires.ftc.teamcode.arch.base

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode

data class Context(val teleop: LinearOpMode)

typealias CtxFunc = (ctx: Context) -> Unit
