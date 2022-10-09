package org.firstinspires.ftc.teamcode.api.arch

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode

data class Context(val teleop: LinearOpMode, val cfg: Config) {
    fun log(title: String, data: String) {
        this.teleop.telemetry.addData(title, data)
    }

    fun pushLog() { this.teleop.telemetry.update() }
}
