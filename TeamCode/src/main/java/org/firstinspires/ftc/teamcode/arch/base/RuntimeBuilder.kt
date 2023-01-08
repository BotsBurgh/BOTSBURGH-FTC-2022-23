package org.firstinspires.ftc.teamcode.arch.base

/**
 * Interface for all runtime builders.
 */
interface RuntimeBuilder {
    fun registerPlugin(plugin: Plugin): RuntimeBuilder
    fun build(): IRuntime
}
