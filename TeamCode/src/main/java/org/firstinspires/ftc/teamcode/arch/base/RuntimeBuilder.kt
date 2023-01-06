package org.firstinspires.ftc.teamcode.arch.base

interface RuntimeBuilder {
    fun registerPlugin(plugin: Plugin): RuntimeBuilder
    fun build(): IRuntime
}
