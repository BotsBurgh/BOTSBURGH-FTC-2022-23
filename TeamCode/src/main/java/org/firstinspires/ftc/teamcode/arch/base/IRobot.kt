package org.firstinspires.ftc.teamcode.arch.base

/**
 * Interface for all robot classes.
 */
interface IRobot<T: IRuntimeBuilder> {
    fun configure(builder: T)
    fun run()
}
