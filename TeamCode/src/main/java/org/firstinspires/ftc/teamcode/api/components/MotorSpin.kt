package org.firstinspires.ftc.teamcode.api.components

import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.teamcode.api.arch.Component
import org.firstinspires.ftc.teamcode.api.arch.Context
import org.firstinspires.ftc.teamcode.api.arch.OpMode
import org.firstinspires.ftc.teamcode.api.plugins.wheels
import kotlin.math.*

class MotorSpin: Component() {

    private var motor1: DcMotor? = null

    override val pre = fun(ctx: Context) {
        this.motor1 = ctx.teleop.hardwareMap.get(DcMotor::class.java, "linearSlide1")
    }

    override val cycle = fun(ctx:Context) {
        if (ctx.teleop.gamepad1.a) {
            motor1?.power = 1.0
        } else if (ctx.teleop.gamepad1.b) {
            motor1?.power = -1.0
        } else {
            motor1?.power = 0.0
        } // motor spins
    }
}