package org.firstinspires.ftc.teamcode.arch.base

/**
 * Interface for all robot classes.
 */
interface Robot<T: RuntimeBuilder> {
    fun configure(builder: T)
    fun run()
}
