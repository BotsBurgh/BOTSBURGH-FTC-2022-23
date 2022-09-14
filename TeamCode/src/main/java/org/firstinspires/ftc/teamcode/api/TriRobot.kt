package org.firstinspires.ftc.teamcode.api

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import kotlin.math.*

class TriRobot(private val opMode: LinearOpMode) {
    private val motor1Angle: Double = 0.0
    private val motor2Angle: Double = PI * (2.0 / 3.0)
    private val motor3Angle: Double = 2.0 * motor2Angle;

    /**
     * Returns the power of each motor from a joystick's polar coordinates.
     *
     * @param radians The angle of the joystick.
     * @param magnitude The distance from the center of the joystick.
     * @return The power of each wheel.
     */
    fun motorPower(radians: Double, magnitude: Double): Triple<Double, Double, Double> = Triple(
            magnitude * sin(motor1Angle - radians),
            magnitude * sin(motor2Angle - radians),
            magnitude * sin(motor3Angle - radians),
    )

    /**
     * Returns the power of each motor from a joystick's coordinates.
     *
     * @param x The x-position of the joystick.
     * @param y The y-position of the joystick.
     * @return The power of each wheel.
     */
    fun motorPowerXY(x: Double, y: Double): Triple<Double, Double, Double> = motorPower(
            atan2(y, x) - (PI / 2.0),
            sqrt(y * y + x * x),
    )
}
