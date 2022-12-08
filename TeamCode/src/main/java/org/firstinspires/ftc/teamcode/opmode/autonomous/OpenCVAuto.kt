package org.firstinspires.ftc.teamcode.opmode.autonomous

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName
import org.openftc.easyopencv.OpenCvCameraFactory
import org.openftc.easyopencv.OpenCvCameraRotation
import org.openftc.easyopencv.OpenCvInternalCamera

@Autonomous(name = "OpenCV Auto")
class OpenCVAuto: LinearOpMode() {
    override fun runOpMode() {
        val cameraMonitorViewId = hardwareMap.appContext.resources.getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.packageName)
        val camera = OpenCvCameraFactory.getInstance().createInternalCamera(
            OpenCvInternalCamera.CameraDirection.BACK,
            cameraMonitorViewId
        )

        camera.openCameraDevice()
        camera.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT)

        telemetry.addData("Status", "Initialized aa")
        telemetry.update()

        waitForStart()

        camera.stopStreaming()
        camera.closeCameraDevice()

        /*
        while (opModeIsActive()) {
            telemetry.addData("Status", "Running")
            telemetry.update()
        }
         */
    }
}
