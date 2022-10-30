package org.firstinspires.ftc.teamcode.api.components

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1
import org.firstinspires.ftc.teamcode.api.arch.Component
import org.firstinspires.ftc.teamcode.api.arch.Context
import kotlin.math.*


class LinearSlides : Component() {

    private var linearSlide1: DcMotor? = null
    private var linearSlide2: DcMotor? = null
    private var claw1: Servo? = null
    private var claw2: Servo? = null

    override val pre = fun(ctx: Context) {
        this.linearSlide1 = ctx.teleop.hardwareMap.get(DcMotor::class.java, "linear, aSlide1")
       /** this.linearSlide2 = ctx.teleop.hardwareMap.get(DcMotor::class.java, "linearSlide2") **/
        this.claw1 = ctx.teleop.hardwareMap.get(Servo::class.java, "claw1")
        linearSlide1?.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE

    }
    override val cycle = fun(ctx: Context) {


        if (ctx.teleop.gamepad1.dpad_left) {
            if (ctx.teleop.gamepad1.x) {
                linearSlide1?.power = 0.5
            } else if (ctx.teleop.gamepad1.y) {
                linearSlide1?.power = -0.5
            } else if (ctx.teleop.gamepad1.a) {
                claw1?.position = 0.4
            } else if (ctx.teleop.gamepad1.b) {
                claw1?.position = 1.0
            } else {
                linearSlide1?.power = 0.0
               /** linearSlide2?.power = 0.0
                linearSlide3?.power = 0.0 **/
            }

        }/** else if (ctx.teleop.gamepad1.dpad_up) {
            if (ctx.teleop.gamepad1.x) {
                linearSlide2?.power = 0.2
            } else if (ctx.teleop.gamepad1.y) {

            } else {
                linearSlide1?.power = 0.0
                linearSlide2?.power = 0.0
                linearSlide3?.power = 0.0
            }

        } else if (ctx.teleop.gamepad1.dpad_right) {
            if (ctx.teleop.gamepad1.x) {

            } else if (ctx.teleop.gamepad1.y) {
            } else {
                linearSlide1?.power = 0.0
                linearSlide2?.power = 0.0
                linearSlide3?.power = 0.0
            }

        } else {
            linearSlide1?.power = 0.0
            linearSlide2?.power = 0.0
            linearSlide3?.power = 0.0
        }**/
    }
}
