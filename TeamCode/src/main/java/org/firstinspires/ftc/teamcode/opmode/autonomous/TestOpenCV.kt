package org.firstinspires.ftc.teamcode.opmode.autonomous

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.openftc.easyopencv.OpenCvCameraFactory
import org.openftc.easyopencv.OpenCvCameraRotation
import org.openftc.easyopencv.OpenCvInternalCamera

@Autonomous(name = "Test OpenCV")
@Disabled
class TestOpenCV: LinearOpMode() {
    override fun runOpMode() {
        val cameraMonitorViewId = hardwareMap.appContext.resources.getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.packageName)
        val camera = OpenCvCameraFactory.getInstance().createInternalCamera(
            OpenCvInternalCamera.CameraDirection.BACK,
            cameraMonitorViewId
        )

        val pipeline = ConeScanPipeline()
        camera.setPipeline(pipeline)

        camera.openCameraDevice()
        camera.startStreaming(320, 240, OpenCvCameraRotation.UPSIDE_DOWN)

        telemetry.addData("Status", "Initialized aa")
        telemetry.update()

        while (opModeInInit()) {
            telemetry.addData("Color", pipeline.output)
            telemetry.update()
        }

        camera.stopStreaming()
        camera.closeCameraDevice()
    }
}
