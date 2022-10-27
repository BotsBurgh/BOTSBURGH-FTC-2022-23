package org.firstinspires.ftc.teamcode.api.plugins

import org.firstinspires.ftc.teamcode.api.arch.Context
import org.firstinspires.ftc.teamcode.api.arch.Plugin

// Backend that enables data persistence with the plugin.
private val logger_store = Logger()

// Enables Context to access logger_store.
val Context.logger
    get() = logger_store

/**
 * Shorthand for `ctx.logger.log`.
 */
fun Context.log(data: String) = logger.log(data)

/**
 * A logger plugin that exposes the telemetry API.
 */
class Logger(): Plugin() {
    private var logData: MutableList<LogData> = ArrayList()

    fun log(data: String, duration: Double = 3.0) {
        this.logData.add(LogData(data, end = this.ctx.teleop.runtime + duration))
    }

    /**
     * Logs some data to the driver station.
     *
     * This data will be added to the queue, so you need to call [pushLog] before it will show up on the driver station.
     * For most cases, [pushLog] is handled for you in the Logger component.
     *
     * @param title The title (category) of the data being logged.
     * @param data The data being logged.
     */
    fun logTitle(title: String, data: String) {
        this.ctx.teleop.telemetry.addData(title, data)
    }

    /**
     * Pushes data from the queue to the driver station, removing what was previously there.
     */
    fun pushLog() {
        val currentRuntime = this.ctx.teleop.runtime

        // Remove any message that has expired (end is less than current)
        this.logData.retainAll { it.end >= currentRuntime }

        // Logs all remaining messages with title as index
        this.logData.withIndex().forEach {
            this.logTitle(it.index.toString(), it.value.data)
        }

        this.ctx.teleop.telemetry.update()
    }
}

private data class LogData(val data: String, val end: Double)
