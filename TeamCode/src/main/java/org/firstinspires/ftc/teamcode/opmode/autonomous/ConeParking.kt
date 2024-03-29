package org.firstinspires.ftc.teamcode.opmode.autonomous

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.api.plugins.DistanceSensors
import org.firstinspires.ftc.teamcode.api.plugins.LinearSlides
import org.firstinspires.ftc.teamcode.api.plugins.Wheels
import org.firstinspires.ftc.teamcode.api.plugins.opencv.ConeScanPipeline
import org.firstinspires.ftc.teamcode.arch.base.Context
import org.openftc.easyopencv.OpenCvCameraFactory
import org.openftc.easyopencv.OpenCvCameraRotation
import org.openftc.easyopencv.OpenCvInternalCamera
import kotlin.math.PI

@Autonomous(name = "Cone Parking")
class ConeParking : LinearOpMode() {
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

        val pipeline = ConeScanPipeline()
        camera.setPipeline(pipeline)

        camera.openCameraDevice()
        camera.startStreaming(320, 240, OpenCvCameraRotation.UPSIDE_DOWN)

        while (opModeInInit()) {
            telemetry.addData("Status", "Initialized")
            telemetry.addData("Color", pipeline.output)
            telemetry.addData("Left", this.distance_sensor.getLeft())
            telemetry.addData("Back", this.distance_sensor.getBack())
            telemetry.addData("Right", this.distance_sensor.getRight())
            telemetry.update()
        }

        waitForStart()

        val runtime = ElapsedTime()

        val coneColor = pipeline.output

        camera.stopStreaming()
        camera.closeCameraDevice()

        runtime.reset()

        if (coneColor == ConeScanPipeline.Color.Green) {
            while (this.distance_sensor.getBack() < 30 && opModeIsActive()) {
                wheels.powerDirection(PI, 0.5)
            }
        } else if (coneColor == ConeScanPipeline.Color.Blue) {
            while (this.distance_sensor.getLeft() > 12.75 && opModeIsActive()) {
                wheels.powerDirection(PI / 2, 0.25)
            }; wheels.stop()
            while (this.distance_sensor.getBack() < 30 && opModeIsActive()) {
                wheels.powerDirection(PI - 0.075, 0.25)
            }
        } else if (coneColor == ConeScanPipeline.Color.Red) {
            while (distance_sensor.getLeft() > 36 && opModeIsActive()) {
                wheels.powerDirection(3 * PI / 2, 0.25)
            }; wheels.stop()
            while (runtime.seconds() < 2.55 && opModeIsActive()) {
                wheels.stop()
            }

            while (this.distance_sensor.getBack() < 30 && opModeIsActive()) {
                wheels.powerDirection(3 * PI / 4, 0.5)
            }

            while (runtime.seconds() < 4.0 && opModeIsActive()) {
                wheels.powerDirection(PI, 0.5)
            }
        }
    }
}
