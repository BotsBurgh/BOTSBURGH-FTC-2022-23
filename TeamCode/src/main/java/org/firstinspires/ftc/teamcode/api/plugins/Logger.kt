package org.firstinspires.ftc.teamcode.api.plugins

import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.arch.base.Context
import org.firstinspires.ftc.teamcode.arch.base.Plugin

private var loggerStore: Logger? = null

val Context.logger
    get() = loggerStore!!

/**
 * Easy interface for working with [Telemetry].
 */
class Logger(private val level: Level = Level.Info): Plugin() {
    init {
        loggerStore = this
    }

    // Lazy load on first call, to avoid null handling
    private val t: Telemetry by lazy { ctx.teleop.telemetry }

    /**
     * Adds data to the screen in the format of `$caption: $msg`.
     *
     * This data will not be flushed to the screen until [updateData] is called.
     */
    fun addData(caption: String, msg: String) {
        t.addData(caption, msg)
    }

    /**
     * Flushes all stored log calls from [addData] to the screen.
     */
    fun update(): Boolean = t.update()

    /**
     * Logs a message to the screen.
     *
     * This is currently just an alias for [info]. This method, and all other logging methods, do not require [update] to be called unless otherwise specified.
     */
    fun log(msg: String) = t.log().add(msg)

    /**
     * Logs a debug message.
     *
     * Unless the [level] is configured otherwise, this will be ignored by default.
     */
    fun debug(msg: String) {
        if (this.level.ordinal <= Level.Debug.ordinal) {
            log(msg)
        }
    }

    /**
     * Logs an info message.
     *
     * This should be the default if you don't know what level to use.
     */
    fun info(msg: String) {
        if (this.level.ordinal <= Level.Info.ordinal) {
            log(msg)
        }
    }

    /**
     * Logs a warn message.
     *
     * Warn messages are usually errors that can be safely ignored.
     */
    fun warn(msg: String) {
        if (this.level.ordinal <= Level.Warn.ordinal) {
            log(msg)
        }
    }

    /**
     * Logs an error message.
     *
     * Error messages are used when the program is about to terminate and needs to say why.
     */
    fun error(msg: String) {
        if (this.level.ordinal <= Level.Error.ordinal) {
            log(msg)
        }
    }

    /**
     * Clears the log of all messages.
     */
    fun clearLog() = t.log().clear()

    /**
     * Represents what logger level should be filtered out or retained.
     */
    enum class Level() {
        /**
         * Retains debug and above levels.
         *
         * This is the lowest level.
         */
        Debug,

        /**
         * Retains info and above levels.
         *
         * This is the default level.
         */
        Info,

        /**
         * Retains warn and above levels.
         */
        Warn,

        /**
         * Retains error and above levels.
         *
         * This is the highest level.
         */
        Error,
    }
}
