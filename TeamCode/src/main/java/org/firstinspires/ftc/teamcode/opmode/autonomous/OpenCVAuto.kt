package org.firstinspires.ftc.teamcode.opmode.autonomous

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.opencv.core.Mat
import org.opencv.imgproc.Imgproc
import org.openftc.easyopencv.OpenCvCameraFactory
import org.openftc.easyopencv.OpenCvCameraRotation
import org.openftc.easyopencv.OpenCvInternalCamera
import org.openftc.easyopencv.OpenCvPipeline

@Autonomous(name = "OpenCV Auto")
class OpenCVAuto: LinearOpMode() {
    override fun runOpMode() {
        val cameraMonitorViewId = hardwareMap.appContext.resources.getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.packageName)
        val camera = OpenCvCameraFactory.getInstance().createInternalCamera(
            OpenCvInternalCamera.CameraDirection.BACK,
            cameraMonitorViewId
        )

        val pipeline = TestPipeline()
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

class TestPipeline: OpenCvPipeline() {
    enum class Color {
        Red,
        Green,
        Blue,
    }

    var output = Color.Blue
        private set

    private var input: Mat? = null
    // private var blurred = Mat()
    private var contrasted = Mat()

    override fun init(_firstFrame: Mat?) {
        val firstFrame = _firstFrame!!

        // val left = (firstFrame.width() / 2) - 50
        // val top = (firstFrame.height() / 2) - 50

        //input = firstFrame.submat(left, left + 100, top, top + 100)
        input = firstFrame
    }

    override fun processFrame(_input: Mat?): Mat {
        // Imgproc.blur(input, blurred, Size(10.0, 10.0))
        input!!.convertTo(contrasted, -1, 0.6)

        val p = contrasted.get(contrasted.rows() / 2, contrasted.cols() / 2)

        output = if (p[0] >= p[1] && p[0] >= p[2]) {
            Color.Red
        } else if (p[1] >= p[0] && p[1] >= p[2]) {
            Color.Green
        } else {
            Color.Blue
        }

        return contrasted
    }
}
