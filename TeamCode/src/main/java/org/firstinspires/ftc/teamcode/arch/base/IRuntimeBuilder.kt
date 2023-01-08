package org.firstinspires.ftc.teamcode.arch.base

/**
 * Interface for all runtime builders.
 */
interface IRuntimeBuilder {
    fun registerPlugin(plugin: Plugin): IRuntimeBuilder
    fun build(): IRuntime
}
