package org.firstinspires.ftc.teamcode.api;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class Robot {
    private final LinearOpMode opMode;

    public static final double motor1Angle = 0.0;
    public static final double motor2Angle = Math.PI * (2.0 / 3.0);
    public static final double motor3Angle = 2.0 * Math.PI * (2.0 / 3.0);

    public Robot(LinearOpMode opMode) {
        this.opMode = opMode;
    }

    /**
     * Returns the power of each motor from a joystick's polar coordinates.
     *
     * @param radians The angle of the joystick.
     * @param magnitude The distance from center of the joystick.
     * @return The power of each wheel.
     */
    public static TripleTuple motorPower(double radians, double magnitude) {
        return new TripleTuple(
                magnitude * Math.sin(Robot.motor1Angle - radians),
                magnitude * Math.sin(Robot.motor2Angle - radians),
                magnitude * Math.sin(Robot.motor3Angle - radians)
        );
    }

    /**
     * Returns the power of each motor from a joystick coordinate.
     *
     * @param x The x-position of the joystick.
     * @param y The y-position of the joystick.
     * @return The power of each wheel.
     */
    public static TripleTuple motorPowerXY(double x, double y) {
        return motorPower(
                Math.atan2(y, x) - (Math.PI / 2.0),
                Math.sqrt(y * y + x * x)
        );
    }
}
