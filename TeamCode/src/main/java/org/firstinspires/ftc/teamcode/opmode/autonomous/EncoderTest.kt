package org.firstinspires.ftc.teamcode.opmode.autonomous

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.api.plugins.*
import org.firstinspires.ftc.teamcode.api.plugins.opencv.ConeScanPipeline
import org.firstinspires.ftc.teamcode.arch.base.Context
import org.openftc.easyopencv.OpenCvCameraFactory
import org.openftc.easyopencv.OpenCvCameraRotation
import org.openftc.easyopencv.OpenCvInternalCamera
import kotlin.math.PI

@Autonomous(name = "Encoder Test")
class EncoderTest :LinearOpMode() {
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

    private var wheel_encoder_store: WheelEncoders? = null
    private val wheel_encoder: WheelEncoders
        get() = wheel_encoder_store!!

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

        this.wheel_encoder_store = WheelEncoders()
        this.wheel_encoder._init(this.ctx!!)
        this.wheel_encoder.init()

        this.wheels.motor1!!.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        this.wheels.motor2!!.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        this.wheels.motor3!!.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER

        this.wheels.motor1!!.mode = DcMotor.RunMode.RUN_USING_ENCODER
        this.wheels.motor2!!.mode = DcMotor.RunMode.RUN_USING_ENCODER
        this.wheels.motor3!!.mode = DcMotor.RunMode.RUN_USING_ENCODER

        while (opModeInInit()) {
            telemetry.addData("WheelOne", this.wheels.motor1!!.currentPosition)
            telemetry.addData("WheelTwo", this.wheels.motor2!!.currentPosition)
            telemetry.addData("WheelThree", this.wheels.motor3!!.currentPosition)
            telemetry.update()
        }

        waitForStart()

        telemetry.addLine("Testing 1/12")
        telemetry.update()
        wheel_encoder.wheelEncoderSpin(45.0, 0.1)
        telemetry.addLine("Testing 2/12")
        telemetry.update()
        wheel_encoder.wheelEncoderSpin(45.0, 0.2)
        telemetry.addLine("Testing 3/12")
        telemetry.update()
        wheel_encoder.wheelEncoderSpin(45.0, 0.4)
        telemetry.addLine("Testing 4/12")
        telemetry.update()
        wheel_encoder.wheelEncoderSpin(-45.0, 0.8)
        telemetry.addLine("Testing 5/12")
        telemetry.update()
        wheel_encoder.wheelEncoderSpin(-45.0, 0.4)
        telemetry.addLine("Testing 6/12")
        telemetry.update()
        wheel_encoder.wheelEncoderSpin(-45.0, 0.2)
        telemetry.addLine("Testing 7/12")
        telemetry.update()
        wheel_encoder.wheelEncoderSpin(-45.0, 0.1)
        telemetry.addLine("Testing 8/12")
        telemetry.update()
        wheel_encoder.wheelEncoderSpin(45.0, 0.8)
        telemetry.addLine("Testing 9/12")
        telemetry.update()
        wheel_encoder.wheelEncoderDirection(PI, 24.0, 0.1)
        telemetry.addLine("Testing 10/12")
        telemetry.update()
        wheel_encoder.wheelEncoderDirection(PI, 24.0, 0.2)
        telemetry.addLine("Testing 11/12")
        telemetry.update()
        wheel_encoder.wheelEncoderDirection(PI, 24.0, 0.4)
        telemetry.addLine("Testing 12/12")
        telemetry.update()
        wheel_encoder.wheelEncoderDirection(PI, 24.0, 0.75)
        telemetry.addLine("Done")
        telemetry.update()
        sleep(1000)




    }
}