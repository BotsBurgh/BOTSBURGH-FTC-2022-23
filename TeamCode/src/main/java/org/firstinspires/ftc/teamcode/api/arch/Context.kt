package org.firstinspires.ftc.teamcode.api.arch

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode

data class Context(val teleop: LinearOpMode, val cfg: Config)

typealias CtxFunc = (ctx: Context) -> Unit
