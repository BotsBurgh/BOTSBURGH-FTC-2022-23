package org.firstinspires.ftc.teamcode.opmode.autonomous

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.api.plugins.opencv.ConeScanPipeline
import org.firstinspires.ftc.teamcode.api.plugins.opencv.OpenCV
import org.firstinspires.ftc.teamcode.api.plugins.opencv.opencv
import org.firstinspires.ftc.teamcode.arch.sequential.SequentialRobot
import org.firstinspires.ftc.teamcode.arch.sequential.SequentialRuntimeBuilder

private class OpenCVTestRobot(teleop: LinearOpMode) : SequentialRobot(teleop) {
    override fun configure(builder: SequentialRuntimeBuilder) {
        val pipeline = ConeScanPipeline()

        builder
            .registerPlugin(OpenCV(pipeline))
            .registerPre {
                // Camera streaming only works in the init (pre) phase

                it.opencv.begin()

                while (it.teleop.opModeInInit()) {
                    it.teleop.telemetry.addData("Status", "Streaming during Pre")
                    it.teleop.telemetry.addData("Color", pipeline.output)
                    it.teleop.telemetry.update()
                }

                it.opencv.end()
            }
    }
}

@Autonomous(name = "Test OpenCV")
@Disabled
class TestOpenCV : LinearOpMode() {
    override fun runOpMode() {
        OpenCVTestRobot(this).run()
    }
}
