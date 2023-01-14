package org.firstinspires.ftc.teamcode.opmode.autonomous

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.api.plugins.DistanceSensors
import org.firstinspires.ftc.teamcode.api.plugins.LinearSlides
import org.firstinspires.ftc.teamcode.api.plugins.Wheels
import org.firstinspires.ftc.teamcode.api.plugins.linear_slides
import org.firstinspires.ftc.teamcode.api.plugins.opencv.ConeScanPipeline
import org.firstinspires.ftc.teamcode.arch.base.Context
import org.openftc.easyopencv.OpenCvCameraFactory
import org.openftc.easyopencv.OpenCvCameraRotation
import org.openftc.easyopencv.OpenCvInternalCamera
import kotlin.math.PI

@Autonomous(name = "Cone Parking Left")
class ConeParkingButWithUsingACone : LinearOpMode() {
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
            telemetry.addData("Slide", this.distance_sensor.getSlide1())
            telemetry.update()
        }

        waitForStart()

        val runtime = ElapsedTime()

        val coneColor = pipeline.output

        camera.stopStreaming()
        camera.closeCameraDevice()

        runtime.reset()

        while(linear_slides.linearSlide1!!.currentPosition < 1000){
            linear_slides.linearSlide1!!.power = 0.3
        }; linear_slides.stopSlide1(); runtime.reset()

        while(distance_sensor.getBack() < 28 && opModeIsActive() && runtime.seconds() < 3) {
            wheels.powerDirection(PI, 0.25)
            telemetry.addData("Back", this.distance_sensor.getBack())
            telemetry.addData("Secconds", runtime.seconds())
            telemetry.update()
        }; wheels.stop()

        while(runtime.seconds() < 3.5 && opModeIsActive()){
            wheels.stop();
        }  ;runtime.reset()

        while(runtime.seconds() < 1.25 && opModeIsActive())  {
            telemetry.addData("SlideDistance", distance_sensor.getSlide1())
            telemetry.update()
            wheels.power(0.25)
        }; wheels.stop(); runtime.reset()

        while(linear_slides.linearSlide1!!.currentPosition < 4300 && opModeIsActive()) {
            telemetry.addData("LinearSlide", linear_slides.linearSlide1!!.currentPosition)
            telemetry.update()
            linear_slides.linearSlide1!!.power = 0.4

        }; linear_slides.stopSlide1(); runtime.reset()

        while(runtime.seconds() < 0.65 && opModeIsActive()) {
            wheels.powerDirection(7* PI / 4, 0.25)
        }; wheels.stop(); runtime.reset()

        while(runtime.seconds() < 1.5 && opModeIsActive()) {

        }

        linear_slides.claw1!!.position = 1.0

        runtime.reset()

        while(runtime.seconds() < 2){

        }

        runtime.reset()

        while(runtime.seconds() < 1.25 && opModeIsActive()) {
           wheels.powerDirection(3 * PI / 4, 0.25)
        }; wheels.stop()

        if(coneColor == ConeScanPipeline.Color.Green){
            while(linear_slides.linearSlide1!!.currentPosition > -20) {
                linear_slides.linearSlide1!!.power = -0.5
            }

        } else if(coneColor == ConeScanPipeline.Color.Blue)  {
            runtime.reset()
            while (runtime.seconds() < 4 && opModeIsActive()){
                wheels.powerDirection(PI, 0.25)
            }
        }   else if(coneColor == ConeScanPipeline.Color.Red) {
            runtime.reset()
            while (runtime.seconds() < 1.5 && opModeIsActive()) {
                wheels.powerDirection(0.0, 0.25)
            }
        }




    }
}
