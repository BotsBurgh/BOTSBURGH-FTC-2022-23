package org.firstinspires.ftc.teamcode.opmode.autonomous

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.api.plugins.DistanceSensors
import org.firstinspires.ftc.teamcode.api.plugins.LinearSlides
import org.firstinspires.ftc.teamcode.api.plugins.Wheels
import org.firstinspires.ftc.teamcode.api.plugins.opencv.ConeScanPipeline
import org.firstinspires.ftc.teamcode.api.plugins.opencv.OpenCV
import org.firstinspires.ftc.teamcode.api.plugins.opencv.QRPipeline
import org.firstinspires.ftc.teamcode.api.plugins.opencv.opencv
import org.firstinspires.ftc.teamcode.arch.base.Context
import org.firstinspires.ftc.teamcode.arch.sequential.SequentialRobot
import org.firstinspires.ftc.teamcode.arch.sequential.SequentialRuntimeBuilder
import org.openftc.easyopencv.OpenCvCameraFactory
import org.openftc.easyopencv.OpenCvCameraRotation
import org.openftc.easyopencv.OpenCvInternalCamera

class QRCode : LinearOpMode() {
    private var ctx: Context? = null

    private var wheelsStore: Wheels? = null
    private val wheels: Wheels
        get() = wheelsStore!!


    private var linear_slides_store: LinearSlides? = null
    private val linear_slides: LinearSlides
        get() = linear_slides_store!!

    private var distance_sensor_store: DistanceSensors? = null
    private val distance_sensor: DistanceSensors
        get() = distance_sensor_store!!


    override fun runOpMode() {


        this.ctx = Context(this)

        this.wheelsStore = Wheels()
        this.wheels._init(this.ctx!!)
        this.wheels.init()

        this.linear_slides_store = LinearSlides()
        this.linear_slides._init(this.ctx!!)
        this.linear_slides.init()

        this.distance_sensor_store = DistanceSensors()
        this.distance_sensor._init(this.ctx!!)
        this.distance_sensor.init()

        val cameraMonitorViewId = hardwareMap.appContext.resources.getIdentifier(
                "cameraMonitorViewId",
                "id",
                hardwareMap.appContext.packageName
        )
        val camera = OpenCvCameraFactory.getInstance().createInternalCamera(
                OpenCvInternalCamera.CameraDirection.BACK,
                cameraMonitorViewId
        )

        val pipeline = QRPipeline()
        camera.setPipeline(pipeline)

        camera.openCameraDevice()
        camera.startStreaming(320, 240, OpenCvCameraRotation.UPSIDE_DOWN)

        while (opModeInInit()) {
            telemetry.addData("Status", "Initialized")
            telemetry.addData("Color", pipeline.output)
            telemetry.addData("Left", this.distance_sensor.getLeft())
            telemetry.addData("Back", this.distance_sensor.getBack())
            telemetry.addData("Right", this.distance_sensor.getRight())
            //telemetry.addData("Slide", this.distance_sensor.getSlide1())
            telemetry.update()
        }

        waitForStart()

        val runtime = ElapsedTime()

        val coneColor = pipeline.output


    }
}