package org.firstinspires.ftc.teamcode.api

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode

open class Robot(val teleop: LinearOpMode, val cfg: Config = Config()) {
    var runtime: Runtime = Runtime()

    fun run() {
        this.teleop.telemetry.addData("Status", "Initializing")
        this.teleop.telemetry.update()
        this.runtime.pre?.invokeRecursive()

        this.teleop.telemetry.addData("Status", "Initialized")
        this.teleop.telemetry.update()
        this.teleop.waitForStart()

        while (this.teleop.opModeIsActive()) {
            this.teleop.telemetry.addData("Status", "Running")
            this.runtime.cycle?.invokeRecursive()
            this.teleop.telemetry.update()
        }

        this.teleop.telemetry.addData("Status", "Shutting down")
        this.teleop.telemetry.update()
        this.runtime.post?.invokeRecursive()

        this.teleop.telemetry.addData("Status", "Finished")
        this.teleop.telemetry.update()
    }

    open fun registerComponents() {

    }
}

data class Config(val autonomous: Boolean = false)
