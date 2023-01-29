package org.firstinspires.ftc.teamcode.api.plugins.logger

import android.os.Environment
import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.robotcore.internal.system.AppUtil
import org.firstinspires.ftc.teamcode.arch.base.Context
import org.firstinspires.ftc.teamcode.arch.base.Plugin
import java.io.BufferedWriter
import java.io.Closeable
import java.io.File
import java.io.FileWriter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

private var loggerStore: Logger? = null

val Context.logger
    get() = loggerStore!!

val BOTSBURGH_FOLDER: File by lazy {
    val res = File(AppUtil.ROOT_FOLDER, "/BotsBurgh/")
    res.mkdirs()
    res
}

class Logger(private val levelFilter: Level = Level.Info) : Plugin(), Closeable {
    init {
        loggerStore = this
    }

    private val runtime = ElapsedTime()

    val dataCollector = DataCollector().apply {
        registerCallback("time") {
            String.format("%1.4f", runtime.time())
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

    private val logFile = File(BOTSBURGH_FOLDER, "/latest.log")
    private val logWriter = BufferedWriter(FileWriter(logFile))

    override fun init() {
        logFile.createNewFile()

        logWriter.write("BotsBurgh 11792 FTC 2022-23 Season\n")
        logWriter.write("Running Arch API for the TriRobot\n\n")
        // logWriter.write("Starting robot at ${LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss"))}\n\n")

        dataCollector.init()

        runtime.reset()
    }

    override fun close() {
        logWriter.close()
        dataCollector.close()
    }

    private fun log(msg: String, level: Level = Level.Info) {
        if (levelFilter.ordinal <= level.ordinal) {
            val out = "[${level.name}] $msg"

            t.log().add(out)
            logWriter.write(out + '\n')
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
