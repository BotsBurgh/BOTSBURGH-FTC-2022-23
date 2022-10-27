package org.firstinspires.ftc.teamcode.api.plugins

import org.firstinspires.ftc.teamcode.api.arch.Context
import org.firstinspires.ftc.teamcode.api.arch.Plugin

/**
 * A logger plugin that exposes the telemetry API.
 */
class Logger(): Plugin() {
    /**
     * Logs some data to the driver station.
     *
     * This data will be added to the queue, so you need to call [pushLog] before it will show up on the driver station.
     * For most cases, [pushLog] is handled for you in the Logger component.
     *
     * @param title The title (category) of the data being logged.
     * @param data The data being logged.
     */
    fun log(title: String, data: String) {
        this.ctx.teleop.telemetry.addData(title, data)
    }

    /**
     * Pushes data from the queue to the driver station, removing what was previously there.
     */
    fun pushLog() {
        this.ctx.teleop.telemetry.update()
    }
}

// Backend that enables data persistence with the plugin.
private val logger_store = Logger()

// Enables Context to access logger_store.
val Context.logger
    get() = logger_store

/**
 * Shorthand for `ctx.logger.log`.
 */
fun Context.log(title: String, data: String) = logger.log(title, data)
