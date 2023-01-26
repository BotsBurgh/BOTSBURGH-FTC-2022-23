package org.firstinspires.ftc.teamcode.api.plugins.logger

import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.arch.base.Context
import org.firstinspires.ftc.teamcode.arch.base.Plugin
import kotlin.math.roundToInt

private var loggerStore: Logger? = null

val Context.logger
    get() = loggerStore!!

class Logger(private val levelFilter: Level = Level.Info) : Plugin() {
    init {
        loggerStore = this
    }

    val dataCollector = DataCollector().apply {
        registerCallback("time") {
            ctx.teleop.runtime.roundToInt().toString()
        }
    }

    // Lazy load on first call, to avoid null handling
    private val t: Telemetry by lazy {
        /*
        The MultipleTelemetry stores each telemetry in a list, then distributes commands to each.
        This may be inefficient for only two different telemetries, so we may just manually mimic it instead.
         */
        MultipleTelemetry(ctx.teleop.telemetry, FtcDashboard.getInstance().telemetry)
    }

    private fun log(msg: String, level: Level = Level.Info) {
        if (levelFilter.ordinal <= level.ordinal) {
            t.log().add("[${level.name}] $msg")
        }
    }

    fun debug(msg: String) = log(msg, Level.Debug)
    fun info(msg: String) = log(msg, Level.Info)
    fun warn(msg: String) = log(msg, Level.Warn)
    fun error(msg: String) = log(msg, Level.Error)

    enum class Level {
        Debug,
        Info,
        Warn,
        Error,
    }
}
