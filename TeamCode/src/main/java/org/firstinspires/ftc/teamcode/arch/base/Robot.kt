package org.firstinspires.ftc.teamcode.arch.base

interface Robot<T: RuntimeBuilder> {
    fun configure(builder: T)
    fun run()
}
