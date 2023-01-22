package org.firstinspires.ftc.teamcode.api.plugins

import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.teamcode.arch.base.Context
import org.firstinspires.ftc.teamcode.arch.base.Plugin
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

private var wheelsExStore: WheelsEx? = null

val Context.wheels_ex
    get() = wheelsExStore!!

/**
 * An extension to the [Wheels] plugin that enables using the encoders to drive to a certain distance.
 */
class WheelsEx : Plugin() {
    init {
        wheelsExStore = this
    }

    /**
     * Stops the wheels and resets their encoder values.
     */
    private fun stopAndResetEncoders() {
        ctx.wheels.stop()

        ctx.wheels.motor1!!.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        ctx.wheels.motor2!!.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        ctx.wheels.motor3!!.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER

        ctx.wheels.motor1!!.mode = DcMotor.RunMode.RUN_USING_ENCODER
        ctx.wheels.motor2!!.mode = DcMotor.RunMode.RUN_USING_ENCODER
        ctx.wheels.motor3!!.mode = DcMotor.RunMode.RUN_USING_ENCODER
    }
}
