package org.firstinspires.ftc.teamcode.api.components

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.teamcode.api.arch.Component
import org.firstinspires.ftc.teamcode.api.arch.Context

class LinearSlides: Component() {
    private var linearSlide1: DcMotor? = null
    // private var linearSlide2: DcMotor? = null

    private var claw1: Servo? = null
    // private var claw2: Servo? = null

    override val pre = fun(ctx: Context) {
        this.linearSlide1 = ctx.teleop.hardwareMap.get(DcMotor::class.java, "linearSlide1")
        // this.linearSlide2 = ctx.teleop.hardwareMap.get(DcMotor::class.java, "linearSlide2")

        this.claw1 = ctx.teleop.hardwareMap.get(Servo::class.java, "claw1")

        // Prevent slide from moving down due to gravity.
        linearSlide1?.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        // linearSlide2?.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE

    }

    override val cycle = fun(ctx: Context) {
        if (ctx.teleop.gamepad1.dpad_left) {
            if (ctx.teleop.gamepad1.x) {
                linearSlide1?.power = 0.5
            } else if (ctx.teleop.gamepad1.y) {
                linearSlide1?.power = -0.5
            } else {
                linearSlide1?.power = 0.0
            }

            if (ctx.teleop.gamepad1.a) {
                claw1?.position = 0.4
            } else if (ctx.teleop.gamepad1.b) {
                claw1?.position = 1.0
            }
        }
    }
}
